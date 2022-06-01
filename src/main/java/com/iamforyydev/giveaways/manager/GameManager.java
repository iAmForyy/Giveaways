package com.iamforyydev.giveaways.manager;

import com.iamforyydev.giveaways.giveaways.Minigame;
import org.bukkit.entity.Player;

public class GameManager {

    public static void joinGame(Player player, Minigame minigame){
        minigame.addPlayer(player);
        if(minigame.getParticipants().size() >= 2){
            minigame.getCountdown().startCountdown();
        }
    }

    public static void leavePlayer(Player player, Minigame minigame){
        minigame.removePlayer(player);
    }

    public static void startGame(Minigame minigame){
        minigame.start();
    }






}
