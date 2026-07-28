# Auto Armor Forge recipe fix

## Problem

The shared Auto Crafter matcher rejected every stackable ingredient slot whose amount was exactly one. A normal Armor Forge pattern made with one Glowstone ingredient per occupied slot therefore never ran, even though it matched a registered `RecipeType.ARMOR_FORGE` recipe.

## Fix

Version `26.2.8-legacy-english` supports two safe input states:

- **One-shot recipe:** exactly one stackable ingredient in every occupied recipe slot. The machine crafts once and consumes the pattern.
- **Buffered/template recipe:** at least two stackable ingredients in every occupied recipe slot. The machine crafts while retaining the final item in each slot as the cargo template.

A partially refilled retained template does not craft until every occupied stackable slot is buffered again. This prevents the recipe layout from being accidentally consumed. Clearing the input grid or toggling the machine from disabled to enabled re-arms one-shot crafting.

## Scope

The repair is implemented in `AutoCrafter`, so it applies consistently to Auto Armor Forge and the other recipe-based FluffyMachines auto-crafters. Recipe IDs, item IDs, energy cost, output handling, and registration are unchanged.
