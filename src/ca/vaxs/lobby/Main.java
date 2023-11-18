package ca.vaxs.lobby;

import org.bukkit.plugin.java.JavaPlugin;

import ca.vaxs.lobby.commands.LobbyCommand;
import ca.vaxs.lobby.listeners.OnJoin;

public class Main extends JavaPlugin {
	
	@Override
	public void onEnable() {
		
		//Create config file
		saveDefaultConfig();
		
		System.out.println("[Better Lobby]: Plugin loaded...");
		getCommand("setlobby").setExecutor(new LobbyCommand(this));
		getCommand("lobby").setExecutor(new LobbyCommand(this));
		getServer().getPluginManager().registerEvents(new OnJoin(this), this);
	}
	
}
