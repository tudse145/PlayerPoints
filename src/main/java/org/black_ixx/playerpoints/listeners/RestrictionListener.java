package org.black_ixx.playerpoints.listeners;

import java.util.Optional;

import org.black_ixx.playerpoints.PlayerPoints;
import org.black_ixx.playerpoints.config.RootConfig;
import org.black_ixx.playerpoints.event.PlayerPointsChangeEvent;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.Order;

public class RestrictionListener {
    
private PlayerPoints plugin;
    
    public RestrictionListener(PlayerPoints plugin) {
        this.plugin = plugin;
    }

    @Listener(order = Order.LATE)
    public void validatePlayerChangeEvent(PlayerPointsChangeEvent event) {
        RootConfig config = plugin.getModuleForClass(RootConfig.class);
        if(config.hasPlayedBefore) {
            Optional<Player> player = Sponge.getServer().getPlayer(event.getPlayerId());
            if(player.isPresent()) {
                event.setCancelled(!player.get().hasPlayedBefore());
            }
        }
    }
}
