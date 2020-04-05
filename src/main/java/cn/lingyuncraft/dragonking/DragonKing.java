package cn.lingyuncraft.dragonking;

import org.bukkit.Bukkit;
import org.serverct.parrot.parrotx.PPlugin;

public final class DragonKing extends PPlugin {

    @Override
    public void onEnable() {
        super.onEnable();
    }

    @Override
    public void onDisable() {
        PlaceHolder.unhook();
        pConfig.save();
        super.onDisable();
    }

    @Override
    protected void registerListener() {
        Bukkit.getPluginManager().registerEvents(new VanillaListener(), this);
    }

    @Override
    protected void load() {
        pConfig = new Data(this);
        pConfig.init();
        PlaceHolder.hook();
        new AutoRefresher();
    }
}
