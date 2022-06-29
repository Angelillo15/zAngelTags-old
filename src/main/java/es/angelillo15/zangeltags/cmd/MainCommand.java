package es.angelillo15.zangeltags.cmd;

import es.angelillo15.zangeltags.PluginReload;
import es.angelillo15.zangeltags.ZAngelTags;
import es.angelillo15.zangeltags.config.ConfigLoader;
import es.angelillo15.zangeltags.config.ConfigManager;
import es.angelillo15.zangeltags.database.SQLQuerys;
import es.angelillo15.zangeltags.gui.TagsGui;
import es.angelillo15.zangeltags.msg.ConsolesMsg;
import es.angelillo15.zangeltags.msg.PlayersMsg;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class MainCommand implements CommandExecutor {
    private ZAngelTags plugin;
    public MainCommand(ZAngelTags plugin) {
        this.plugin = plugin;
    }
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(!(sender instanceof Player)){
            if(args.length < 1){
                ConsolesMsg.help();
                return true;
            }else{
                if(args[0].equals("reload")){
                    PluginReload pr = new PluginReload(plugin);
                    return true;
                }



                ConsolesMsg.help();
            }


        }else{
            FileConfiguration messages = plugin.cl.getMessageConfig();
            Player p = (Player) sender;
            String noPerm = messages.getString("Messages.noPerm");
            if(args.length < 1){
                TagsGui tg = new TagsGui(plugin, p);
                return true;
            }else{
                if (args[0].equalsIgnoreCase("Reload")) {
                    if(p.hasPermission("zAngelTags.reload")){
                        PluginReload pr = new PluginReload(plugin);
                        p.sendMessage(ChatColor.translateAlternateColorCodes('&', messages.getString("Messages.reloadMessage")));
                        return true;
                    }else {
                        p.sendMessage(ChatColor.translateAlternateColorCodes('&', noPerm));
                    }

                }

                if(args[0].equalsIgnoreCase("gui")){
                    if(p.hasPermission("zAngelTags.gui")){
                        TagsGui tg = new TagsGui(plugin, p);
                        return true;
                    }else {
                        p.sendMessage(ChatColor.translateAlternateColorCodes('&', noPerm));
                        return true;
                    }
                }
                if(args[0].equalsIgnoreCase("tag") && args.length < 2){
                    help(p);
                    return true;
                }
                if(p.hasPermission("zAngelTags.tag")){
                    if(args[0].equalsIgnoreCase("tag") && (args[1].equalsIgnoreCase("list"))){
                        FileConfiguration tags = plugin.cl.getTagsConfig();
                        FileConfiguration gui = plugin.cl.GuiConfig.getConfig();

                        Set<String> tagsArray = tags.getConfigurationSection("Tags").getKeys(false);
                        for(String s : tagsArray){
                            /*
                            p.sendMessage(ChatColor.translateAlternateColorCodes('&',"&b----------------------------------------" ));
                            p.sendMessage(ChatColor.translateAlternateColorCodes('&',"&bTag name: &6" +tags.getString("Tags."+s+ ".name")));
                            p.sendMessage(ChatColor.translateAlternateColorCodes('&',"&bTag display name: &6" +tags.getString("Tags."+s+ ".inGameTag")));
                            p.sendMessage(ChatColor.translateAlternateColorCodes('&',"&bTag permission: &6" +tags.getString("Tags."+s+ ".permission")));
                            p.sendMessage(ChatColor.translateAlternateColorCodes('&',"&b----------------------------------------"));
                            */
                            List<String> mensaje = (List<String>) gui.getList("Messages.GuiLore");
                            for (String c : mensaje){
                                p.sendMessage(ChatColor.translateAlternateColorCodes('&', c
                                        .replace("{tag_name}", tags.getString("Tags."+s+ ".name"))
                                        .replace("{tag_displayName}", tags.getString("Tags."+s+ ".inGameTag"))
                                        .replace("{tag_perm}", tags.getString("Tags."+s+ ".permission"))));
                            }
                        }
                        return true;

                    }else if(args[0].equalsIgnoreCase("tag") && (args[1].equalsIgnoreCase("set"))){
                        if(args.length < 3){
                            p.sendMessage(ChatColor.translateAlternateColorCodes('&', messages.getString("Messages.noTagSelected")));
                            return true;
                        }else{
                            FileConfiguration tags = plugin.cl.getTagsConfig();
                            Set<String> tagsArray = tags.getConfigurationSection("Tags").getKeys(false);

                            if(tags.contains("Tags."+args[2].toLowerCase())){
                                if(p.hasPermission(tags.getString("Tags."+args[2].toLowerCase()+ ".permission")) || p.hasPermission("zAngelTags.tags.all")){
                                    p.sendMessage(ChatColor.translateAlternateColorCodes('&', messages.getString("Messages.SelectedTag").replace("{tag}", tags.getString("Tags."+args[2].toLowerCase()+".inGameTag" ))));
                                    if(SQLQuerys.playerInDB(plugin.getConnection(), p.getUniqueId())){
                                        SQLQuerys.updateData(plugin.getConnection(), p.getUniqueId(), tags.getString("Tags."+args[2].toLowerCase()+".inGameTag"));
                                    }else {
                                        SQLQuerys.insertData(plugin.getConnection(), p.getUniqueId(), tags.getString("Tags."+args[2].toLowerCase()+".inGameTag"));
                                    }
                                    return true;
                                }else{
                                    p.sendMessage(ChatColor.translateAlternateColorCodes('&', noPerm));
                                    return true;
                                }


                            }else {
                                p.sendMessage(ChatColor.translateAlternateColorCodes('&', messages.getString("Messages.dontExist")));
                                return true;
                            }

                        }
                    }else if(args[0].equalsIgnoreCase("tag") && (args[1].equalsIgnoreCase("get"))){
                        p.sendMessage(ChatColor.translateAlternateColorCodes('&', messages.getString("Messages.ActualTag").replace("{tag}", SQLQuerys.getTag(plugin.getConnection(), p.getUniqueId()))));
                        return true;
                    }else if(args[0].equalsIgnoreCase("tag") && (args[1].equalsIgnoreCase("disable"))){
                        p.sendMessage(ChatColor.translateAlternateColorCodes('&', messages.getString("Messages.DisableTag")));
                        SQLQuerys.updateData(plugin.getConnection(), p.getUniqueId(), "");
                        return true;
                    }else {
                        help(p);
                        return true;
                    }
                }else{
                    p.sendMessage(ChatColor.translateAlternateColorCodes('&', noPerm));
                    return true;
                }

            }
        }
            return false;
    }



    public void help(Player p){
        p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6----------------zAngelTags----------------"));
        p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&bAvailable Commands:"));
        p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&bzAngelTags reload &8&l» &f To reload the plugin"));
        p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&bzAngelTags tag get &8&l» &f Return your selected"));
        p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&bzAngelTags tag list &8&l» &f list all the tags"));
        p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&bzAngelTags tag set <tag> &8&l» &f set a tag"));
        p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&bzAngelTags gui &8&l» &f open plugin gui "));
        p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&bzAngelTags tag disable &8&l» &f open plugin gui "));
        p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&bzAngelTags help &8&l» &f view the help of the plugin "));

    }
}
