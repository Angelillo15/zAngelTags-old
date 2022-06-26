package es.angelillo15.zangeltags.config;

import es.angelillo15.zangeltags.ZAngelTags;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;

public class ConfigLoader {
    private ZAngelTags plugin;
    public static ConfigManager MainConfig;
    public ConfigLoader(ZAngelTags plugin){
        this.plugin = plugin;
        loadMainConfig();
    }
    public void loadMainConfig(){
        MainConfig = new ConfigManager(plugin, "", "Config.yml");
        Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "&6Config succesfully reloaded"));
    }
    public FileConfiguration getMainConfig(){
        return MainConfig.getConfig();
    }
    public static void reloadMainConfig(){
        MainConfig.reloadConfig();
    }
}
