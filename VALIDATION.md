# FluffyMachines 26.2+ Validation Report

## Automated checks completed

- `pom.xml` parsed successfully as XML.
- `plugin.yml`, `config.yml`, the GitHub workflow, and issue-template YAML files parsed successfully.
- A repository-wide Unicode scan found no CJK characters in source, resources, documentation, or GitHub configuration.
- No imports remain under the removed shaded namespace `io.github.thebusybiscuit.slimefun4.libraries.dough`.
- No calls remain to the deprecated slot-list backpack save overloads.
- Paper API targeting is set to 26.2+ and the Java release is set to 25.
- `plugin.yml` declares `api-version: '26.2'`.
- Dolly source assertions confirmed:
  - no fixed time-based timeout;
  - per-player active-operation locking;
  - main-thread marshaling for Bukkit block and inventory work;
  - normal and trapped chest support;
  - regular and universal Slimefun block protection checks;
  - double-chest half checks;
  - backing-backpack clearing after successful placement;
  - rollback handling after failed placement.
- Portable Charger anti-duplication branching and Fluffy Barrel metadata matching are present.
- A Java parser pass over all production `.java` files found no syntax-pattern diagnostics. The parser reported missing external packages because dependency artifacts are not installed in this environment.
- `git diff --check` produced no whitespace-error output.

## Full build status

A complete Maven build was not performed in this workspace because Maven, Java 25, and the external Paper/Slimefun dependency artifacts are unavailable here. The included GitHub Actions workflow runs `mvn -B verify` with Temurin Java 25.

To compile against a separately published Albion Slimefun Legacy artifact, override the exposed Maven properties described in `README.md`.

## Required staging-server checks

Use a copy of the live server and test all of the following before replacing the production JAR:

1. Pick up and place a normal single chest.
2. Pick up and place a trapped single chest.
3. Pick up and place a normal double chest by clicking each half in separate tests.
4. Pick up and place a trapped double chest by clicking each half in separate tests.
5. Verify locked and custom-named chests preserve their data.
6. Verify protection denial on either half prevents a double-chest pickup or placement.
7. Verify a chest carrying regular or universal Slimefun block data is rejected.
8. Restart while a Dolly is carrying a chest, then place it and confirm every slot persists exactly once.
9. Force an obstructed placement and confirm the Dolly retains all contents.
10. Test old bound Dollies created by the previous build.
11. Test Backpack Loader and Backpack Unloader with full, partially full, and empty outputs.
12. Open every FluffyMachines guide category, recipe, recipe selector, and machine GUI and confirm all visible text is English.
13. Test Portable Charger insertion, removal, logout/close recovery, and Portable-Charger-to-Portable-Charger rejection.
14. Test Fluffy Barrels with vanilla items that have no explicit metadata and with metadata-bearing items.
15. Test Auto Ancient Altar broken-spawner repair recipes.
