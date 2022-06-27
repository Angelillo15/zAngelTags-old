package es.angelillo15.zangeltags.msg;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;

public class ConsolesMsg {
    public static void help(){
        Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "\n" +
                "&6----------------zAngelTags---------------- \n" +
                "&bAvailable Commands: \n" +
                "&bzAngelTags reload &8&l» &f To reload the plugin"
        ));
    }
}
