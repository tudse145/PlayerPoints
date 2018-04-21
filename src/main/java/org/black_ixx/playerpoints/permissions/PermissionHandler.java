package org.black_ixx.playerpoints.permissions;

import org.spongepowered.api.command.CommandSource;

/**
 * Modified version to handle permissions. Removed Vault handling. Allows for
 * checking if a given CommandSender has a permission node by using the
 * PermissionNode enumeration.
 * 
 * @author Mitsugaru
 * 
 */
public class PermissionHandler {

    /**
     * Check if the CommandSender has the specified permission node.
     * 
     * @param CommandSender
     * @param PermissionNode
     *            to check
     * @return True if sender has permission, else false. If sender is OP, then
     *         return true always.
     */
    public static boolean has(CommandSource sender, PermissionNode node) {
        return sender.hasPermission(node.getNode());
    }

}
