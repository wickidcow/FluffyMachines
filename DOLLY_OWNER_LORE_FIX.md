# Dolly Owner Lore English Fix

## Problem

Dollies compiled against an older translated Slimefun artifact could inherit the
translated backpack owner placeholder. Existing Dollies could also retain
that line when they were rebound to a backpack.

## Fix

- The Dolly item template now defines `&7Owner: ` directly instead of importing
  the compile-time `PlayerBackpack.LORE_OWNER` constant from a dependency.
- Every Dolly use normalizes known simplified and traditional Chinese owner
  labels to `Owner:`.
- Backpack creation and loading remove duplicate owner lines and preserve the
  resolved player name.
- Slimefun IDs, backpack UUIDs, owner UUIDs, chest contents, and all unrelated
  item metadata remain untouched.

## Existing items

After installing this build, using an affected Dolly once updates its owner lore.
Running `/sf doctor repair confirm` again will also repair stored Dollies because
the registered FluffyMachines template is now fully English.
