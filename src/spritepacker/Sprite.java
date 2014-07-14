package spritepacker;

public class Sprite {

    /**
     * Holds the sprite name.
     */
    private final String name;

    /**
     * Holds the sprite.
     */
    private final byte[] data;

    /**
     * Creates a Sprite object that will hold the sprite(byte array) and
     * the sprite name.
     *
     * @param name sprite name
     * @param data sprite data in a byte array
     */
    public Sprite(String name, byte[] data) {
        this.name = name;
        this.data = data;
    }

    /**
     * Returns the sprite name.
     *
     * @return the sprite in a byte array
     */
    public byte[] getData() {
        return data;
    }

    /**
     * Returns the sprite name.
     *
     * @return sprite name
     */
    public String getName() {
        return name;
    }
}
