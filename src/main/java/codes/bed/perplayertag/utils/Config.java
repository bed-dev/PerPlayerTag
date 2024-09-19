package codes.bed.perplayertag.utils;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

import static codes.bed.perplayertag.PerPlayerTag.Instance;

@SuppressWarnings({"unused"})
public class Config {

    private static FileConfiguration config;
    private static File configFile;

    public static void init() {
        configFile = new File(Instance.getDataFolder(), "config.yml");
        if (!configFile.exists()) {
            Instance.saveResource("config.yml", false);
        }
        config = YamlConfiguration.loadConfiguration(configFile);
    }

    public static FileConfiguration getConfig() {
        return config;
    }

    public static void reloadConfig() {
        config = YamlConfiguration.loadConfiguration(configFile);
    }

    public static void saveConfig() {
        try {
            config.save(configFile);
        } catch (IOException e) {
            Logger.severe("Could not save config file" + e);
        }
    }

    public static void set(String path, Object value) {
        config.set(path, value);
        saveConfig();
    }

    public static Object get(String path) {
        if (!config.contains(path)) {
            Logger.warning("Configuration key not found: " + path);
            return null;
        }
        return config.get(path);
    }

    public static boolean contains(String path) {
        return config.contains(path);
    }

    public static String String(String path, String defaultValue) {
        if (!config.contains(path)) {
            Logger.warning("Configuration key not found: " + path);
            return defaultValue;
        }
        return config.getString(path, defaultValue);
    }

    public static int Int(String path, int defaultValue) {
        if (!config.contains(path)) {
            Logger.warning("Configuration key not found: " + path);
            return defaultValue;
        }
        return config.getInt(path, defaultValue);
    }

    public static boolean Boolean(String path, boolean defaultValue) {
        if (!config.contains(path)) {
            Logger.warning("Configuration key not found: " + path);
            return defaultValue;
        }
        return config.getBoolean(path, defaultValue);
    }
    public static List<?> List(String path, List<?> defaultValue) {
        if (!config.contains(path)) {
            Logger.warning("Configuration key not found: " + path);
            return defaultValue;
        }
        return config.getList(path, defaultValue);
    }

    @SuppressWarnings("ResultOfMethodCallIgnored")
    public static void load(String path) {
        File file = new File(Instance.getDataFolder(), path);
        if (!file.isFile()) {
            file.mkdirs();
            for(String p : Objects.requireNonNull(file.list())){
                load(p);
            }
        } else {
            Instance.saveResource(path, false);
        }

    }


}
