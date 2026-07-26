# 26.2 Compilation Fix History

## 26.2.1 — Slimefun-relocated Dough API

The first GitHub Actions run reached Java compilation but could not resolve `Pair`, `CustomItemStack`, and `Interaction` because the addon had been changed to use the standalone Dough namespace.

The target Slimefun Legacy/Gugu core shades Dough into:

`io.github.thebusybiscuit.slimefun4.libraries.dough`

Version 26.2.1 restored all 46 affected imports and added a CI guard against direct `io.github.bakedlibs.dough` imports.

## 26.2.2 — Paper 26.2 and Java 25 API cleanup

The second GitHub Actions run passed dependency resolution and the relocated Dough checks. Compilation then exposed the remaining modern API incompatibilities:

- Java 25 no longer implicitly runs Lombok annotation processing, so the Portable Charger enum had no generated constructor.
- `BlockExplodeEvent` now requires the exploded `BlockState` and an `ExplosionResult`.
- `Particle.WATER_SPLASH`, `Particle.VILLAGER_HAPPY`, and `Particle.REDSTONE` were replaced by `SPLASH`, `HAPPY_VILLAGER`, and `DUST`.
- `PotionEffectType.SLOW` was replaced by `SLOWNESS`.
- `Effect.STEP_SOUND` is removal-pending in Paper 26.2 and was replaced by `DESTROY_BLOCK` with `BlockData`.
- Backpack Loader and Unloader were missing their local `Utils` imports after the main-thread persistence fix.

### Corrections

- Replaced the Lombok-generated Portable Charger enum constructor with an explicit Java constructor.
- Removed all remaining Lombok annotations, imports, and the Maven dependency.
- Updated the synthetic explosion event to the Paper 26.2 five-argument constructor using `ExplosionResult.DESTROY`.
- Migrated all removed particle and potion-effect names.
- Migrated the removal-pending block-break effect.
- Restored the two missing utility imports.
- Updated GitHub Actions to the Node 24-based `actions/checkout@v5` and `actions/setup-java@v5` releases.
- Updated Maven Compiler Plugin to 3.14.1 and Maven Shade Plugin to 3.6.2.
- Added CI guards against the removed Paper names and against reintroducing Lombok.
- Bumped the maintenance version to `26.2.2-legacy-english`.

## Local verification

- Maven XML and all YAML files parse successfully.
- No standalone Dough imports remain.
- No Lombok references remain.
- No removed Paper particle or potion constants remain.
- No CJK text remains in source, resources, documentation, or GitHub configuration.
- `git diff --check` reports no whitespace errors.

A complete Maven compile still must run in GitHub Actions because this workspace does not contain Maven, Java 25, or the external dependency cache.


## 26.2.3 — Initial Dolly storage migration

The next build reached Dolly and showed that profile creation and `PlayerBackpack#getAsync(ItemStack)` differed from the Legacy/Gugu API. Profile preparation was migrated to `PlayerProfile.get(...)`, but the first storage-controller implementation incorrectly assumed newer one-argument future overloads.

## 26.2.4 — Exact Gugu 2025.1 callback signatures

The compiler identified the exact available overloads. Version 26.2.4 now uses `IAsyncReadCallback<PlayerBackpack>` with both the UUID lookup and owner/number lookup. It also removes the seven removal warnings printed by the same build by replacing `ItemStackHelper` and `Effect.VILLAGER_PLANT_GROW`.
