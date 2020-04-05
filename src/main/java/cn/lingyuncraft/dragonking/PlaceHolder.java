package cn.lingyuncraft.dragonking;

import me.clip.placeholderapi.PlaceholderAPI;
import me.clip.placeholderapi.PlaceholderHook;
import org.bukkit.entity.Player;

public class PlaceHolder extends PlaceholderHook {
    private static final String hook_name = "DragonKing";

    public static void hook() {
        PlaceholderAPI.registerPlaceholderHook(hook_name, new PlaceHolder());
    }

    public static void unhook() {
        PlaceholderAPI.unregisterPlaceholderHook(hook_name);
    }

    @Override
    public String onPlaceholderRequest(Player player, String identifier) {
        if (player==null){
            return "";
        }
        if (identifier.equals("is")){
            if (player.getUniqueId()==Top.get().getTop(1)){
                return " §2[龙王]";
            }
        }
        if (identifier.equals("current")){
            Top.get().getTop(1);
        }
        return null;
    }
}
