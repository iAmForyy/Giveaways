package com.iamforyydev.giveaways.commands;

import com.iamforyydev.giveaways.Giveaways;
import com.iamforyydev.giveaways.giveaways.Minigame;
import com.iamforyydev.giveaways.manager.GameManager;
import com.iamforyydev.giveaways.utils.StringUtils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Arrays;

import static com.iamforyydev.giveaways.utils.StringUtils.c;

public class MainCommands implements CommandExecutor {

    private final Giveaways plugin = Giveaways.getInstance();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(!(sender instanceof Player)) return false;
        Player player = (Player) sender;
        if(args.length <= 0){
            player.sendMessage(c("&eThe argument is invalid, use /giveways help."));
            return true;
        }if(args[0].equalsIgnoreCase("start")){
            Minigame minigame = plugin.getMinigame();
            if(minigame == null) return true;
            if(minigame.isStarted()){
                player.sendMessage(c(plugin.getConfig().getString("Messages.is_started")));
                return true;
            }else {
                GameManager.startGame(minigame);
                player.sendTitle(c("&6&lGIVEAWAY"), c("&fThe giveaway has started!"));
                player.sendMessage(c("&eYou have started the giveaway!"));
                plugin.getConfig().getStringList("Messages.announce").forEach(StringUtils::sendBroadcast);
            }
        }else if(args[0].equalsIgnoreCase("join")){
            Minigame minigame = plugin.getMinigame();
            if(minigame == null) return true;
            if(minigame.isStarted()){
                if(minigame.containsPlayer(player)){
                    player.sendMessage(c(plugin.getConfig().getString("Messages.already_in_game")));
                    return true;
                }
                GameManager.joinGame(player, minigame, plugin);
            }else{
                player.sendMessage(c("&cThe raffle has not started yet!"));
            }
        }else if(args[0].equalsIgnoreCase("leave")){
            Minigame minigame = plugin.getMinigame();
            if(minigame == null) return true;
            if(minigame.isStarted()){
                if(!minigame.containsPlayer(player)){
                    player.sendMessage(c("&cYou must first enter the draw!"));
                    return true;
                }else{
                    GameManager.leavePlayer(player, minigame, plugin);
                }
            }else{
                player.sendMessage(c("&cThe raffle has not started yet!"));
            }
        }else if(args[0].equalsIgnoreCase("help")){
            Arrays.asList(
                    "",
                    "             &6&lGIVEAWAYS",
                    "",
                    "&fuse &e'/giveaways help' &fto receive the help menu!",
                    "&fuse &e'/giveaways join' &fto enter the draw!",
                    "&fuse &e'/giveaways leave' &fto get out of the draw!",
                    "&fuse &e'/giveaways start' &fto start a giveaway!",
                    ""
            ).forEach(string -> player.sendMessage(c(string)));
        }








        return true;
    }
}
