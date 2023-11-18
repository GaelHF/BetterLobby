package ca.vaxs.lobby.commands;

import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import ca.vaxs.lobby.Main;

public class LobbyCommand implements CommandExecutor {

	private Main main;
	
	public LobbyCommand(Main main) {
		this.main = main;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(sender instanceof Player)
		{
			Player player = (Player)sender;
			
			if(cmd.getName().equalsIgnoreCase("setlobby"))
			{
				double x = player.getLocation().getX();
				double y = player.getLocation().getY();
				double z = player.getLocation().getZ();
				String world = player.getWorld().getName();
				
				main.getConfig().set("lobby.x", x);
				main.getConfig().set("lobby.y", y);
				main.getConfig().set("lobby.z", z);
				main.getConfig().set("lobby.world", world);
				String confirm_message = "&7[&bBetter Lobby&7] &e&nLobby&6 is setup !";
				player.sendMessage(confirm_message.replace("&", "§"));
				return true;
			}
			if(cmd.getName().equalsIgnoreCase("lobby"))
			{
				double x = main.getConfig().getDouble("lobby.x");
				double y = main.getConfig().getDouble("lobby.y");
				double z = main.getConfig().getDouble("lobby.z");
				String world = main.getConfig().getString("lobby.world");
				Location lobby = new Location(player.getServer().getWorld(world), x, y, z);
				
				player.teleport(lobby);
				String confirm_message = "&7[&bBetter Lobby&7] &6You are at the &e&nlobby&6 !";
				player.sendMessage(confirm_message.replace("&", "§"));
				return true;
			}
			return false;
			
		}
		return false;
	}

}
