package com.ft.ce.core;

public class Card {
    public final Name name;
    public final Suite suite;

    public Card(Name name, Suite suite) {
        this.name = name;
        if (name != Name.JOKER) {
            this.suite = suite;
        } else {
            this.suite = null;
        }
    }

    public Card(byte b) {
        this(Name.getInstance(b), Suite.getInstance(b));
    }

    public byte[] toBytes() {
        return new byte[]{(byte) (suite.mask ^ name.mask)};
    }

    public boolean is(Name name) {
        return this.name == name;
    }

    public boolean is(Suite suite) {
        return this.suite == suite;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Card)) return false;

        Card card = (Card) obj;
        return card.suite == suite && card.name == name;
    }

    @Override
    public String toString() {
        if (name == Name.JOKER) {
            return name.toString();
        } else {
            return name.toString() + " of " + suite.toString();
        }
    }
}
