package io.ncbpfluffybear.fluffymachines.items;

import com.xzavier0722.mc.plugin.slimefun4.storage.util.StorageCacheUtils;
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.core.handlers.BlockPlaceHandler;
import io.github.thebusybiscuit.slimefun4.implementation.Slimefun;
import io.github.thebusybiscuit.slimefun4.libraries.dough.items.CustomItemStack;
import io.github.thebusybiscuit.slimefun4.utils.ChatUtils;
import io.github.thebusybiscuit.slimefun4.utils.NumberUtils;
import io.ncbpfluffybear.fluffymachines.utils.Utils;
import javax.annotation.Nonnull;
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenu;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;

public class MiniBarrel extends Barrel {
    private static final int MAX_STORAGE = 172800;

    public MiniBarrel(ItemGroup category, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
        super(category, item, recipeType, recipe, MAX_STORAGE);
        addItemHandler(onPlace());
    }

    private BlockPlaceHandler onPlace() {
        return new BlockPlaceHandler(false) {
            @Override
            public void onPlayerPlace(@Nonnull BlockPlaceEvent e) {
                StorageCacheUtils.setData(e.getBlock().getLocation(), "max-size", String.valueOf(barrelCapacity.getValue()));
            }
        };
    }

    @Override
    protected void buildMenu(BlockMenu menu, Block b) {
        super.buildMenu(menu, b);
        menu.replaceExistingItem(13, new CustomItemStack(Material.YELLOW_STAINED_GLASS_PANE,
                "&eChange capacity", "&7> Click to change capacity", "&eCurrent capacity: " + getCapacity(b),
                "&eMaximum: " + barrelCapacity.getValue()
        ));
        menu.addMenuClickHandler(13, (p, slot, item, action) -> {
            p.closeInventory();
            Utils.send(p, "&eEnter a Mini Fluffy Barrel capacity. Maximum: " + barrelCapacity.getValue());
            ChatUtils.awaitInput(p, message -> {
                String cleanMsg = message.replaceAll("[^0-9]", "");
                int renameSize = NumberUtils.getInt(cleanMsg, 0);

                if (renameSize == 0 || renameSize > barrelCapacity.getValue()) {
                    Utils.send(p, "&cBarrel capacity must be between 1 and " + barrelCapacity.getValue());
                    return;
                }

                if (renameSize < getStored(b)) {
                    Utils.send(p, "&cRemove excess items before lowering the capacity!");
                    return;
                }

               StorageCacheUtils.setData(b.getLocation(), "max-size", String.valueOf(renameSize));
                menu.replaceExistingItem(13, new CustomItemStack(Material.YELLOW_STAINED_GLASS_PANE,
                    "&eChange capacity", "&7> Click to change capacity", "&eCurrent capacity: " + renameSize,
                    "&eMaximum: " + barrelCapacity.getValue()
                ));
                Utils.send(p, "&aCapacity changed to: " + renameSize);
                updateMenu(b, menu, true, renameSize);
            });
            return false;
        });
    }

    @Override
    public int getCapacity(Block b) {
        String capacity = StorageCacheUtils.getData(b.getLocation(), "max-size");
        if (capacity == null) {
            StorageCacheUtils.setData(b.getLocation(), "max-size", String.valueOf(barrelCapacity.getValue()));
            return barrelCapacity.getValue();
        }
        return Integer.parseInt(capacity);
    }

    public static int getDisplayCapacity() {
        int capacity = Slimefun.getItemCfg().getInt("MINI_FLUFFY_BARREL.capacity");
        if (capacity == 0) {
            capacity = MAX_STORAGE;
        }

        return capacity;
    }
}
