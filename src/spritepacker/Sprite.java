package spritepacker;

public class Sprite {
    private final String name;
    private final byte[] data;

    public Sprite(String name, byte[] data) {
        this.name = name;
        this.data = data;
    }

    public byte[] getData() {
        return data;
    }

    public String getName() {
        return name;
    }
}
