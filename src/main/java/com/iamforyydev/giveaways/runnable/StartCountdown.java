package com.iamforyydev.giveaways.runnable;

import com.iamforyydev.giveaways.Giveaways;
import com.iamforyydev.giveaways.giveaways.Minigame;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.List;
import java.util.Random;

import static com.iamforyydev.giveaways.utils.StringUtils.c;
import static com.iamforyydev.giveaways.utils.StringUtils.sendBroadcast;

public class StartCountdown extends BukkitRunnable {

    private final Giveaways plugin;
    private final Minigame minigame;
    private int countdown;

    public StartCountdown(Minigame minigame){
        this.plugin = Giveaways.getInstance();
        this.minigame = minigame;
        this.countdown = 20;
    }

    public void startCountdown(){
        try{
            minigame.setStarted(true);
            runTaskTimer(plugin, 0, 20L);
        }catch (IllegalStateException thrown){
            thrown.printStackTrace();
            Bukkit.getLogger().info("Already task");
        }
    }

    public void stopCountdown(){
        minigame.getParticipants().clear();
        minigame.setStarted(false);
        cancel();
        minigame.stop();
    }

    @Override
    public void run() {
        if(countdown <= 0){
            if(minigame.getParticipants().size() <= 1){
                sendBroadcast("&cThere was no winner!");
            }else{
                int random = new Random().nextInt(minigame.getParticipants().size());
                Player winner = minigame.getParticipants().get(random);
                sendBroadcast(plugin.getConfig().getString("Messages.has_winner").replace("<player>", winner.getName()));
                minigame.setStarted(false);
                plugin.getConfig().getStringList("Settings.winner_commands").forEach(s ->
                        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), s.replace("<player>", winner.getName())));
            }
            stopCountdown();
            return;
        }else if(countdown == 10 || countdown == 15){
                List<String> lista = plugin.getConfig().getStringList("Messages.announce");
                lista.add(c("&eThe draw starts in: &6"+countdown));lista.add("");
                lista.forEach(s -> sendBroadcast(s.replace("<seconds>", Integer.toString(countdown))));
        }
        countdown--;
    }
}

