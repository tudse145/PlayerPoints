package org.black_ixx.playerpoints.storage.imports;

import java.io.IOException;
import java.util.Map.Entry;

import org.black_ixx.playerpoints.PlayerPoints;
import org.black_ixx.playerpoints.storage.IStorage;
import org.black_ixx.playerpoints.storage.StorageType;

import ninja.leaping.configurate.ConfigurationNode;
import ninja.leaping.configurate.hocon.HoconConfigurationLoader;

/**
 * Import from YAML to MySQL.
 * 
 * @author Mitsugaru
 */
public class YAMLImport extends DatabaseImport {

    /**
     * Constructor.
     * 
     * @param plugin
     *            - Plugin instance.
     */
    public YAMLImport(PlayerPoints plugin) {
        super(plugin);
    }

    @Override
    void doImport() {
        plugin.getLogger().info("Importing YAML to MySQL");
        IStorage mysql = generator
                .createStorageHandlerForType(StorageType.MYSQL);
		try {
			ConfigurationNode config = HoconConfigurationLoader.builder().setPath(plugin.getConfigFolder().resolve("storage.cfg")).build().load();
	        final ConfigurationNode points = config
	                .getNode("Points");
	        for(Entry<Object, ? extends ConfigurationNode> key : points.getChildrenMap().entrySet()) {
	            mysql.setPoints(key.getKey().toString(), key.getValue().getInt());
	        }
		} catch (IOException e) {
			e.printStackTrace();
		}

    }

}
