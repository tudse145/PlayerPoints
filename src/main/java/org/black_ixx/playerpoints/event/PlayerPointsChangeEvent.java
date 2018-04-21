package org.black_ixx.playerpoints.event;

import java.util.UUID;

import org.spongepowered.api.event.cause.Cause;

/**
 * Called when a player's points is to be changed.
 */
public class PlayerPointsChangeEvent extends PlayerPointsEvent {

    /**
     * Constructor.
     * 
     * @param name
     *            - Name of player.
     * @param change
     *            - Amount of points to be changed.
     */
    public PlayerPointsChangeEvent(UUID id, int change, Cause cause) {
        super(id, change, cause);
    }

}