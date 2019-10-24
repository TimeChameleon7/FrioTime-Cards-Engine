package io.github.algodiv.cards_engine.engine.core;

import java.io.IOException;

public class NotDirectoryException extends IOException {
    NotDirectoryException(String message) {
        super(message);
    }
}
