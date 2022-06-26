package es.angelillo15.zangeltags.cmd;

import es.angelillo15.zangeltags.ZAngelTags;
import es.angelillo15.zangeltags.msg.ConsolesMsg;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

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
            }

        }
        return false;
    }
}
