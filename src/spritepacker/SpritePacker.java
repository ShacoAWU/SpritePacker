package spritepacker;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.zip.GZIPOutputStream;

public class SpritePacker {

    /**
     * Directory and file paths.
     */
    private String spriteDirPath, cacheFilePath;

    /**
     * List contains stored sprites of this instance.
     */
    private ArrayList<Sprite> sprites = new ArrayList<Sprite>();

    /**
     * Creates a SpritePacker that will load sprites from a sprite directory and
     * builds a cache on a given location.
     *
     * @param spriteDirPath directory where the sprites will be loaded from
     * @param cacheFilePath the cache will be saved here
     */
    public SpritePacker(String spriteDirPath, String cacheFilePath) {
        this.spriteDirPath = spriteDirPath;
        this.cacheFilePath = cacheFilePath;
    }

    /**
     * Builds a cache on the given location. It is possible to append data on an
     * old cache.
     *
     * @param append if false, the cache will be overwritten
     * @return true if succeeded
     */
    public boolean buildCache(boolean append, ArrayList<Sprite> sprites) {
        try {
            DataOutputStream outputStream = new DataOutputStream(new GZIPOutputStream(new FileOutputStream(cacheFilePath, append)));
            for (Sprite sprite : sprites) {
                outputStream.writeByte(OpCodes.FILE_NAME.getOpCode());
                outputStream.writeUTF(sprite.getName());
                byte[] data = sprite.getData();
                outputStream.writeByte(OpCodes.FILE_LENGTH.getOpCode());
                outputStream.writeInt(data.length);
                outputStream.writeByte(OpCodes.FILE_DATA.getOpCode());
                outputStream.write(data, 0, data.length);
            }
            outputStream.flush();
            outputStream.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Reads all the sprites in the sprite directory and adds them to a list. This
     * method will read the sprites again if this list is empty when called.
     *
     * @return a list of sprites
     */
    public ArrayList<Sprite> getSprites() {
        if (sprites.size() > 0) {
            return sprites;
        }
        File dir = new File(spriteDirPath);
        if (dir.exists()) {
            File[] files = dir.listFiles();
            if (files != null) {
                for (File file : files) {
                    if (isImage(file)) {
                        try {
                            sprites.add(new Sprite(getNameWithoutExtension(file), getBytes(file)));
                        } catch (IOException e) {
                            System.out.println("An error occurred while reading: " + file.getName());
                            e.printStackTrace();
                        }
                    }
                }
            }
        } else {
            throw new RuntimeException("Sprite directory not found!");
        }
        return sprites;
    }

    /**
     * Checks if the file is an PNG image.
     *
     * @param file file input
     * @return true if the file has a PNG extension
     */
    private boolean isImage(File file) {
        return file.getName().toLowerCase().endsWith(".png");
    }

    /**
     * Gets the read bytes from the file.
     *
     * @param file file input
     * @return the read bytes from the file
     * @throws java.io.IOException
     */
    private byte[] getBytes(File file) throws IOException {
        Path path = Paths.get(file.getAbsolutePath());
        return Files.readAllBytes(path);
    }

    /**
     * Gets the name of a file without the PNG extension.
     *
     * @param file file input
     * @return filename without the PNG extension
     */
    private String getNameWithoutExtension(File file) {
        String fileName = file.getName();
        return fileName.substring(0, fileName.length() - 4);
    }
}
