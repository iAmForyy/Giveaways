package com.iamforyydev.giveaways.commands;

import com.iamforyydev.giveaways.Giveaways;
import com.iamforyydev.giveaways.giveaways.Minigame;
import com.iamforyydev.giveaways.manager.GameManager;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
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
                player.sendMessage(c("&cUps! El juego ya ha sido iniciado!"));
                return true;
            }else {
                GameManager.startGame(minigame);
                player.sendMessage(c("&eHas iniciado el sorteo para el minigame "+minigame.getName()));
                Arrays.asList(
                        "                    &6&lGIVEAWAY",
                        "&eThe giveaway is about to start, ",
                        "&ejoin to win great rewards!",
                        "",
                        "Reward: &61,000 Coins",
                        ""
                ).forEach(s -> Bukkit.broadcastMessage(c(s)));
            }
        }else if(args[0].equalsIgnoreCase("join")){
            Minigame minigame = plugin.getMinigame();
            if(minigame == null) {
                player.sendMessage(c("&cEl juego es nullo"));
                return true;
            }
            if(minigame.isStarted()){
                if(minigame.containsPlayer(player)){
                    player.sendMessage(c("&cYa estás dento del sorteo!"));
                    return true;
                }
                GameManager.joinGame(player, minigame);
                player.sendMessage(c("&eHas ingresado al sorteo!"));
            }else{
                player.sendMessage(c("&cEl sorteo aún no ha iniciado!"));
            }
        }else if(args[0].equalsIgnoreCase("leave")){
            Minigame minigame = plugin.getMinigame();
            if(minigame == null) return true;
            if(minigame.isStarted()){
                if(!minigame.containsPlayer(player)){
                    player.sendMessage(c("&cPrimero debes ingresar al sorteo!"));
                    return true;
                }else{
                    GameManager.leavePlayer(player, minigame);
                    player.sendMessage(c("&cAcabas de salir del juego!"));

                }
            }else{
                player.sendMessage(c("&cEl sorteo aún no ha iniciado!"));
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
