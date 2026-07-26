# FluffyMachines — Albion 26.2+ English Legacy Build

FluffyMachines is a Slimefun addon containing automation machines, portable tools, storage blocks, cargo utilities, and multiblocks.

This maintenance branch targets **Paper 26.2+**, **Java 25**, and Slimefun Legacy cores derived from the **SlimefunGuguProject** storage/backpack line. Existing FluffyMachines item IDs remain unchanged.

## Full English conversion

The conversion is applied in the addon source instead of depending on a packet translator:

- item names and lore;
- guide categories;
- machine titles, buttons, status messages, and errors;
- recipe ingredients, recipe-selection displays, and recipe-help screens;
- command and console messages;
- multiblock feedback;
- issue templates and documentation.

## Included maintenance repairs

- Reworked Dolly pickup and placement for modern asynchronous backpack storage.
- Supports normal, trapped, single, double, locked, and custom-named chests.
- Checks protection and Slimefun storage data on both halves of a double chest.
- Preserves all chest inventory contents and rolls back failed transfers.
- Clears the backing Dolly backpack only after successful placement.
- Replaced the fixed Dolly timeout with a per-player in-progress transaction lock.
- Uses Slimefun Legacy’s relocated Dough API at `io.github.thebusybiscuit.slimefun4.libraries.dough`, matching the target core’s shaded public types.
- Updated backpack saves to the current snapshot-based API.
- Prevented asynchronous Bukkit inventory mutation in the Backpack Loader and Unloader.
- Prevented the Backpack Unloader from clearing an item before confirming output capacity.
- Fixed Fluffy Barrel metadata matching for metadata-free items.
- Carried forward newer Auto Ancient Altar broken-spawner handling.
- Prevented Portable Chargers from charging another Portable Charger.
- Removed the Java 25 annotation-processing dependency by replacing all Lombok-generated code with explicit Java.
- Updated removed Paper 26.2 particle, potion-effect, explosion-event, and block-effect APIs.
- Added additional null/legacy-item guards for recipe keys, Warp Pad Configurators, Watering Cans, and Smart Factory displays.

See [PATCH_NOTES.md](PATCH_NOTES.md) for technical details.

## Building

Paper 26.2 uses Java 25. Maven is configured to resolve the newest available 26.2+ Paper API build:

```bash
mvn -B clean verify
```

The default Slimefun dependency coordinates are configurable. To compile against a separately published Albion Legacy artifact:

```bash
mvn -B clean verify \
  -Dslimefun.groupId=your.maven.group \
  -Dslimefun.artifactId=Slimefun-Legacy \
  -Dslimefun.version=your-version
```

For a core JAR that is not published to a Maven repository, install it locally first using the same coordinates, then run the command above.

## Main content

### Machines

Auto Crafting Table, Auto Enhanced Crafting Table, Auto Armor Forge, Auto Magic Workbench, Auto Ancient Altar, Auto Table Saw, Water Sprinkler, Backpack Loader/Unloader, Advanced Auto Disenchanter, Electric Dust Fabricator/Recycler, Advanced Charging Bench, and Smart Factory.

### Storage, cargo, and travel

Mini Fluffy Barrel, six Fluffy Barrel tiers, Ender Chest Insertion/Extraction Nodes, Cargo Manipulator, Dolly, Warp Pads, and Alternate Elevator Plates.

### Tools and multiblocks

Portable Chargers, Fluffy Wrenches, improved explosive tools, improved Lumber Axe, Scythe, Paxel, Watering Can, Crank Generator, Foundry, and Superheated Furnace.

## Credits

Original addon by NCBPFluffyBear. This branch keeps the original addon identity and item IDs while integrating later upstream/Gugu maintenance work and Albion-specific compatibility repairs.

## Developer note for 26.2.3

The Dolly implementation intentionally does not call `getOrCreateProfileAsync(Player)` or the one-argument `PlayerBackpack.getAsync(ItemStack)`. Those methods are not available in the target Gugu `2025.1` addon API. Profile initialization uses `PlayerProfile.get(...)`, while bound Dolly storage is resolved through the profile data controller's `CompletableFuture` backpack lookup methods. This keeps the source directly buildable by other developers against the declared dependency instead of relying on a locally patched binary.
