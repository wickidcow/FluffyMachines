package io.ncbpfluffybear.fluffymachines.utils;

import dev.j3fftw.extrautils.utils.LoreBuilderDynamic;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.core.attributes.MachineTier;
import io.github.thebusybiscuit.slimefun4.core.attributes.MachineType;
import io.github.thebusybiscuit.slimefun4.utils.LoreBuilder;
import io.github.thebusybiscuit.slimefun4.utils.itemstack.ColoredFireworkStar;
import io.ncbpfluffybear.fluffymachines.items.FireproofRune;
import io.ncbpfluffybear.fluffymachines.items.MiniBarrel;
import io.ncbpfluffybear.fluffymachines.items.tools.FluffyWrench;
import io.ncbpfluffybear.fluffymachines.items.tools.PortableCharger;
import io.ncbpfluffybear.fluffymachines.machines.AdvancedAutoDisenchanter;
import io.ncbpfluffybear.fluffymachines.machines.AdvancedChargingBench;
import io.ncbpfluffybear.fluffymachines.machines.AutoAncientAltar;
import io.ncbpfluffybear.fluffymachines.machines.AutoCraftingTable;
import io.ncbpfluffybear.fluffymachines.machines.AutoTableSaw;
import io.ncbpfluffybear.fluffymachines.machines.BackpackLoader;
import io.ncbpfluffybear.fluffymachines.machines.BackpackUnloader;
import io.ncbpfluffybear.fluffymachines.machines.ElectricDustFabricator;
import io.ncbpfluffybear.fluffymachines.machines.ElectricDustRecycler;
import io.ncbpfluffybear.fluffymachines.machines.SmartFactory;
import io.ncbpfluffybear.fluffymachines.machines.WaterSprinkler;
import io.ncbpfluffybear.fluffymachines.multiblocks.CrankGenerator;
import io.ncbpfluffybear.fluffymachines.objects.AutoCrafter;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

/**
 * Specifies all plugin items
 */
public class FluffyItems {

    private FluffyItems() {
    }

    // Barrels
    public static final SlimefunItemStack MINI_FLUFFY_BARREL = new SlimefunItemStack(
        "MINI_FLUFFY_BARREL",
        Material.COMPOSTER,
        "&eMini Fluffy Barrel",
        "",
        "&7Stores a large amount of items",
        "&7Its capacity can be changed",
        "",
        "&bMaximum capacity: &e" + MiniBarrel.getDisplayCapacity() + " items"
    );

    // Portable Chargers
    public static final SlimefunItemStack SMALL_PORTABLE_CHARGER = new SlimefunItemStack(
        "SMALL_PORTABLE_CHARGER",
        Material.BRICK,
        "&ePortable Charger I",
        "",
        "&7Charges held items and equipment",
        "",
        "&eCharge speed: &7" + PortableCharger.Type.SMALL.chargeSpeed + " J/s",
        LoreBuilder.powerCharged(0, PortableCharger.Type.SMALL.chargeCapacity)
    );

    public static final SlimefunItemStack MEDIUM_PORTABLE_CHARGER = new SlimefunItemStack(
        "MEDIUM_PORTABLE_CHARGER",
        Material.IRON_INGOT,
        "&6Portable Charger II",
        "",
        "&7Charges held items and equipment",
        "",
        "&eCharge speed: &7" + PortableCharger.Type.MEDIUM.chargeSpeed + " J/s",
        LoreBuilder.powerCharged(0, PortableCharger.Type.MEDIUM.chargeCapacity)
    );

    public static final SlimefunItemStack BIG_PORTABLE_CHARGER = new SlimefunItemStack(
        "BIG_PORTABLE_CHARGER",
        Material.GOLD_INGOT,
        "&aPortable Charger III",
        "",
        "&7Charges held items and equipment",
        "",
        "&eCharge speed: &7" + PortableCharger.Type.BIG.chargeSpeed + " J/s",
        LoreBuilder.powerCharged(0, PortableCharger.Type.BIG.chargeCapacity)
    );

    public static final SlimefunItemStack LARGE_PORTABLE_CHARGER = new SlimefunItemStack(
        "LARGE_PORTABLE_CHARGER",
        Material.NETHER_BRICK,
        "&2Portable Charger IV",
        "",
        "&7Charges held items and equipment",
        "",
        "&eCharge speed: &7" + PortableCharger.Type.LARGE.chargeSpeed + " J/s",
        LoreBuilder.powerCharged(0, PortableCharger.Type.LARGE.chargeCapacity)
    );

    public static final SlimefunItemStack CARBONADO_PORTABLE_CHARGER = new SlimefunItemStack(
        "CARBONADO_PORTABLE_CHARGER",
        Material.NETHERITE_INGOT,
        "&4Portable Charger V",
        "",
        "&7Charges held items and equipment",
        "",
        "&eCharge speed: &7" + PortableCharger.Type.CARBONADO.chargeSpeed + " J/s",
        LoreBuilder.powerCharged(0, PortableCharger.Type.CARBONADO.chargeCapacity)
    );

    // Items
    public static final SlimefunItemStack ANCIENT_BOOK = new SlimefunItemStack(
        "ANCIENT_BOOK",
        Material.BOOK,
        "&6Ancient Book",
        "",
        "&7Used in the &cAdvanced Auto Disenchanter",
        "",
        "&6&oCondensed essence of a thousand years"
    );
    public static final SlimefunItemStack HELICOPTER_HAT = new SlimefunItemStack(
        "HELICOPTER_HAT",
        Material.LEATHER_HELMET, Color.AQUA,
        "&1Helicopter Hat",
        "",
        "&7brrrrrrrrRRRRRRRR",
        "",
        "&eSneak&7 to use"
    );
    public static final SlimefunItemStack WATERING_CAN = new SlimefunItemStack(
        "WATERING_CAN",
        "6484da45301625dee79ae29ff513efa583f1ed838033f20db80963cedf8aeb0e",
        "&bWatering Can",
        "",
        "&fWaters plants",
        "",
        "&7> &eRight-click&7 water to fill the can",
        "&7> &eRight-click&7 crops to accelerate growth.",
        "&7> &eRight-click&7 to water a smaller area",
        "",
        "&aWater remaining: &e0"
    );
    public static final SlimefunItemStack ENDER_CHEST_EXTRACTION_NODE = new SlimefunItemStack(
        "ENDER_CHEST_EXTRACTION_NODE",
        "e707c7f6c3a056a377d4120028405fdd09acfcd5ae804bfde0f653be866afe39",
        "&6Ender Chest Extraction Node",
        "",
        "&7Place this machine beside an &5Ender Chest",
        "",
        "&7Moves items out of the &5Ender Chest",
        "&7and into an adjacent &6Chest"
    );
    public static final SlimefunItemStack ENDER_CHEST_INSERTION_NODE = new SlimefunItemStack(
        "ENDER_CHEST_INSERTION_NODE",
        "7e5dc50c0186d53381d9430a2eff4c38f816b8791890c7471ffdb65ba202bc5",
        "&bEnder Chest Insertion Node",
        "",
        "&7Place this machine beside an &5Ender Chest",
        "",
        "&7Moves items into the &5Ender Chest",
        "&7using items from an adjacent &6Chest"
    );
    // Machines
    public static final SlimefunItemStack AUTO_CRAFTING_TABLE = new SlimefunItemStack(
        "AUTO_CRAFTING_TABLE",
        Material.CRAFTING_TABLE,
        "&6Auto Crafting Table (Vanilla)",
        "",
        "&7Automatically crafts &fvanilla&7 items",
        "",
        LoreBuilderDynamic.powerBuffer(AutoCraftingTable.CAPACITY),
        LoreBuilderDynamic.powerPerSecond(AutoCraftingTable.ENERGY_CONSUMPTION)
    );
    public static final SlimefunItemStack AUTO_ANCIENT_ALTAR = new SlimefunItemStack(
        "AUTO_ANCIENT_ALTAR",
        Material.ENCHANTING_TABLE,
        "&5Auto Ancient Altar",
        "",
        "&7Automatically crafts &5Ancient Altar&7 items",
        "",
        LoreBuilderDynamic.powerBuffer(AutoAncientAltar.CAPACITY),
        LoreBuilderDynamic.powerPerSecond(AutoAncientAltar.ENERGY_CONSUMPTION)
    );
    public static final SlimefunItemStack AUTO_TABLE_SAW = new SlimefunItemStack(
        "AUTO_TABLE_SAW",
        Material.STONECUTTER,
        "&6Auto Table Saw",
        "",
        "&7Automatically crafts &6Table Saw&7 items",
        "",
        LoreBuilderDynamic.powerBuffer(AutoTableSaw.CAPACITY),
        LoreBuilderDynamic.powerPerSecond(AutoTableSaw.ENERGY_CONSUMPTION)
    );
    public static final SlimefunItemStack WATER_SPRINKER = new SlimefunItemStack(
        "WATER_SPRINKLER",
        "d6b13d69d1929dcf8edf99f3901415217c6a567d3a6ead12f75a4de3ed835e85",
        "&bWater Sprinkler",
        "",
        "&7Automatically waters nearby crops",
        "",
        LoreBuilderDynamic.powerBuffer(WaterSprinkler.CAPACITY),
        LoreBuilderDynamic.powerPerSecond(WaterSprinkler.ENERGY_CONSUMPTION) + " per crop"
    );
    public static final SlimefunItemStack GENERATOR_CORE = new SlimefunItemStack(
        "GENERATOR_CORE",
        Material.BLAST_FURNACE,
        "&7Generator Core",
        "",
        "&7A component for generators"
    );
    public static final SlimefunItemStack CRANK_GENERATOR = new SlimefunItemStack(
        "CRANK_GENERATOR",
        Material.BLAST_FURNACE,
        "&7Crank Generator",
        "",
        "&eRight-click&7 the lever to generate power",
        "",
        LoreBuilderDynamic.power(CrankGenerator.RATE, " per use"),
        LoreBuilderDynamic.powerBuffer(CrankGenerator.CAPACITY),
        "",
        Utils.multiBlockWarning()
    );

    public static final SlimefunItemStack FOUNDRY = new SlimefunItemStack(
        "FOUNDRY",
        Material.BLAST_FURNACE,
        "&cFoundry",
        "",
        "&eStores dusts and ingots",
        "&7Stores up to 138,240 ingots",
        "",
        Utils.multiBlockWarning()
    );

    public static final SlimefunItemStack BACKPACK_UNLOADER = new SlimefunItemStack(
        "BACKPACK_UNLOADER",
        Material.BROWN_STAINED_GLASS,
        "&eBackpack Unloader",
        "",
        "&7Unloads items from backpacks",
        "",
        LoreBuilderDynamic.powerBuffer(BackpackUnloader.CAPACITY),
        LoreBuilderDynamic.powerPerSecond(BackpackUnloader.ENERGY_CONSUMPTION)
    );
    public static final SlimefunItemStack BACKPACK_LOADER = new SlimefunItemStack(
        "BACKPACK_LOADER",
        Material.ORANGE_STAINED_GLASS,
        "&eBackpack Loader",
        "",
        "&7Loads items into backpacks",
        "",
        LoreBuilderDynamic.powerBuffer(BackpackLoader.CAPACITY),
        LoreBuilderDynamic.powerPerSecond(BackpackLoader.ENERGY_CONSUMPTION)
    );
    public static final SlimefunItemStack UPGRADED_EXPLOSIVE_PICKAXE = new SlimefunItemStack(
        "UPGRADED_EXPLOSIVE_PICKAXE",
        Material.DIAMOND_PICKAXE,
        "&e&lImproved Explosive Pickaxe",
        "",
        "&7Mines a 5x5 area"
    );
    public static final SlimefunItemStack UPGRADED_EXPLOSIVE_SHOVEL = new SlimefunItemStack(
        "UPGRADED_EXPLOSIVE_SHOVEL",
        Material.DIAMOND_SHOVEL,
        "&e&lImproved Explosive Shovel",
        "",
        "&7Mines a 5x5 area"
    );
    public static final SlimefunItemStack FIREPROOF_RUNE = new SlimefunItemStack(
        "FIREPROOF_RUNE",
        new ColoredFireworkStar(Color.fromRGB(255, 165, 0),
            "&7Ancient Rune&8&l[&c&lFireproof&8&l]",
            "",
            "&eDrop this rune together with another item",
            "&eThe item will become &cFireproof",
            ""
        )
    );
    public static final SlimefunItemStack SUPERHEATED_FURNACE = new SlimefunItemStack(
        "SUPERHEATED_FURNACE",
        Material.BLAST_FURNACE,
        "&cSuperheated Furnace",
        "",
        "&7Foundry component",
        "&cDo not break this with explosive tools!"
    );
    public static final SlimefunItemStack AUTO_ENHANCED_CRAFTING_TABLE = new SlimefunItemStack(
        "AUTO_ENHANCED_CRAFTING_TABLE",
        Material.CRAFTING_TABLE,
        "&eAuto Enhanced Crafting Table",
        "",
        "&7Automatically crafts &eEnhanced Crafting Table&7 items",
        "",
        LoreBuilderDynamic.powerBuffer(AutoCrafter.CAPACITY),
        LoreBuilderDynamic.powerPerSecond(AutoCrafter.ENERGY_CONSUMPTION)
    );
    public static final SlimefunItemStack AUTO_MAGIC_WORKBENCH = new SlimefunItemStack(
        "AUTO_MAGIC_WORKBENCH",
        Material.BOOKSHELF,
        "&6Auto Magic Workbench",
        "",
        "&7Automatically crafts &6Magic Workbench&7 items",
        "",
        LoreBuilderDynamic.powerBuffer(AutoCrafter.CAPACITY),
        LoreBuilderDynamic.powerPerSecond(AutoCrafter.ENERGY_CONSUMPTION)
    );
    public static final SlimefunItemStack AUTO_ARMOR_FORGE = new SlimefunItemStack(
        "AUTO_ARMOR_FORGE",
        Material.SMITHING_TABLE,
        "&7Auto Armor Forge",
        "",
        "&7Automatically crafts &6Armor Forge&7 items",
        "",
        LoreBuilderDynamic.powerBuffer(AutoCrafter.CAPACITY),
        LoreBuilderDynamic.powerPerSecond(AutoCrafter.ENERGY_CONSUMPTION)
    );
    public static final SlimefunItemStack ADVANCED_AUTO_DISENCHANTER = new SlimefunItemStack(
        "ADVANCED_AUTO_DISENCHANTER",
        Material.ENCHANTING_TABLE,
        "&cAdvanced Auto Disenchanter",
        "",
        "&7Removes one selected enchantment from an item",
        "&7Requires an &6Ancient Book&7 to operate",
        "",
        LoreBuilderDynamic.powerBuffer(AdvancedAutoDisenchanter.CAPACITY),
        LoreBuilderDynamic.powerPerSecond(AdvancedAutoDisenchanter.ENERGY_CONSUMPTION)
    );
    public static final SlimefunItemStack SCYTHE = new SlimefunItemStack(
        "SCYTHE",
        Material.IRON_HOE,
        "&eScythe",
        "",
        "&7Harvests five crops at once"
    );
    public static final SlimefunItemStack UPGRADED_LUMBER_AXE = new SlimefunItemStack(
        "UPGRADED_LUMBER_AXE",
        Material.DIAMOND_AXE,
        "&6&lImproved Lumber Axe",
        "",
        "&7Cuts down an entire tree at once.",
        "&7Right-click to strip logs."
    );
    public static final SlimefunItemStack DOLLY = new SlimefunItemStack(
        "DOLLY",
        Material.MINECART,
        "&bDolly",
        "",
        "&7Right-click to pick up a chest",
        "",
        "&7Owner: "
    );

    public static final SlimefunItemStack WARP_PAD = new SlimefunItemStack(
        "WARP_PAD",
        Material.SMOKER,
        "&6Warp Pad",
        "",
        "&7Teleports you to another Warp Pad",
        "&7Requires a Warp Pad Configurator",
        "",
        "&7Sneak while standing on the Warp Pad to teleport"
    );

    public static final SlimefunItemStack WARP_PAD_CONFIGURATOR = new SlimefunItemStack(
        "WARP_PAD_CONFIGURATOR",
        Material.BLAZE_ROD,
        "&6Warp Pad Configurator",
        "",
        "&eSneak-right-click&7 to select a destination",
        "&eRight-click&7 to set an origin",
        "",
        "&eLinked coordinates: &7None"
    );

    public static final SlimefunItemStack ELECTRIC_DUST_FABRICATOR = new SlimefunItemStack(
        "ELECTRIC_DUST_FABRICATOR",
        Material.BLAST_FURNACE,
        "&6Electric Dust Fabricator",
        "",
        "&7A grinder, gold pan, and ore washer in one machine",
        LoreBuilder.machine(MachineTier.END_GAME, MachineType.MACHINE),
        LoreBuilder.speed(10),
        LoreBuilderDynamic.powerBuffer(ElectricDustFabricator.CAPACITY),
        LoreBuilderDynamic.powerPerSecond(ElectricDustFabricator.ENERGY_CONSUMPTION)
    );

    public static final SlimefunItemStack ELECTRIC_DUST_RECYCLER = new SlimefunItemStack(
        "ELECTRIC_DUST_RECYCLER",
        Material.IRON_BLOCK,
        "&fElectric Dust Recycler",
        "",
        "&7Recycles dust back into sifted ore",
        LoreBuilder.machine(MachineTier.END_GAME, MachineType.MACHINE),
        LoreBuilder.speed(1),
        LoreBuilderDynamic.powerBuffer(ElectricDustRecycler.CAPACITY),
        LoreBuilderDynamic.powerPerSecond(ElectricDustRecycler.ENERGY_CONSUMPTION)
    );

    public static final SlimefunItemStack ALTERNATE_ELEVATOR_PLATE = new SlimefunItemStack(
        "ALTERNATE_ELEVATOR_PLATE",
        Material.POLISHED_BLACKSTONE_PRESSURE_PLATE,
        "&3Alternate Elevator Plate",
        "",
        "&fPlace one on every floor",
        "&fAll elevator plates must share the same vertical column.",
        "",
        "&eRight-click&7 to name the floor",
        ""
    );

    public static final SlimefunItemStack FLUFFY_WRENCH = new SlimefunItemStack(
        "FLUFFY_WRENCH",
        FluffyWrench.Wrench.DEFAULT.getMaterial(),
        "&6Fluffy Wrench",
        "",
        "&7Quickly dismantles Slimefun network components and machines (uses durability)",
        "",
        "&eLeft-click/right-click&7 to dismantle"
    );

    public static final SlimefunItemStack REINFORCED_FLUFFY_WRENCH = new SlimefunItemStack(
        "REINFORCED_FLUFFY_WRENCH",
        FluffyWrench.Wrench.REINFORCED.getMaterial(),
        "&bReinforced Fluffy Wrench",
        "",
        "&7Quickly dismantles Slimefun network components and machines (uses durability)",
        "",
        "&eLeft-click/right-click&7 to dismantle"
    );

    public static final SlimefunItemStack CARBONADO_FLUFFY_WRENCH = new SlimefunItemStack(
        "CARBONADO_FLUFFY_WRENCH",
        FluffyWrench.Wrench.CARBONADO.getMaterial(),
        "&7Carbonado Fluffy Wrench",
        "",
        "&7Quickly dismantles Slimefun network components and machines (uses 1 J per operation)",
        "",
        "&eLeft-click/right-click&7 to dismantle",
        "",
        LoreBuilder.powerCharged(0, FluffyWrench.Wrench.CARBONADO.getMaxCharge())
    );

    public static final SlimefunItemStack PAXEL = new SlimefunItemStack(
        "PAXEL",
        Material.DIAMOND_PICKAXE,
        "&bPaxel",
        "",
        "&7Pickaxe, axe, and shovel in one tool!"
    );

    public static final SlimefunItemStack ADVANCED_CHARGING_BENCH = new SlimefunItemStack(
        "ADVANCED_CHARGING_BENCH",
        Material.SMITHING_TABLE,
        "&cAdvanced Charging Bench",
        "",
        "&7Charges items",
        "&7Can be upgraded with an &6ACB Upgrade Card"
    );

    public static final SlimefunItemStack ACB_UPGRADE_CARD = new SlimefunItemStack(
        "ACB_UPGRADE_CARD",
        Material.PAPER,
        "&6ACB Upgrade Card",
        "",
        "&eRight-click an &cAdvanced Charging Bench&7 to upgrade it",
        "",
        "&6Charge speed: &a+" + AdvancedChargingBench.CHARGE + "J",
        "&6Capacity: &a+" + AdvancedChargingBench.CAPACITY + "J",
        "&6Energy consumption: &c+" + AdvancedChargingBench.ENERGY_CONSUMPTION + "J"
    );

    public static final SlimefunItemStack CARGO_MANIPULATOR = new SlimefunItemStack(
        "CARGO_MANIPULATOR",
        Material.SEA_PICKLE,
        "&9Cargo Manipulator",
        "",
        "&eRight-click&7 to copy a Cargo Node configuration",
        "&eLeft-click&7 to apply a Cargo Node configuration",
        "&eShift-right-click&7 to clear the saved configuration"
    );

    public static final SlimefunItemStack EXP_DISPENSER = new SlimefunItemStack(
        "EXP_DISPENSER",
        Material.DISPENSER,
        "&aExperience Dispenser",
        "",
        "&7Right-click to collect experience from bottles",
        "&7inside the dispenser and the Fluffy Barrel it faces",
        "&7converting all experience bottles into experience",
        "",
        Utils.multiBlockWarning()
    );

    public static final SlimefunItemStack SMART_FACTORY = new SlimefunItemStack(
        "SMART_FACTORY",
        Material.SMOKER,
        "&cSmart Factory",
        "",
        "&7An all-in-one processing machine",
        "&7Crafts selected resources directly from raw materials",
        "",
        LoreBuilder.machine(MachineTier.END_GAME, MachineType.MACHINE),
        LoreBuilder.speed(1),
        LoreBuilderDynamic.powerBuffer(SmartFactory.getEnergyCapacity()),
        LoreBuilderDynamic.powerPerSecond(SmartFactory.getEnergyConsumption())
    );

    static {
        FireproofRune.setFireproof(FIREPROOF_RUNE);
        addGlow(SMALL_PORTABLE_CHARGER);
        addGlow(MEDIUM_PORTABLE_CHARGER);
        addGlow(BIG_PORTABLE_CHARGER);
        addGlow(LARGE_PORTABLE_CHARGER);
        addGlow(CARBONADO_PORTABLE_CHARGER);
    }

    private static void addGlow(ItemStack item) {
        item.addUnsafeEnchantment(Enchantment.BINDING_CURSE, 1);
        ItemMeta meta = item.getItemMeta();
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        item.setItemMeta(meta);
    }
}
