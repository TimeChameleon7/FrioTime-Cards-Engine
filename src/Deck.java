import java.util.ArrayList;
import java.util.Collections;
import java.util.function.Predicate;

public class Deck {
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

    public Card[] getCards() {
        return cards.toArray(Card[]::new);
    }

    public Deck shuffle() {
        Collections.shuffle(cards);
        return this;
    }

    public boolean move(Card card, Deck deck) {
        if (cards.remove(card)) {
            deck.cards.add(card);
            return true;
        }
        return false;
    }

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
}
