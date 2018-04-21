package org.black_ixx.playerpoints.services.version;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents version metadata.
 * 
 * @author Mitsugaru
 */
public class Metadata {

    /**
     * Empty metadata set.
     */
    public static final Metadata NONE = new Metadata("");

    /**
     * Raw metadata string.
     */
    private final String raw;

    /**
     * Parsed metadata.
     */
    private final List<String> metadata = new ArrayList<String>();

    /**
     * Constructor.
     * 
     * @param meta
     *            - Raw metadata string.
     */
    public Metadata(final String meta) {
        this.raw = meta;

        String[] ids = meta.split("\\.");
        for(int i = 0; i < ids.length; i++) {
            metadata.add(ids[i]);
        }
    }

    /**
     * Get the list of metadata.
     * 
     * @return
     */
    public List<String> getMetadata() {
        return metadata;
    }

    @Override
    public String toString() {
        return raw;
    }

}
