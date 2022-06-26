package es.angelillo15.zangeltags;

public class PluginReload {
    private ZAngelTags plugin;
    public PluginReload(ZAngelTags plugin){
        this.plugin = plugin;

    }
    public void reloadConfig(){
        plugin.cl.reloadMainConfig();
        plugin.cl.reloadMessageConfig();
    }

}
