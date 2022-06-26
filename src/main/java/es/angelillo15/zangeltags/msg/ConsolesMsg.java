package es.angelillo15.zangeltags.msg;

import org.bukkit.Bukkit;

public class ConsolesMsg {
    public static void help(){
        Bukkit.getConsoleSender().sendMessage("&6----------------zAngelTags----------------");
        Bukkit.getConsoleSender().sendMessage("&bAvaible Commands:");
        Bukkit.getConsoleSender().sendMessage("&b zAngelTags reload ");
    }
}
