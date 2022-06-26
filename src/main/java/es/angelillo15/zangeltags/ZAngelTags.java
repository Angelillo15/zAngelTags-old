package es.angelillo15.zangeltags;

import es.angelillo15.zangeltags.cmd.MainCommand;
import es.angelillo15.zangeltags.config.ConfigLoader;
import es.angelillo15.zangeltags.database.PluginConnection;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

public final class ZAngelTags extends JavaPlugin {
    PluginDescriptionFile pdf = this.getDescription();
    public String version = pdf.getVersion();
    public String prefix = "&b「zAngelTags」";
    private PluginConnection connection;


    @Override
    public void onEnable() {

        // Plugin startup logic
        Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "&6&lzAngelTags » &r By Angelillo15"));
        Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "&e&l----------angelillo15.es---------"));
        Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "&e&l---------------"+version+"---------------"));
        dbConnection();
        registerCommands();
    }
    public ConfigLoader cl = new ConfigLoader(this);


    public void registerCommands(){
        this.getCommand("zAngelTags").setExecutor(new MainCommand(this));
        this.getCommand("zat").setExecutor(new MainCommand(this));
    }

    public void dbConnection(){
        FileConfiguration db = this.cl.getMainConfig();
        String host = db.getString("database.host");
        int port = Integer.parseInt(db.getString("database.host"));
        String database = db.getString("database.database");
        String user = db.getString("database.database");
        String password = db.getString("database.database");
        this.connection = new PluginConnection(host, port, database, user, password);
    }
    public void getConnection(){
        this.connection.getConection();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
