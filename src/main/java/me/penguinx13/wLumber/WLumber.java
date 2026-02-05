package me.penguinx13.wLumber;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class WLumber extends JavaPlugin {

    @Override
    public void onEnable() {
        Bukkit.getPluginManager().registerEvents(new BreakListener(this), this);
    }
}
