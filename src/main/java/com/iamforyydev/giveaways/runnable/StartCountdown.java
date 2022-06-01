package com.iamforyydev.giveaways.runnable;

import com.iamforyydev.giveaways.Giveaways;
import com.iamforyydev.giveaways.giveaways.Minigame;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import static com.iamforyydev.giveaways.utils.StringUtils.c;

public class StartCountdown extends BukkitRunnable {

    private final Giveaways plugin;
    private final Minigame minigame;
    private int countdown;

    public StartCountdown(Minigame minigame){
        this.plugin = Giveaways.getInstance();
        this.minigame = minigame;
        this.countdown = 30;
    }

    public void startCountdown(){
        try{
            minigame.setStarted(true);
            runTaskTimer(plugin, 0, 20L);
        }catch (IllegalStateException exception){
            exception.printStackTrace();
            Bukkit.getLogger().info("El task ya está iniciado!");
        }
    }


    @Override
    public void run() {
        if(countdown <= 0){
            cancel();
            if(minigame.getParticipants().size() == 0){
                Bukkit.broadcastMessage(c("&cNadie ingresó al sorteo, por lo que nadie ganó!"));
                return;
            }
            for(Player participant : minigame.getParticipants()){
                if(participant.getName().equals("iAmForyy_")){
                    minigame.setStarted(false);
                    participant.getInventory().addItem(new ItemStack(Material.DIAMOND, 2));
                    Bukkit.broadcastMessage(c("&eFelicidades! el jugador &6"+participant.getName()+" &eha ganado"));
                }
                minigame.getParticipants().clear();
            }
            return;
        }else if(countdown == 10 || countdown == 15 || countdown == 30){
            Bukkit.broadcastMessage(c("&eEl sorteo está a punto de iniciar, quedan &6"+countdown+" &esegundos para sortear"));
        }
        countdown--;
    }
}
