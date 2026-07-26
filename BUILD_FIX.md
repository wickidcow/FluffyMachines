# 26.2.1 Compilation Fix

## Reported failure

The 26.2.0 maintenance source reached Maven compilation but could not resolve `Pair`, `CustomItemStack`, and `Interaction`.

## Cause

The addon had been changed to import the standalone Dough namespace:

`io.github.bakedlibs.dough`

The target Slimefun Legacy/Gugu core shades Dough into:

`io.github.thebusybiscuit.slimefun4.libraries.dough`

This distinction is required for more than class discovery. Slimefun API methods such as protection checks use the relocated `Interaction` type in their method signatures, so a standalone Dough enum would not be type-compatible even if another Dough dependency were added.

## Correction

- Restored all 46 Dough imports to the Slimefun-relocated namespace.
- Restored `Pair`, `CustomItemStack`, `Interaction`, `Config`, `ChatColors`, `BlockPosition`, `Vein`, and `PersistentDataAPI` to the target core's exposed package.
- Added a GitHub Actions guard that fails immediately if a direct `io.github.bakedlibs.dough` import is reintroduced.
- Bumped the maintenance version to `26.2.1-legacy-english`.

## Verification performed here

- Parsed Maven XML and all YAML configuration files.
- Confirmed no direct standalone Dough imports remain.
- Confirmed all relocated imports are already represented in the upstream FluffyMachines source baseline.
- Confirmed the complete source still contains no CJK text.
- Confirmed no whitespace errors in the generated patch.

The updated source still needs `mvn -B verify --file pom.xml` rerun in GitHub Actions because this local workspace does not include Maven, Java 25, or the external dependency cache.
