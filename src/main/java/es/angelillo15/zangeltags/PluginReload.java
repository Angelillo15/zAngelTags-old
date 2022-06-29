package es.angelillo15.zangeltags;

public class PluginReload {
    private ZAngelTags plugin;
    public PluginReload(ZAngelTags plugin){
        this.plugin = plugin;
        reloadConfig();

    }
    public void reloadConfig(){
        plugin.cl.reloadMainConfig();
        plugin.cl.reloadMessagesConfig();
        plugin.cl.reloadTagsConfig();
        plugin.cl.GuiConfig.reloadConfig();
    }

}
