package spritepacker;

public enum OpCodes {
    FILE_NAME(1), FILE_LENGTH(2) ,FILE_DATA(3);

    private int opCode;

    private OpCodes(int opCode) {
        this.opCode = opCode;
    }

    public int getOpCode() {
        return opCode;
    }
}