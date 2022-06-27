package es.angelillo15.zangeltags;

import es.angelillo15.zangeltags.bstats.Metrics;
import es.angelillo15.zangeltags.cmd.MainCommand;
import es.angelillo15.zangeltags.cmd.TabComplete;
import es.angelillo15.zangeltags.config.ConfigLoader;
import es.angelillo15.zangeltags.database.PluginConnection;
import es.angelillo15.zangeltags.database.SQLQuerys;
import es.angelillo15.zangeltags.events.TagInventoryClickEvent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.sql.Connection;
import java.util.UUID;

public final class ZAngelTags extends JavaPlugin {
    PluginDescriptionFile pdf = this.getDescription();
    public String version = pdf.getVersion();
    public String prefix = "&b「zAngelTags」";
    private PluginConnection connection;
    public ConfigLoader cl;
    //15601

    int pluginId = 15601;
    Metrics metrics = new Metrics(this, pluginId);

    @Override
    public void onEnable() {

        // Plugin startup logic
        Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "\n &bVersion: "+ version+" \n &b" +
                "███████╗ █████╗ ███╗   ██╗ ██████╗ ███████╗██╗  ████████╗ █████╗  ██████╗ ███████╗\n" +
                "╚══███╔╝██╔══██╗████╗  ██║██╔════╝ ██╔════╝██║  ╚══██╔══╝██╔══██╗██╔════╝ ██╔════╝\n" +
                "  ███╔╝ ███████║██╔██╗ ██║██║  ███╗█████╗  ██║     ██║   ███████║██║  ███╗███████╗\n" +
                " ███╔╝  ██╔══██║██║╚██╗██║██║   ██║██╔══╝  ██║     ██║   ██╔══██║██║   ██║╚════██║\n" +
                "███████╗██║  ██║██║ ╚████║╚██████╔╝███████╗███████╗██║   ██║  ██║╚██████╔╝███████║\n" +
                "╚══════╝╚═╝  ╚═╝╚═╝  ╚═══╝ ╚═════╝ ╚══════╝╚══════╝╚═╝   ╚═╝  ╚═╝ ╚═════╝ ╚══════╝")
        );
        cl = new ConfigLoader(this);
        dbConnection();
        registerCommands();
        registerPlaceholder();
        registerEvents();



    }



    public void registerCommands(){
        this.getCommand("zAngelTags").setExecutor(new MainCommand(this));
        this.getCommand("zat").setExecutor(new MainCommand(this));
        this.getCommand("zAngelTags").setTabCompleter(new TabComplete());
        this.getCommand("zat").setTabCompleter(new TabComplete());
        this.getCommand("tags").setExecutor(new MainCommand(this));
        this.getCommand("tags").setTabCompleter(new TabComplete());
    }

    public void dbConnection(){
        FileConfiguration db = this.cl.getMainConfig();
        String host = db.getString("database.host");
        int port = Integer.parseInt(db.getString("database.port"));
        String database = db.getString("database.database");
        String user = db.getString("database.user");
        String password = db.getString("database.password");
        this.connection = new PluginConnection(host, port, database, user, password);
        if(!SQLQuerys.tablesCreated(getConnection())){
            SQLQuerys.createTables(getConnection());
        }
    }
    public Connection getConnection(){
        return this.connection.getConection();

    }

    public void registerPlaceholder(){
        if(Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null){
            new PlaceHolderApiExtensions(this).register();
        }

    }
    public void registerEvents(){
        PluginManager pm = this.getServer().getPluginManager();
        pm.registerEvents(new TagInventoryClickEvent(this), this);
    }



    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
