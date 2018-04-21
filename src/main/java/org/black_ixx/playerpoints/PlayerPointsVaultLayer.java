package org.black_ixx.playerpoints;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

import org.black_ixx.playerpoints.services.IModule;
import org.black_ixx.playerpoints.storage.StorageHandler;


/**
 * Vault economy layer for PlayerPoints.
 * 
 * @author Mitsugaru
 */
public class PlayerPointsVaultLayer  {

    /**
     * Plugin instance.
     */
    private PlayerPoints plugin;

    /**
     * Constructor.
     * 
     * @param plugin
     *            - Plugin instance.
     */
    public PlayerPointsVaultLayer(PlayerPoints plugin) {
        this.plugin = plugin;
    }

    public void starting() {
        // Set to low priority. Allow for other, standard economy plugins to
        // supercede ours.
        plugin.getServer().getServicesManager()
                .register(Economy.class, this, plugin, ServicePriority.Low);
    }

    public void closing() {
        plugin.getServer().getServicesManager().unregister(Economy.class, this);
    }

    public boolean isEnabled() {
        return plugin.isEnabled();
    }

    public String getName() {
        return plugin.getName();
    }

    public boolean hasBankSupport() {
        return false;
    }

    public int fractionalDigits() {
        return 0;
    }

    public String format(double amount) {
        StringBuilder sb = new StringBuilder();
        int points = (int) amount;
        sb.append(points + " ");
        if(points == 1) {
            sb.append(currencyNameSingular());
        } else {
            sb.append(currencyNamePlural());
        }
        return sb.toString();
    }

    public String currencyNamePlural() {
        return "Points";
    }

    public String currencyNameSingular() {
        return "Point";
    }

    public boolean hasAccount(String playerName) {
    	boolean has = false;
    	UUID id = handleTranslation(playerName);
    	if(id != null) {
    		has = plugin.getModuleForClass(StorageHandler.class).playerEntryExists(id.toString());
    	}
        return has;
    }

    public boolean hasAccount(String playerName, String worldName) {
        return hasAccount(playerName);
    }

    public double getBalance(String playerName) {
        return plugin.getAPI().look(handleTranslation(playerName));
    }

    public double getBalance(String playerName, String world) {
        return getBalance(playerName);
    }

    public boolean has(String playerName, double amount) {
        int current = plugin.getAPI().look(handleTranslation(playerName));
        return current >= amount;
    }

    public boolean has(String playerName, String worldName, double amount) {
        return has(playerName, amount);
    }

    public EconomyResponse withdrawPlayer(String playerName, double amount) {
        int points = (int) amount;
        boolean result = plugin.getAPI().take(handleTranslation(playerName), points);
        int balance = plugin.getAPI().look(handleTranslation(playerName));

        EconomyResponse response = null;
        if(result) {
            response = new EconomyResponse(amount, balance,
                    ResponseType.SUCCESS, null);
        } else {
            response = new EconomyResponse(amount, balance,
                    ResponseType.FAILURE, "Lack funds");
        }
        return response;
    }

    public EconomyResponse withdrawPlayer(String playerName, String worldName,
            double amount) {
        return withdrawPlayer(playerName, amount);
    }

    public EconomyResponse depositPlayer(String playerName, double amount) {
        int points = (int) amount;
        boolean result = plugin.getAPI().give(handleTranslation(playerName), points);
        int balance = plugin.getAPI().look(handleTranslation(playerName));

        EconomyResponse response = null;
        if(result) {
            response = new EconomyResponse(amount, balance,
                    ResponseType.SUCCESS, null);
        } else {
            response = new EconomyResponse(amount, balance,
                    ResponseType.FAILURE, null);
        }
        return response;
    }

    public EconomyResponse depositPlayer(String playerName, String worldName,
            double amount) {
        return depositPlayer(playerName, amount);
    }

    public EconomyResponse createBank(String name, String player) {
        return new EconomyResponse(0, 0, ResponseType.NOT_IMPLEMENTED,
                "Does not handle banks.");
    }

    public EconomyResponse deleteBank(String name) {
        return new EconomyResponse(0, 0, ResponseType.NOT_IMPLEMENTED,
                "Does not handle banks.");
    }

    public EconomyResponse bankBalance(String name) {
        return new EconomyResponse(0, 0, ResponseType.NOT_IMPLEMENTED,
                "Does not handle banks.");
    }

    public EconomyResponse bankHas(String name, double amount) {
        return new EconomyResponse(0, 0, ResponseType.NOT_IMPLEMENTED,
                "Does not handle banks.");
    }

    public EconomyResponse bankWithdraw(String name, double amount) {
        return new EconomyResponse(0, 0, ResponseType.NOT_IMPLEMENTED,
                "Does not handle banks.");
    }

    public EconomyResponse bankDeposit(String name, double amount) {
        return new EconomyResponse(0, 0, ResponseType.NOT_IMPLEMENTED,
                "Does not handle banks.");
    }

    public EconomyResponse isBankOwner(String name, String playerName) {
        return new EconomyResponse(0, 0, ResponseType.NOT_IMPLEMENTED,
                "Does not handle banks.");
    }

    public EconomyResponse isBankMember(String name, String playerName) {
        return new EconomyResponse(0, 0, ResponseType.NOT_IMPLEMENTED,
                "Does not handle banks.");
    }

    public List<String> getBanks() {
        return Collections.emptyList();
    }

    public boolean createPlayerAccount(String playerName) {
        // Assume true as the storage handler will dynamically add players.
        return true;
    }

    public boolean createPlayerAccount(String playerName, String worldName) {
        return createPlayerAccount(playerName);
    }
    
    private UUID handleTranslation(String name) {
        UUID id = null;
        try {
            UUID.fromString(name);
        } catch(IllegalArgumentException e) {
            id = plugin.translateNameToUUID(name);
        }
        return id;
    }

	public EconomyResponse createBank(String bank, OfflinePlayer player) {
		return new EconomyResponse(0, 0, ResponseType.NOT_IMPLEMENTED,
                "Does not handle banks.");
	}

	public boolean createPlayerAccount(OfflinePlayer player) {
		// Assume true as the storage handler will dynamically add players.
        return true;
	}

	public boolean createPlayerAccount(OfflinePlayer player, String world) {
		// Assume true as the storage handler will dynamically add players.
        return true;
	}

	public EconomyResponse depositPlayer(OfflinePlayer player, double amount) {
		int points = (int) amount;
        boolean result = plugin.getAPI().give(player.getUniqueId(), points);
        int balance = plugin.getAPI().look(player.getUniqueId());

        EconomyResponse response = null;
        if(result) {
            response = new EconomyResponse(amount, balance,
                    ResponseType.SUCCESS, null);
        } else {
            response = new EconomyResponse(amount, balance,
                    ResponseType.FAILURE, null);
        }
        return response;
	}

	public EconomyResponse depositPlayer(OfflinePlayer player, String world,
			double amount) {
		return depositPlayer(player, amount);
	}

	public double getBalance(OfflinePlayer player) {
		return plugin.getAPI().look(player.getUniqueId());
	}

	public double getBalance(OfflinePlayer player, String world) {
		return getBalance(player);
	}

	public boolean has(OfflinePlayer player, double amount) {
		int current = plugin.getAPI().look(player.getUniqueId());
        return current >= amount;
	}

	public boolean has(OfflinePlayer player, String world, double amount) {
		return has(player, amount);
	}

	public boolean hasAccount(OfflinePlayer player) {
		return plugin.getModuleForClass(StorageHandler.class).playerEntryExists(player.getUniqueId().toString());
	}

	public boolean hasAccount(OfflinePlayer player, String world) {
		return hasAccount(player);
	}

	public EconomyResponse isBankMember(String bank, OfflinePlayer player) {
		return new EconomyResponse(0, 0, ResponseType.NOT_IMPLEMENTED,
                "Does not handle banks.");
	}

	public EconomyResponse isBankOwner(String bank, OfflinePlayer player) {
		return new EconomyResponse(0, 0, ResponseType.NOT_IMPLEMENTED,
                "Does not handle banks.");
	}

	public EconomyResponse withdrawPlayer(OfflinePlayer player, double amount) {
		int points = (int) amount;
        boolean result = plugin.getAPI().take(player.getUniqueId(), points);
        int balance = plugin.getAPI().look(player.getUniqueId());

        EconomyResponse response = null;
        if(result) {
            response = new EconomyResponse(amount, balance,
                    ResponseType.SUCCESS, null);
        } else {
            response = new EconomyResponse(amount, balance,
                    ResponseType.FAILURE, "Lack funds");
        }
        return response;
	}

	public EconomyResponse withdrawPlayer(OfflinePlayer player, String world,
			double amount) {
		return withdrawPlayer(player, amount);
	}

}
