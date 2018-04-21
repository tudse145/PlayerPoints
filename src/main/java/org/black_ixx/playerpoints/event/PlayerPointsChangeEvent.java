package org.black_ixx.playerpoints.event;

import java.util.UUID;

import org.spongepowered.api.event.Event;
import org.spongepowered.api.event.cause.Cause;
import org.spongepowered.api.event.impl.AbstractEvent;

/**
 * Called when a player's points is to be changed.
 */
public class PlayerPointsChangeEvent extends AbstractEvent {

    /**
     * Handler list.
     */
    private static final HandlerList handlers = new HandlerList();

    /**
     * Constructor.
     * 
     * @param name
     *            - Name of player.
     * @param change
     *            - Amount of points to be changed.
     */
    public PlayerPointsChangeEvent(UUID id, int change) {
    }

    /**
     * Static method to get HandlerList.
     * 
     * @return HandlerList.
     */
    public static HandlerList getHandlerList() {
        return handlers;
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

	@Override
	public Cause getCause() {
		// TODO Auto-generated method stub
		return null;
	}
}
