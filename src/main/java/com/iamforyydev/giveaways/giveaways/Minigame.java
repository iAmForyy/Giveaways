package com.iamforyydev.giveaways.giveaways;

import com.iamforyydev.giveaways.runnable.StartCountdown;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

import static com.iamforyydev.giveaways.utils.StringUtils.c;

public class Minigame {

    private final String name;
    private final List<Player> participants;
    private boolean isStarted;
    private final StartCountdown countdown;

    public Minigame(String name){
        this.name = name;
        this.participants = new ArrayList<>();
        this.isStarted = false;
        this.countdown = new StartCountdown(this);

    }

    public void sendMessage(String message){
        for(Player participant : participants){
            participant.sendMessage(c(message));
        }
    }



    public void start(){
        this.setStarted(true);
        this.countdown.startCountdown();
    }

    public String getName() {
        return name;
    }

    public List<Player> getParticipants() {
        return participants;
    }

    public void addPlayer(Player player){
        if(containsPlayer(player)) return;
        this.participants.add(player);
    }

    public void removePlayer(Player player){
        if(!containsPlayer(player)) return;
        this.participants.remove(player);

    }

    public boolean containsPlayer(Player pleyer){
        return participants.contains(pleyer);
    }



    public boolean isStarted() {
        return isStarted;
    }

    public void setStarted(boolean started) {
        isStarted = started;
    }

    public StartCountdown getCountdown() {
        return countdown;
    }
}
