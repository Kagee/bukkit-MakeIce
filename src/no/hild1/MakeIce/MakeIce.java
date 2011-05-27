package no.hild1.MakeIce;

import java.util.logging.Logger;

import org.bukkit.event.Event;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class MakeIce extends JavaPlugin {

	Logger log = Logger.getLogger("Minecraft");
	private final MakeIcePlayerListener blockListener = new MakeIcePlayerListener(this);
	
	public void onEnable(){ 

		PluginManager pm = getServer().getPluginManager();
		pm.registerEvent(Event.Type.PLAYER_INTERACT, blockListener, Event.Priority.Normal, this);

		PluginDescriptionFile pdfFile = this.getDescription();
		log.info( pdfFile.getName() + " version " + pdfFile.getVersion() + " has been loaded." );
	} 

	public void onDisable(){ 
		PluginDescriptionFile pdfFile = this.getDescription();
		log.info(pdfFile.getName() + " version " + pdfFile.getVersion() + " has been unloaded." );	 
	}

}
