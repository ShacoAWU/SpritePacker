package spritepacker;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.EOFException;
import java.util.HashMap;
import java.util.zip.GZIPInputStream;

public class SpriteUnpacker {

    /**
     * Cache stored in a byte array.
     */
    private byte[] cacheData;

    /**
     * Map that contains the sprite in byte arrays.
     */
    private HashMap<String, byte[]> spriteMap = new HashMap<String, byte[]>();

    /**
     * Creates a SpriteUnpacker that will unload sprites from a cache.
     *
     * @param cacheData cache that will be unpacked
     */
    public SpriteUnpacker(byte[] cacheData) {
        this.cacheData = cacheData;
    }

    /**
     * Unpacks the sprites from the cache. And stores them in this instance.
     *
     * @return true if succeeded
     */
    public boolean unpackSprites() {
        if (spriteMap.size() > 0) {
            return true;
        }
        try {
            DataInputStream inputStream = new DataInputStream(new GZIPInputStream(new ByteArrayInputStream(cacheData)));
            int len = 0;
            String name = null;
            while (inputStream.available() > 0) {
                int opCode = inputStream.readByte();
                if (opCode == OpCodes.FILE_NAME.getOpCode()) {
                    name = inputStream.readUTF();
                } else if (opCode == OpCodes.FILE_LENGTH.getOpCode()) {
                    len = inputStream.readInt();
                } else if (opCode == OpCodes.FILE_DATA.getOpCode()) {
                    byte[] data = new byte[len];
                    inputStream.readFully(data);
                    spriteMap.put(name, data);
                }
            }
            return true;
        } catch (EOFException ignored) {
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Returns the sprite in a byte array
     *
     * @param spriteName sprite to be returned
     * @return the sprite in a byte array
     */
    public byte[] getSpriteBytes(String spriteName) {
        return spriteMap.get(spriteName);
    }

    /**
     * Clears the loaded sprites.
     */
    public void unloadSprites() {
        spriteMap.clear();
    }

}
