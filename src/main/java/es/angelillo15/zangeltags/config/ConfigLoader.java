package es.angelillo15.zangeltags.config;

import es.angelillo15.zangeltags.ZAngelTags;
import jdk.jfr.internal.tool.Main;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;

public class ConfigLoader {
    private ZAngelTags plugin;
    public static ConfigManager MainConfig;
    public static ConfigManager MessageConfig;
    public ConfigLoader(ZAngelTags plugin){
        this.plugin = plugin;
        loadMainConfig();
    }
    public void loadMainConfig(){
        MainConfig = new ConfigManager(plugin, "", "Config.yml");
        MainConfig.saveDefaultConfig();
        Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "&6Config succesfully reloaded"));
    }
    public void loadMessages(){
        MessageConfig = new ConfigManager(plugin, "", "messages.yml");
        MessageConfig.saveDefaultConfig();
        Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "&6Messages succesfully oaded"));

    }
    public FileConfiguration getMainConfig(){
        return MainConfig.getConfig();
    }
    public FileConfiguration getMessageConfig(){
        return MessageConfig.getConfig();

    }
    public void reloadMainConfig(){
        MainConfig.reloadConfig();

    }
    public void saveConfig(){
        MainConfig.saveConfig();
    }
    public void saveMessages(){
        MessageConfig.saveConfig();
    }
    public void reloadMessageConfig(){
        MessageConfig.reloadConfig();
    }
}
