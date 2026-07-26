package io.ncbpfluffybear.fluffymachines.utils;

import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.logging.Level;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.EventException;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.plugin.EventExecutor;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Optional mcMMO integration which deliberately avoids compile-time references
 * to mcMMO classes. This keeps mcMMO optional for both Maven builds and servers.
 */
public final class McMMOEvents {

    private static final String ABILITY_EVENT_CLASS =
        "com.gmail.nossr50.events.skills.abilities.McMMOPlayerAbilityActivateEvent";

    private McMMOEvents() {}

    /**
     * Registers the mcMMO ability listener when a compatible mcMMO plugin is present.
     *
     * @param plugin FluffyMachines plugin instance
     * @return {@code true} when the integration was registered
     */
    public static boolean register(JavaPlugin plugin) {
        PluginManager pluginManager = plugin.getServer().getPluginManager();
        Plugin mcMMO = pluginManager.getPlugin("mcMMO");

        if (mcMMO == null || !mcMMO.isEnabled()) {
            return false;
        }

        try {
            ClassLoader mcMMOClassLoader = mcMMO.getClass().getClassLoader();
            Class<? extends Event> abilityEventClass = Class
                .forName(ABILITY_EVENT_CLASS, false, mcMMOClassLoader)
                .asSubclass(Event.class);
            Method getPlayer = abilityEventClass.getMethod("getPlayer");

            Listener listener = new Listener() {};
            EventExecutor executor = (ignored, event) -> handleAbilityEvent(event, getPlayer);

            pluginManager.registerEvent(
                abilityEventClass,
                listener,
                EventPriority.NORMAL,
                executor,
                plugin,
                true
            );
            return true;
        } catch (ClassNotFoundException | NoSuchMethodException | ClassCastException | LinkageError ex) {
            plugin.getLogger().log(
                Level.WARNING,
                "Unable to enable optional mcMMO integration. FluffyMachines will continue without it.",
                ex
            );
            return false;
        }
    }

    private static void handleAbilityEvent(Event event, Method getPlayer) throws EventException {
        try {
            Object value = getPlayer.invoke(event);
            if (!(value instanceof Player player)) {
                return;
            }

            SlimefunItem slimefunItem = SlimefunItem.getByItem(player.getInventory().getItemInMainHand());
            if (slimefunItem != null
                && slimefunItem.getId().equals(FluffyItems.PAXEL.getItemId())
                && event instanceof Cancellable cancellable) {
                cancellable.setCancelled(true);
            }
        } catch (IllegalAccessException | InvocationTargetException ex) {
            throw new EventException(ex);
        }
    }
}
