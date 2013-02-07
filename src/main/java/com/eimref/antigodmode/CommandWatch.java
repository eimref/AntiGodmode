package com.eimref.antigodmode;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Handler for the /watch command.
 * @author Eimref
 */
public class CommandWatch implements CommandExecutor {
	private final AntiGodmode plugin;
	
    public CommandWatch(AntiGodmode plugin) {
        this.plugin = plugin;
    }
	
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) return false;
        //TODO Add command to watch certain players more closely
        return false;
    }
}
