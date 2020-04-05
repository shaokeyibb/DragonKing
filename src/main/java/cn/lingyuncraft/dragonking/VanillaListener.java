package cn.lingyuncraft.dragonking;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.util.UUID;

public class VanillaListener implements Listener {
    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent e) {
        UUID uuid = e.getPlayer().getUniqueId();
        Data.DATA.put(uuid, Data.DATA.getOrDefault(uuid, 0) + 1);
    }
}
