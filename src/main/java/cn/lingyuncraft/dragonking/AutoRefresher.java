package cn.lingyuncraft.dragonking;

import org.bukkit.scheduler.BukkitRunnable;

public class AutoRefresher {

    public AutoRefresher() {
        new BukkitRunnable() {

            @Override
            public void run() {
                Top.refresh();
            }
        }.runTaskTimerAsynchronously(DragonKing.getInstance(), 0, Data.CHECK_TIME);
        new BukkitRunnable() {

            @Override
            public void run() {
                DragonKing.getInstance().pConfig.save();
            }
        }.runTaskTimerAsynchronously(DragonKing.getInstance(), 0, Data.SAVED_TIME);
    }

}
