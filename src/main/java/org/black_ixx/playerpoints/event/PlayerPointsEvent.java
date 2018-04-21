package org.black_ixx.playerpoints.event;

import java.util.UUID;

import org.spongepowered.api.event.Cancellable;
import org.spongepowered.api.event.cause.Cause;
import org.spongepowered.api.event.cause.EventContextKeys;
import org.spongepowered.api.event.impl.AbstractEvent;

public class PlayerPointsEvent extends AbstractEvent implements Cancellable {
    /**
     * Player whose points is changing.
     */
    private final UUID playerId;
    /**
     * Amount their points are being changed by. Note, this is NOT the final
     * amount that the player's points balance will be. This is the amount to
     * modify their current balance by.
     */
    private int change;
    /**
     * Cancelled flag.
     */
    private boolean cancelled;
	private Cause cause;

    /**
     * Constructor.
     * 
     * @param id
     *            - Id of player.
     * @param change
     *            - Amount of change that will apply to their current balance.
     */
    public PlayerPointsEvent(UUID id, int change, Cause cause) {
        this.playerId = id;
        this.change = change;
		this.cause = cause;
    }

    /**
     * Get the amount of points that the player's balance will change by.
     * 
     * @return Amount of change.
     */
    public int getChange() {
        return change;
    }

    /**
     * Set the amount of change that will be used to adjust the player's
     * balance.
     * 
     * @param change
     *            - Amount of change.
     */
    public void setChange(int change) {
        this.change = change;
    }

    /**
     * Get the player id.
     * 
     * @return Player UUID.
     */
    public UUID getPlayerId() {
        return playerId;
    }

    @Override
    public boolean isCancelled() {
        return cancelled;
    }

    @Override
    public void setCancelled(boolean cancelled) {
        this.cancelled = cancelled;
    }

	@Override
	public Cause getCause() {
		return cause;
	}

}
