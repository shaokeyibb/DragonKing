package cn.lingyuncraft.dragonking;

import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class VanillaListener implements Listener {

    public void onPlayerChat(AsyncPlayerChatEvent e){
        if (!Data.data.containsKey(e.getPlayer().getUniqueId())){
            Data.data.put(e.getPlayer().getUniqueId(),0);
        }
        Data.data.put(e.getPlayer().getUniqueId(),Data.data.get(e.getPlayer().getUniqueId())+1);
    }
}
