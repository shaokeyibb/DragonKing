package cn.lingyuncraft.dragonking;

import lombok.NonNull;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.YamlConfiguration;
import org.serverct.parrot.parrotx.PPlugin;
import org.serverct.parrot.parrotx.config.PConfig;
import org.serverct.parrot.parrotx.data.flags.Timestamp;

import java.io.File;
import java.util.Map;
import java.util.UUID;

public class Data extends PConfig implements Timestamp {

    public long time;

    public static long lastTime;
    public static long CHECK_TIME;
    public static int SAVED_TIME;

    public static Map<UUID, Integer> data;

    public Data(@NonNull PPlugin plugin) {
        super(plugin, "config", "主配置文件");
    }

    @Override
    public void saveDefault() {
        plugin.saveDefaultConfig();
    }

    public static void saveData() {
        ConfigurationSection dataConfig = DragonKing.getInstance().getConfig().createSection("data");
        data.forEach((k,v)->dataConfig.set(k.toString(),v));
    }

    @Override
    public void load(@NonNull File file) {
        CHECK_TIME = this.config.getLong("check_time");
        lastTime = this.config.getLong("lastTime");
        if (lastTime+CHECK_TIME<time){
            lastTime = getTimestamp();
            init();
            return;
        }
        SAVED_TIME = this.config.getInt("saved_time");
        this.config.createSection("data").getKeys(false).forEach(s -> data.put(UUID.fromString(s), this.config.getInt("data." + s)));
    }

    @Override
    public long getTimestamp() {
        return time;
    }

    @Override
    public void setTime(long time) {
        time = this.time;
    }
}
