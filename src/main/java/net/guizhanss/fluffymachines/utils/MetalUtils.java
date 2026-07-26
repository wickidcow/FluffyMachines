package net.guizhanss.fluffymachines.utils;

import net.guizhanss.guizhanlib.common.utils.StringUtil;

import java.util.Locale;

public final class MetalUtils {

    private MetalUtils() {
    }

    public static String getMetalName(String type) {
        return switch (type.toUpperCase(Locale.ROOT)) {
            case "IRON" -> "Iron";
            case "GOLD" -> "Gold";
            case "COPPER" -> "Copper";
            case "TIN" -> "Tin";
            case "SILVER" -> "Silver";
            case "LEAD" -> "Lead";
            case "ALUMINUM" -> "Aluminum";
            case "ZINC" -> "Zinc";
            case "MAGNESIUM" -> "Magnesium";
            default -> StringUtil.humanize(type);
        };
    }
}
