package es.angelillo15.zangeltags;

import es.angelillo15.zangeltags.cmd.MainCommand;
import es.angelillo15.zangeltags.config.ConfigLoader;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

public final class ZAngelTags extends JavaPlugin {
    PluginDescriptionFile pdf = this.getDescription();
    public String version = pdf.getVersion();
    public String prefix = "&b「zAngelTags」";

    @Override
    public void onEnable() {

        // Plugin startup logic
        Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "&6&lzAngelTags » &r By Angelillo15"));
        Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "&e&l----------angelillo15.es---------"));
        Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "&e&l---------------"+version+"---------------"));
        ConfigLoader cl = new ConfigLoader(this);
        registerCommands();
    }

    public void registerCommands(){
        this.getCommand("zAngelTags").setExecutor(new MainCommand(this));
        this.getCommand("zat").setExecutor(new MainCommand(this));
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
