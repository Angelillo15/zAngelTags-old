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
    public static ConfigManager TagsConfig;
    public ConfigLoader(ZAngelTags plugin){
        this.plugin = plugin;
        loadMainConfig();
        loadMessages();
        loadTags();
    }
    public void loadMainConfig(){
        MainConfig = new ConfigManager(plugin, "", "Config.yml");
        MainConfig.saveDefaultConfig();
        Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.prefix+"&6Config successfully loaded"));
    }
    public void loadMessages(){
        MessageConfig = new ConfigManager(plugin, "", "messages.yml");
        MessageConfig.saveDefaultConfig();
        Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.prefix+"&6Messages successfully loaded"));

    }
    public void loadTags(){
        TagsConfig = new ConfigManager(plugin, "", "tags.yml");
        TagsConfig.saveDefaultConfig();
        Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.prefix+"&6Tags successfully loaded"));

    }
    public FileConfiguration getMainConfig(){
        return MainConfig.getConfig();
    }
    public FileConfiguration getMessageConfig(){
        return MessageConfig.getConfig();
    }
    public FileConfiguration getTagsConfig(){
        return TagsConfig.getConfig();
    }
    public void reloadMainConfig(){
        MainConfig.reloadConfig();

    }
    public void reloadMessagesConfig(){
        MessageConfig.reloadConfig();
    }
    public void reloadTagsConfig(){
        TagsConfig.reloadConfig();
    }
    public void saveConfig(){
        MainConfig.saveConfig();
    }
    public void saveMessages(){
        MessageConfig.saveConfig();
    }
    public void saveTags(){
        TagsConfig.saveConfig();
    }
    public void reloadMessageConfig(){
        MessageConfig.reloadConfig();
    }
}
