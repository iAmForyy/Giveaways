package com.iamforyydev.giveaways.manager;

import com.iamforyydev.giveaways.Giveaways;
import com.iamforyydev.giveaways.giveaways.Minigame;
import org.bukkit.entity.Player;

import static com.iamforyydev.giveaways.utils.StringUtils.sendBroadcast;

public class GameManager {

    public static void joinGame(Player player, Minigame minigame, Giveaways plugin){
        minigame.addPlayer(player);
        sendBroadcast(plugin.getConfig().getString("Messages.announce_player_entered").replace("<player>", player.getName()));
    }

    public static void leavePlayer(Player player, Minigame minigame, Giveaways plugin){
        minigame.removePlayer(player);
        sendBroadcast(plugin.getConfig().getString("Messages.announce_player_leave").replace("<player>", player.getName()));
    }

    public static void startGame(Minigame minigame){
        minigame.start();
    }






}
