package org.black_ixx.playerpoints.event;

import java.util.UUID;

import org.spongepowered.api.event.cause.Cause;

/**
 * Called when a player's points is to be reset.
 */
public class PlayerPointsResetEvent extends PlayerPointsEvent {

    /**
     * Constructor.
     * 
     * @param name
     *            - Name of player.
     */
    public PlayerPointsResetEvent(UUID id, Cause cause) {
        super(id, 0, cause);
    }
}
