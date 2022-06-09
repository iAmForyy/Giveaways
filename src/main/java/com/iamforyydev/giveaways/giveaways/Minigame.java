package com.iamforyydev.giveaways.giveaways;

import com.iamforyydev.giveaways.runnable.StartCountdown;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class Minigame {

    private final int id;
    private final List<Player> participants;
    private boolean isStarted;
    private StartCountdown countdown;

    public Minigame(int id){
        this.id = id;
        this.participants = new ArrayList<>();
        this.isStarted = false;
        this.countdown = new StartCountdown(this);
    }

    public void stop(){
        this.countdown = new StartCountdown(this);
    }

    public void start(){
        this.getCountdown().startCountdown();
    }

    public int getId() {
        return id;
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
