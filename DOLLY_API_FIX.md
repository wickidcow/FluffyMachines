# Dolly API compatibility fix for developers

## Build failure addressed

The target dependency `com.github.SlimefunGuguProject:Slimefun4:2025.1` does not expose:

```java
getOrCreateProfileAsync(Player)
PlayerBackpack.getAsync(ItemStack)
```

The supported source APIs are:

```java
PlayerProfile.get(OfflinePlayer, Consumer<PlayerProfile>)
ProfileDataController.getBackpackAsync(String)
ProfileDataController.getBackpackAsync(OfflinePlayer, int)
```

## Design used in this branch

1. An unbound Dolly asks `PlayerProfile.get(...)` for the player's profile and creates its backing backpack inside that callback.
2. A bound Dolly reads its current backpack UUID from persistent item data.
3. Old lore-bound Dollies fall back to the stored owner UUID and backpack number.
4. Both controller reads return `CompletableFuture<PlayerBackpack>` and therefore complete for a valid record, a missing record (`null`), or an exception.
5. The completion is marshalled through `runDollyOperation`, which performs Bukkit work on the server thread and removes the per-player operation lock in `finally`.
6. No arbitrary time-based cooldown or timeout is used.

## Compatibility and safety behavior retained

- normal and trapped chests;
- single and double chests;
- protection checks on both halves;
- Slimefun/universal storage block rejection;
- locked and custom-named chest persistence;
- rollback after failed placement;
- backing-backpack clearing only after successful placement;
- legacy Dolly binding migration to persistent data.
