package org.black_ixx.playerpoints.listeners;

import org.black_ixx.playerpoints.PlayerPoints;
import org.black_ixx.playerpoints.config.RootConfig;
import org.black_ixx.playerpoints.event.PlayerPointsChangeEvent;

public class RestrictionListener implements Listener {
    
    private PlayerPoints plugin;
    
    public RestrictionListener(PlayerPoints plugin) {
        this.plugin = plugin;
    }

    @EventHandler(priority = EventPriority.LOW)
    public void validatePlayerChangeEvent(PlayerPointsChangeEvent event) {
        RootConfig config = plugin.getModuleForClass(RootConfig.class);
        if(config.hasPlayedBefore) {
            Player player = plugin.getServer().getPlayer(event.getPlayerId());
            if(player != null) {
                event.setCancelled(!player.hasPlayedBefore());
            }
        }
    }
}
