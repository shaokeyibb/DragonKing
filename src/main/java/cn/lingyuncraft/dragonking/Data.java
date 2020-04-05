package cn.lingyuncraft.dragonking;

import lombok.NonNull;
import org.bukkit.configuration.ConfigurationSection;
import org.serverct.parrot.parrotx.PPlugin;
import org.serverct.parrot.parrotx.config.PConfig;
import org.serverct.parrot.parrotx.data.flags.Timestamp;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class Data extends PConfig implements Timestamp {

    public static long LAST_TIME;
    public static long CHECK_TIME;
    public static int SAVED_TIME;
    public static Map<UUID, Integer> DATA = new HashMap<>();

    public Data(@NonNull PPlugin plugin) {
        super(plugin, "config", "主配置文件");
    }

    @Override
    public void save() {
        ConfigurationSection dataConfig = config.createSection("data");
        DATA.forEach((uuid, amount) -> dataConfig.set(uuid.toString(), amount));
        super.save();
    }

    @Override
    public void saveDefault() {
        plugin.saveDefaultConfig();
    }

    @Override
    public void load(@NonNull File file) {
        CHECK_TIME = config.getLong("check_time");
        LAST_TIME = config.getLong("lastTime");
        SAVED_TIME = config.getInt("saved_time");
        // 如果 最后检查时间 + 检查间隔 小于 当前时间，代表已超时。
        if (LAST_TIME + CHECK_TIME < System.currentTimeMillis()) {
            // 若已超时，设置最后更新时间为当前时间，同时不再加载 data 数据。
            LAST_TIME = System.currentTimeMillis();
            return;
        }
        config.createSection("data").getKeys(false).forEach(s -> DATA.put(UUID.fromString(s), config.getInt("data." + s)));
    }

    @Override
    public long getTimestamp() {
        return LAST_TIME;
    }

    @Override
    public void setTime(long time) {
        LAST_TIME = time;
    }
}
