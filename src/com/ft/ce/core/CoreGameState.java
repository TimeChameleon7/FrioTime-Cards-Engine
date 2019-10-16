package com.ft.ce.core;

import com.ft.ce.tools.GameState;

import java.util.ArrayList;

// This is the gameState class that will actually be used.
// If any part of a function should not exist in user space then it will be implemented here so that the user has no knowing of it.
public class CoreGameState extends GameState {
    ArrayList<Deck> decks = new ArrayList<>();

    @Override
    public int addNewDeck() {
        Deck newDeck = new Deck(Deck.Preset.FULL_DECK);
        decks.add(newDeck);
        return decks.indexOf(newDeck);
    }

    @Override
    public void shuffleDeck(int deckID) {
        decks.get(deckID).shuffle();
    }

    @Override
    public void putOnTop(int deckID, byte card) {
    }

    @Override
    public void putOnBottom(int deckID, byte card) {
    }

    @Override
    public byte draw(int deckID) {
        Card card = decks.get(deckID).draw();
        return card.toBytes()[0];
    }
}
