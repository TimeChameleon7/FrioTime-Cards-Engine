package io.github.algodiv.cards_engine.commons.cards;

import java.util.ArrayList;
import java.util.Collections;
import java.util.function.Predicate;

/**
 * Represents a stack of {@linkplain Card}s.
 */
public class Deck {
    /**
     * A standard deck of cards, jokers included.
     */
    private final static Card[] fullDeck = new Card[54];

    static {
        Name[] names = Name.values();
        Suite[] suites = Suite.values();

        int i = 0;
        for (Suite suite : suites) {
            for (int n = 0; n < names.length - 1; n++) {
                fullDeck[i++] = new Card(names[n], suite);
            }
        }
        fullDeck[52] = new Card(Name.JOKER, null);
        fullDeck[53] = new Card(Name.JOKER, null);
    }

    /**
     * The {@linkplain Card}s contained within {@code this} Deck.
     */
    public final ArrayList<Card> cards;

    public Deck(Predicate<Card> predicate) {
        cards = new ArrayList<>();
        for (Card card : fullDeck) {
            if (predicate.test(card)) {
                cards.add(card);
            }
        }
    }

    public Deck(Preset preset) {
        this(preset.predicate);
    }

    /**
     * Returns the current array of {@linkplain Card}s.
     *
     * @return the current array of {@linkplain Card}s.
     */
    public Card[] getCards() {
        return cards.toArray(Card[]::new);
    }

    /**
     * Shuffles the deck.
     *
     * @return {@code this}.
     */
    public Deck shuffle() {
        Collections.shuffle(cards);
        return this;
    }

    /**
     * Moves the specified {@linkplain Card} from {@code this} Deck to the specified Deck.
     *
     * @param card The card to be removed from this deck, and added to the target deck.
     * @param deck The deck that the specified card should be added to.
     * @return {@code true} if the movement was successful.
     */
    public boolean move(Card card, Deck deck) {
        if (cards.remove(card)) {
            deck.cards.add(card);
            return true;
        }
        return false;
    }

    /**
     * Returns {@code true} if {@code this} deck can move {@literal count} cards.
     *
     * @param count Number of cards to check if could be moved.
     * @return {@code true} if {@code this} deck can move {@literal count} cards.
     */
    public boolean canMove(int count) {
        return cards.size() >= count;
    }

    public boolean moveTop(Deck deck) {
        if (canMove(1)) {
            moveTop0(deck);
            return true;
        } else {
            return false;
        }
    }

    private void moveTop0(Deck deck) {
        Card card = cards.remove(cards.size() - 1);
        deck.cards.add(card);
    }

    public boolean deal(Deck[] decks) {
        if (canMove(decks.length)) {
            deal0(decks);
            return true;
        } else {
            return false;
        }
    }

    private void deal0(Deck[] decks) {
        for (Deck deck : decks) {
            moveTop0(deck);
        }
    }

    public boolean deal(int count, Deck[] decks) {
        if (canMove(count * decks.length)) {
            for (int i = 0; i < count; i++) {
                deal0(decks);
            }
            return true;
        }
        return false;
    }

    public enum Preset {
        FULL_DECK(card -> true),
        NO_JOKERS(card -> !card.is(Name.JOKER)),
        EMPTY(card -> false);

        final Predicate<Card> predicate;

        Preset(Predicate<Card> predicate) {
            this.predicate = predicate;
        }
    }

    public Card draw() {
        return cards.remove(cards.size() - 1);
    }
}
