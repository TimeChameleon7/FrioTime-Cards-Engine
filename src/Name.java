public enum Name {
    ACE((byte) 0b00000000),
    TWO((byte) 0b00000001),
    THREE((byte) 0b00000010),
    FOUR((byte) 0b00000011),
    FIVE((byte) 0b00000100),
    SIX((byte) 0b00000101),
    SEVEN((byte) 0b00000110),
    EIGHT((byte) 0b00000111),
    NINE((byte) 0b00001000),
    TEN((byte) 0b00001001),
    JACK((byte) 0b00001010),
    QUEEN((byte) 0b00001011),
    KING((byte) 0b00001100),
    JOKER((byte) 0b00001101);


    final byte mask;
    private final String properName;

    Name(byte mask) {
        this.mask = mask;

        String actualName = super.toString();
        properName = actualName.substring(0, 1) + actualName.substring(1).toLowerCase();
    }

    static Name getInstance(byte b) {
        Name[] names = Name.values();
        for (int i = names.length - 1; i >= 0; i--) {
            if (names[i].matchesMask(b)) {
                return names[i];
            }
        }
        throw new IllegalArgumentException("No matching masks " + b);
    }

    private boolean matchesMask(byte b) {
        return (mask & b) == mask;
    }

    @Override
    public String toString() {
        return properName;
    }
}
