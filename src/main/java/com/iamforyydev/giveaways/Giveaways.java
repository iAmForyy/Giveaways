package com.iamforyydev.giveaways;

import com.iamforyydev.giveaways.commands.MainCommands;
import com.iamforyydev.giveaways.giveaways.Minigame;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

public final class Giveaways extends JavaPlugin {

    private static Giveaways instance;
    private List<Minigame> minigames;

    @Override
    public void onEnable() {
        instance = this;
        minigames = new ArrayList<>();
        minigames.add(new Minigame(0));

        getConfig().options().copyDefaults(true);
        saveDefaultConfig();

        getCommand("giveaways").setExecutor(new MainCommands());
    }

    public static Giveaways getInstance(){
        return instance;
    }

    public Minigame getMinigame(){
        for(Minigame minigame : minigames){
            if(minigame.getId() == 0){
                return minigame;
            }
        }
        return null;
    }
}
