public enum Suite {
    SPADES((byte) 0b00000000),
    HEARTS((byte) 0b00010000),
    DIAMONDS((byte) 0b00100000),
    CLUBS((byte) 0b00110000);

    final byte mask;
    private final String properName;

    Suite(byte mask) {
        this.mask = mask;

        String actualName = super.toString();
        properName = actualName.substring(0, 1) + actualName.substring(1).toLowerCase();
    }

    static Suite getInstance(byte b) {
        Suite[] suites = Suite.values();
        for (int i = suites.length - 1; i >= 0; i--) {
            if (suites[i].matchesMask(b)) {
                return suites[i];
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
