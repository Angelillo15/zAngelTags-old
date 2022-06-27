package es.angelillo15.zangeltags.gui;

import es.angelillo15.zangeltags.ZAngelTags;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Set;

public class TagsGui {
    private ZAngelTags plugin;
    private Player p;
    public TagsGui(ZAngelTags plugin, Player p){
        this.plugin = plugin;
        this.p = p;
        CreateGUI();
    }

    public void CreateGUI(){
        FileConfiguration tags = plugin.cl.getTagsConfig();
        Set<String> tagsArray = tags.getConfigurationSection("Tags").getKeys(false);
        FileConfiguration config = plugin.cl.getMainConfig();

        String Title = config.getString("Gui.Tittle");

        Material material = Material.valueOf(config.getString("Gui.TagItemMaterial"));

        String BarrierCloseName = config.getString("Gui.BarrierCloseName");

        String DisableName = config.getString("Gui.DisableTag");

        Inventory tagGui = Bukkit.createInventory(null, 54, ChatColor.translateAlternateColorCodes('&', Title));

        int slots = 1;
        for(String s : tagsArray){
            if(slots <= 46){

                ItemStack item = new ItemStack(material);



                ItemMeta meta = item.getItemMeta();
                ArrayList<String> list = new ArrayList<>();
                list.add(ChatColor.translateAlternateColorCodes('&',"&b----------------------------------------"));
                list.add(ChatColor.translateAlternateColorCodes('&',"&bTag name: &6" +tags.getString("Tags."+s+ ".name")));
                list.add(ChatColor.translateAlternateColorCodes('&',"&bTag display name: &6" +tags.getString("Tags."+s+ ".inGameTag")));
                list.add(ChatColor.translateAlternateColorCodes('&',"&bTag permission: &6" +tags.getString("Tags."+s+ ".permission")));
                list.add(ChatColor.translateAlternateColorCodes('&',"&b----------------------------------------"));
                meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', tags.getString("Tags."+s+ ".inGameTag")));
                meta.setLore(list);
                item.setItemMeta(meta);

                tagGui.addItem(item);
            }
            slots++;

        }
        ItemStack barrier  = new ItemStack(Material.BARRIER);
        ItemMeta barrierMeta = barrier.getItemMeta();
        barrierMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', BarrierCloseName));
        barrier.setItemMeta(barrierMeta);
        tagGui.setItem(49, barrier);

        ItemStack disable  = new ItemStack(Material.NAME_TAG);
        ItemMeta disableMeta = barrier.getItemMeta();
        disableMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', DisableName));
        disable.setItemMeta(disableMeta);
        tagGui.setItem(53, disable);


        p.openInventory(tagGui);

    }
}
