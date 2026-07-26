# Dolly copper chest support

FluffyMachines 26.2.5 extends the Dolly's vanilla container support to copper chests.

Supported materials include:

- Copper Chest
- Exposed Copper Chest
- Weathered Copper Chest
- Oxidized Copper Chest
- Waxed Copper Chest
- Waxed Exposed Copper Chest
- Waxed Weathered Copper Chest
- Waxed Oxidized Copper Chest

The implementation recognizes copper chests by their Bukkit material name ending in `_COPPER_CHEST`, while also accepting the base `COPPER_CHEST` material. This avoids a brittle list and remains compatible with future copper chest stages that follow the same vanilla naming convention.

The Dolly stores the exact material in its persistent item data. For double copper chests, the second half's material is stored separately so mixed oxidation stages are not silently normalized. Existing Dollies without the second-half key remain compatible and use the first chest material for both halves.

All existing safeguards remain active:

- Protection checks on both halves
- Slimefun block rejection
- Locked chest setting
- Custom name and lock preservation
- Main-thread Bukkit operations
- Safe rollback after placement or storage failure
- No arbitrary operation timeout
