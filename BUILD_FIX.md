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

## 26.2.3 — Gugu backpack/profile API compatibility

The Java 25 build then reached `Dolly.java` and exposed three source-level API mismatches against `com.github.SlimefunGuguProject:Slimefun4:2025.1`:

- `ProfileDataController#getOrCreateProfileAsync(Player)` is not part of this API.
- `PlayerBackpack#getAsync(ItemStack)` is not available; this fork exposes the callback overload and controller `CompletableFuture` reads.
- Both pickup and placement therefore needed a source-level migration rather than a compiled-JAR-only workaround.

### Corrections

- Restored profile creation/loading through the public `PlayerProfile#get(OfflinePlayer, Consumer<PlayerProfile>)` API used by the original addon line.
- Kept profile acquisition outside the Dolly transaction lock, avoiding a permanent lock when an older Gugu core is already loading the same profile and does not queue another callback.
- Added `getBackpackFuture(ItemStack)` to resolve current PDC bindings and legacy lore bindings through `ProfileDataController#getBackpackAsync(...)`.
- Handles successful, missing, malformed, and exceptionally completed backpack reads and always releases the Dolly operation lock without a fixed timeout.
- Preserves migration of old lore-only Dollies to the current backpack persistent-data binding.
- Bumped the maintenance version to `26.2.3-legacy-english`.
