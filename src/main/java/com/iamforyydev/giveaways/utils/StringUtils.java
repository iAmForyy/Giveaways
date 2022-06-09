package com.iamforyydev.giveaways.utils;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

public class StringUtils {

    public static String c(String message){
        return ChatColor.translateAlternateColorCodes('&', message);
    }
    public static void sendBroadcast(String message){
        Bukkit.getOnlinePlayers().forEach(player -> player.sendMessage(c(message)));
    }
}
