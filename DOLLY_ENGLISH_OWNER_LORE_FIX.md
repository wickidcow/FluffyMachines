# Dolly English owner lore fix

FluffyMachines 26.2.7 no longer uses `PlayerBackpack.LORE_OWNER` in its item definition.
The Gugu Slimefun dependency localizes that constant, which caused Chinese owner text to leak into the English addon.

The Dolly now owns the stable English prefix `&7Owner: ` and binds backpack PDC plus display lore through `Dolly#bindDollyItem`. Existing bound Dollies are migrated when loaded: the legacy final non-ID owner line is replaced with the English line while UUID and backpack ID lore remain intact.
