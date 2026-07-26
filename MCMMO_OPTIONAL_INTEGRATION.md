# Optional mcMMO Integration

FluffyMachines keeps mcMMO as an optional runtime integration without requiring the mcMMO API during Maven builds.

## Why this changed

The old source imported `McMMOPlayerAbilityActivateEvent` directly and declared mcMMO 2.1.149 as a provided Maven dependency. A temporary or permanent CodeMC download failure therefore prevented FluffyMachines from compiling, even for server owners who do not use mcMMO.

## New behavior

- `plugin.yml` still lists `mcMMO` under `softdepend`.
- No mcMMO artifact is declared in `pom.xml`.
- When mcMMO is installed, FluffyMachines loads `McMMOPlayerAbilityActivateEvent` from mcMMO's own plugin class loader.
- Bukkit's dynamic `registerEvent` API is used to cancel mcMMO abilities while a player is holding the FluffyMachines Paxel.
- When mcMMO is absent or its API changes, FluffyMachines continues loading and logs a warning only if integration registration was attempted.

This preserves optional support for other server owners while eliminating mcMMO as a build-time availability risk.
