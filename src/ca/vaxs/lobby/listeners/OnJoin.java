package ca.vaxs.lobby.listeners;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import ca.vaxs.lobby.Main;
import ca.vaxs.lobby.utils.TitleManager;
import net.md_5.bungee.api.ChatColor;

public class OnJoin implements Listener {

	private Main main;
	
	public OnJoin(Main main) {
		this.main = main;
	}
	
	@EventHandler
	public void OnPlayerJoin(PlayerJoinEvent event) {
		//Auto Lobby
		Player player = event.getPlayer();
		double x= main.getConfig().getDouble("lobby.x");
		double y= main.getConfig().getDouble("lobby.y");
		double z= main.getConfig().getDouble("lobby.z");
		String world = main.getConfig().getString("lobby.world");
		Location lobby = new Location(player.getServer().getWorld(world), x, y, z);
		if(main.getConfig().getBoolean("lobby-on-join")) {
			player.teleport(lobby);
		}
		
		//Custom Join Message
		if(main.getConfig().getBoolean("remove-on-join-message")) {
			event.setJoinMessage("");
		}
		else {
			event.setJoinMessage(ChatColor.YELLOW + player.getName() + " joined the game");
		}
		if(main.getConfig().getBoolean("custom-message-enable")) {
			event.setJoinMessage(main.getConfig().getString("custom-message").replace("&", "§"));
		}
		
		//Title
		if(main.getConfig().getBoolean("title-enable"))
		{
			String title = main.getConfig().getString("title").replace("&", "§");
			String subtitle = main.getConfig().getString("title-subtitle").replace("&", "§");
			TitleManager.sendTitle(player, title, subtitle, (20 * 3));
		}
	}
	
}
