package bukkit.anfanzer.hc;

import org.bukkit.Material;

/**
 * HungerControl foods list:
 *
 * @author Anfanzer
 */
public enum FoodType
{
    APPLE(Material.APPLE, "APPLE_HUNGER"),
    BAKED_POTATO(Material.BAKED_POTATO, "BAKED_POTATO_HUNGER"),
    BREAD(Material.BREAD, "BREAD_HUNGER"),
    CARROT(Material.CARROT, "CARROT_HUNGER"),
    CAKE(Material.CAKE_BLOCK, "CAKE_HUNGER"),
    COOKED_CHICKEN(Material.COOKED_CHICKEN, "COOKED_CHICKEN_HUNGER"),
    COOKED_FISH(Material.COOKED_FISH, "COOKED_FISH_HUNGER"),
    COOKED_PORKCHOP(Material.GRILLED_PORK, "COOKED_PORKCHOP_HUNGER"),
    COOKIE(Material.COOKIE, "COOKIE_HUNGER"),
    GOLDEN_APPLE(Material.GOLDEN_APPLE, "GOLDEN_APPLE_HUNGER"),
    GOLDEN_CARROT(Material.GOLDEN_CARROT, "GOLDEN_CARROT_HUNGER"),
    MELON(Material.MELON, "MELON_HUNGER"),
    MUSHROOM_STEW(Material.MUSHROOM_SOUP, "MUSHROOM_STEW_HUNGER"),
    POISONOUS_POTATO(Material.POISONOUS_POTATO, "POISONOUS_POTATO_HUNGER"),
    POTATO(Material.POTATO, "POTATO_HUNGER"),
    PUMPKIN_PIE(Material.PUMPKIN_PIE, "PUMPKIN_PIE_HUNGER"),
    RAW_BEEF(Material.RAW_BEEF, "RAW_BEEF_HUNGER"),
    RAW_CHICKEN(Material.RAW_CHICKEN, "RAW_CHICKEN_HUNGER"),
    RAW_FISH(Material.RAW_FISH, "RAW_FISH_HUNGER"),
    RAW_PORKCHOP(Material.PORK, "RAW_PORKCHOP_HUNGER"),
    ROTTEN_FLESH(Material.ROTTEN_FLESH, "ROTTEN_FLESH_HUNGER"),
    SPIDER_EYE(Material.SPIDER_EYE, "SPIDER_EYE_HUNGER"),
    STEAK(Material.COOKED_BEEF, "STEAK_HUNGER");

    private Material material;
    private String string;

    private FoodType(Material materialValue, String stringValue)
    {
        material = materialValue;
        string = stringValue;
    }

    public Material getMaterialValue()
    {
        return material;
    }

    public String getStringValue()
    {
        return string;
    }
}
