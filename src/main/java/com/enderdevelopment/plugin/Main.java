package com.enderdevelopment.plugin;

import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.util.Vector;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;

public final class Main extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        getLogger().info("SpiderManPlugin has been enabled!");
        getServer().getPluginManager().registerEvents(this, this);
    }

    @Override
    public void onDisable() {
        getLogger().info("SpiderManPlugin has been disabled!");
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("spiderman")) {
            if (sender instanceof Player) {
                Player player = (Player) sender;
                toggleSpiderMan(player);
                return true;
            } else {
                sender.sendMessage(Component.text("Only players can use this command!").color(NamedTextColor.RED));
                return false;
            }
        }
        return false;
    }

    private void toggleSpiderMan(Player player) {
        // Toggle Spider-Man abilities here
        player.sendMessage(Component.text("Spider-Man abilities toggled!").color(NamedTextColor.GREEN));
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        player.sendMessage(Component.text("Welcome to the Spider-Man world!").color(NamedTextColor.GOLD));
    }

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {
        Player player = event.getPlayer();
        // Web-slinging ability
        if (player.isSneaking()) {
            Vector direction = player.getLocation().getDirection().multiply(2);
            player.setVelocity(direction);
        }
        // Wall-crawling ability
        if (player.getLocation().getBlock().getRelative(0, -1, 0).getType().isAir()) {
            Vector velocity = player.getVelocity();
            velocity.setY(-0.1);
            player.setVelocity(velocity);
        }
    }
}