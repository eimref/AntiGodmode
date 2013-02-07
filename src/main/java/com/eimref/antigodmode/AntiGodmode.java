package com.eimref.antigodmode;

import java.util.List;

import org.bukkit.entity.Player;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Anti-Godmode for Bukkit
 *
 * @author Eimref
 */

public class AntiGodmode extends JavaPlugin {
    private final PlayerListener playerListener = new PlayerListener(this);
    
    @Override
    public void onDisable() {
    	 PluginDescriptionFile pdfFile = this.getDescription();
    	 getLogger().info( pdfFile.getName() + " version " + pdfFile.getVersion() + " now disabled." );
    }

    @Override
    public void onEnable() {
        // Register Events
        PluginManager pm = getServer().getPluginManager();
        pm.registerEvents(playerListener, this);
        
        // Ensure everything is working as it should
        PluginDescriptionFile pdfFile = this.getDescription();
        getLogger().info( pdfFile.getName() + " version " + pdfFile.getVersion() + " is now enabled." );
        
    }
    
    public void setMetadata(Player player, String key, Object value){
    	  player.setMetadata(key,new FixedMetadataValue(this, value));
    }
    
	public Object getMetadata(Player player, String key){
	  List<MetadataValue> values = player.getMetadata(key);
	  
	  for(MetadataValue value : values){
	     if(value.getOwningPlugin().getDescription().getName().equals(this.getDescription().getName())){
	        return value.value();
	     }
	  }
	  
	  return null;
	}
}
