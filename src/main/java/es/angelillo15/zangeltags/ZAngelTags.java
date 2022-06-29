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

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Connection;
import java.util.UUID;

public final class ZAngelTags extends JavaPlugin {
    PluginDescriptionFile pdf = this.getDescription();
    public String version = pdf.getVersion();
    public String latestversion;

    public String prefix = "&b「zAngelTags」";
    private PluginConnection connection;
    public ConfigLoader cl;
    //15601

    int pluginId = 15601;
    Metrics metrics = new Metrics(this, pluginId);

    @Override
    public void onEnable() {

        // Plugin startup logic
        Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "\n &bVersion: "+ version+" \n " +
                "███████╗ █████╗ ███╗   ██╗ ██████╗ ███████╗██╗  ████████╗ █████╗  ██████╗ ███████╗\n" +
                "╚══███╔╝██╔══██╗████╗  ██║██╔════╝ ██╔════╝██║  ╚══██╔══╝██╔══██╗██╔════╝ ██╔════╝\n" +
                "  ███╔╝ ███████║██╔██╗ ██║██║  ███╗█████╗  ██║     ██║   ███████║██║  ███╗███████╗\n" +
                " ███╔╝  ██╔══██║██║╚██╗██║██║   ██║██╔══╝  ██║     ██║   ██╔══██║██║   ██║╚════██║\n" +
                "███████╗██║  ██║██║ ╚████║╚██████╔╝███████╗███████╗██║   ██║  ██║╚██████╔╝███████║\n" +
                "╚══════╝╚═╝  ╚═╝╚═╝  ╚═══╝ ╚═════╝ ╚══════╝╚══════╝╚═╝   ╚═╝  ╚═╝ ╚═════╝ ╚══════╝")
        );
        cl = new ConfigLoader(this);
        updateChecker();
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
    public void updateChecker(){
        try {
            HttpURLConnection con = (HttpURLConnection) new URL(
                    "https://api.spigotmc.org/legacy/update.php?resource=102952").openConnection();
            int timed_out = 1250;
            con.setConnectTimeout(timed_out);
            con.setReadTimeout(timed_out);
            latestversion = new BufferedReader(new InputStreamReader(con.getInputStream())).readLine();
            if (latestversion.length() <= 7) {
                if(!version.equals(latestversion)){
                    Bukkit.getConsoleSender().sendMessage("");
                    Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', prefix +"&aThere is a new update of the plugin"));
                    Bukkit.getConsoleSender().sendMessage(ChatColor.AQUA+"「zAngelTags」"+ChatColor.RED+"You can download it at: "+ChatColor.WHITE+"https://www.spigotmc.org/resources/102952/");
                }
            }
        } catch (Exception ex) {
            Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', prefix+"&4&lERROR CHECKING UPDATES"));
        }
    }




    @Override
    public void onDisable() {
        SQLQuerys.CloseConnection(getConnection());
        Bukkit.getConsoleSender().sendMessage(net.md_5.bungee.api.ChatColor.translateAlternateColorCodes('&', this.prefix + "&6Connection closed"));
    }
}
