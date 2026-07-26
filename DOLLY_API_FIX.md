# Dolly API compatibility fix for developers

## Build failure addressed

The target dependency `com.github.SlimefunGuguProject:Slimefun4:2025.1` exposes these backpack reads:

```java
void getBackpackAsync(String backpackUuid, IAsyncReadCallback<PlayerBackpack> callback)
void getBackpackAsync(OfflinePlayer owner, int backpackNumber,
                      IAsyncReadCallback<PlayerBackpack> callback)
```

It does **not** expose one-argument `CompletableFuture` overloads in the `2025.1` artifact.

## Design used in this branch

1. An unbound Dolly asks `PlayerProfile.get(...)` for the player's profile and creates its backing backpack inside that callback.
2. A bound Dolly reads its backpack UUID from persistent item data and invokes the UUID-plus-callback controller overload.
3. Old lore-bound Dollies fall back to the owner UUID, backpack number, and the three-argument controller overload.
4. `onResult` handles a loaded backpack and upgrades legacy lore-only bindings to persistent item data.
5. `onResultNotFound` reports missing or corrupt storage and releases the operation lock.
6. Both callbacks enter `runDollyOperation`, which returns Bukkit block and inventory work to the primary server thread and removes the per-player lock in `finally`.
7. No arbitrary time-based cooldown or timeout is used.

## Compatibility and safety behavior retained

- normal and trapped chests;
- single and double chests;
- protection checks on both halves;
- Slimefun/universal storage block rejection;
- locked and custom-named chest persistence;
- rollback after failed placement;
- backing-backpack clearing only after successful placement;
- legacy Dolly binding migration to persistent data.
## Copper chest compatibility

Version 26.2.5 also accepts vanilla copper chests, including every oxidation and waxed variant available in Paper 26.2+. The exact material is stored on the Dolly and restored during placement. Double copper chests store the second half independently.

