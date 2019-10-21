package io.github.algodiv.cards_engine.commons.tools;

public class GameState implements IGameState {

    @Override
    public int addNewDeck() {
        // Returns the id of the newly created deck.
        return 0;
    }

    @Override
    public void shuffleDeck(int deckID) {
        // Shuffles the deck for the deckID
    }

    @Override
    public void putOnTop(int deckID, byte card) {
        // Puts the card on the top of deckID
    }

    @Override
    public void putOnBottom(int deckID, byte card) {
        // Puts the card on the bottom of deckID
    }

    @Override
    public byte draw(int deckID) {
        // Returns the value of card that was drawn from the deck passed.
        return 0;
    }
}
