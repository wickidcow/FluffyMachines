# Albion 26.2+ English Maintenance Notes

## Compatibility target

- Paper 26.2+.
- Java 25 toolchain and bytecode.
- Albion Slimefun Legacy derived from the SlimefunGuguProject core.
- Slimefun-relocated Dough namespace: `io.github.thebusybiscuit.slimefun4.libraries.dough`.
- Current snapshot-based `saveBackpackInventory(PlayerBackpack)` persistence API.
- Existing FluffyMachines item IDs are unchanged.

The Maven Paper API version uses `[26.2.build,)`, while the Slimefun group, artifact, and version are exposed as build properties.

## English conversion scope

All user-visible source strings were converted to English, including item registration, lore, machine menus, recipe setup, recipe selectors, guide categories, command output, multiblock messages, and documentation. A repository-wide Unicode validation scan checks for remaining CJK text.

## Dolly repair

The old Dolly could touch Bukkit block states from an asynchronous backpack callback and could leave stale chest data in its backing backpack. The rewritten transaction flow:

1. loads or creates the backing backpack asynchronously;
2. performs every Bukkit block and inventory operation on the primary server thread;
3. revalidates chest type, Slimefun status, and protection permissions after loading;
4. checks both halves of a double chest;
5. supports normal and trapped chests;
6. preserves lock text and custom names in persistent item data;
7. replaces all backing inventory slots when picking up a chest;
8. clears and persists the backing inventory only after successful placement;
9. restores contents and removes partial blocks when placement fails;
10. uses an active-operation lock instead of an arbitrary time-based cooldown.

## Fork fixes carried forward

- Fluffy Barrel item-meta comparison fix.
- Modern BrokenSpawner handling in Auto Ancient Altar.
- Storage-cache migration used by Foundry and Superheated Furnace.
- Backpack Loader/Unloader persistence fixes.
- Portable Charger missing-item guard.
- Portable Charger-to-Portable Charger anti-duplication guard.

## Additional hardening

- Uses the Slimefun-relocated Dough API required by the target Gugu/Albion core. Direct `io.github.bakedlibs.dough` imports are intentionally rejected because Slimefun shades Dough and its APIs use the relocated types.
- Updated backpack writes to the current snapshot-based save method.
- Moved Backpack Loader/Unloader mutations to the server thread.
- Confirmed output capacity before Backpack Unloader source deletion.
- Added null-safe handling for old or damaged item lore in recipe keys, Warp Pad Configurators, Watering Cans, and Smart Factory recipe displays.
- Set `api-version: '26.2'` so older servers refuse to load this compatibility build.

## 26.2.2 compilation compatibility follow-up

- Removed Lombok entirely so Java 25 compilation does not depend on implicit annotation processing.
- Added an explicit constructor to `PortableCharger.Type`.
- Updated `BlockExplodeEvent` to include the captured block state and `ExplosionResult.DESTROY`.
- Migrated `WATER_SPLASH` to `SPLASH`, `VILLAGER_HAPPY` to `HAPPY_VILLAGER`, and `REDSTONE` to `DUST`.
- Migrated `PotionEffectType.SLOW` to `SLOWNESS`.
- Replaced removal-pending `Effect.STEP_SOUND` with `Effect.DESTROY_BLOCK` and block data.
- Restored missing `Utils` imports in the Backpack Loader and Unloader.
- Updated GitHub Actions to Node 24-based action releases and refreshed Maven compiler/shade plugins.
