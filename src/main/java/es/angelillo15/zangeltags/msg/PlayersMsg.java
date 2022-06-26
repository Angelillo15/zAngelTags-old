package es.angelillo15.zangeltags.msg;

import es.angelillo15.zangeltags.ZAngelTags;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.ChatMessageType;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

public class PlayersMsg {
    private ZAngelTags plugin;
    private Player p;
    public PlayersMsg(ZAngelTags plugin, Player p){
        this.plugin = plugin;
        this.p = p;
    }
    FileConfiguration m = plugin.cl.getMessageConfig();
    public void sendHelpMessage(){
        p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6----------------zAngelTags----------------"));
        p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&bAvaible Commands:"));
        p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&b zAngelTags reload"));
    }
    public void reloadMessage(){
        String reloadMessage = m.getString("Messages.reloadMessage");
        p.sendMessage(ChatColor.translateAlternateColorCodes('&', reloadMessage));

    }
}
