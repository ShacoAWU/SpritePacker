package spritepacker;

public enum OpCodes {
    /**
     * The OpCodes that will be used to read and write the cache
     */
    FILE_NAME(1), FILE_LENGTH(2) ,FILE_DATA(3);

    /**
     * Holds the specific OpCode.
     */
    private int opCode;

    /**
     * Creates a new OpCode.
     */
    private OpCodes(int opCode) {
        this.opCode = opCode;
    }

    /**
     * Returns the value of the OpCode.
     */
    public int getOpCode() {
        return opCode;
    }
}
