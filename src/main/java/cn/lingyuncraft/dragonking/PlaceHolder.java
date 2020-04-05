package cn.lingyuncraft.dragonking;

import me.clip.placeholderapi.PlaceholderAPI;
import me.clip.placeholderapi.PlaceholderHook;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class PlaceHolder extends PlaceholderHook {
    private static final String HOOK_NAME = "DragonKing";

    public static void hook() {
        PlaceholderAPI.registerPlaceholderHook(HOOK_NAME, new PlaceHolder());
    }

    public static void unhook() {
        PlaceholderAPI.unregisterPlaceholderHook(HOOK_NAME);
    }

    @Override
    public String onPlaceholderRequest(Player player, String identifier) {
        if (player == null) {
            return "";
        }
        if (identifier.equals("is")) {
            if (player.getUniqueId() == Top.get().getTop(1)) {
                return " §2[龙王]";
            }
        }
        if (identifier.equals("current")) {
            return Bukkit.getOfflinePlayer(Top.get().getTop(1)).getName();
        }
        return null;
    }
}
