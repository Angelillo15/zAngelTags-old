package es.angelillo15.zangeltags.events;

import es.angelillo15.zangeltags.ZAngelTags;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class TagInventoryClickEvent implements Listener {
    private ZAngelTags plugin;
    public TagInventoryClickEvent(ZAngelTags plugin){
        this.plugin = plugin;
    }
    @EventHandler
    public void clickEvent(InventoryClickEvent e){
        String titleConfig = ChatColor.stripColor(ChatColor.translateAlternateColorCodes('&',plugin.cl.getMainConfig().getString("Gui.Tittle")));
        String titleStrip = ChatColor.stripColor(e.getView().getTitle());

        if(titleStrip.toLowerCase().startsWith(titleConfig.toLowerCase())){
            if(e.getCurrentItem() == null || e.getSlotType() == null || e.getCurrentItem().getType() == Material.AIR){
                e.setCancelled(true);
                return;
            }else{
                String close = ChatColor.translateAlternateColorCodes('&', plugin.cl.getMainConfig().getString("Gui.BarrierCloseName"));
                String DisableTag = ChatColor.translateAlternateColorCodes('&', plugin.cl.getMainConfig().getString("Gui.DisableTag"));
                if(e.getCurrentItem().getItemMeta().getDisplayName().equals(close)){
                    e.getWhoClicked().closeInventory();
                    e.setCancelled(true);
                    return;
                }
                if(e.getCurrentItem().getItemMeta().getDisplayName().equals(DisableTag)){

                }
                FileConfiguration tags = plugin.cl.getTagsConfig();
                Set<String> tagsArray = tags.getConfigurationSection("Tags").getKeys(false);
                List<String> tagsArraya = new ArrayList<>(tagsArray);
                Player p = (Player) e.getWhoClicked();
                p.performCommand("zat tag set "+tagsArraya.get(e.getSlot()));
                p.closeInventory();


                e.setCancelled(true);
            }
        }


    }
}
