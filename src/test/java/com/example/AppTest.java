package com.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class AppTest {

    private final App app = new App();

    @Test
    void greet_withName() {
        assertEquals("Hello, Alice!", app.greet("Alice"));
    }

    @Test
    void greet_withNull() {
        assertEquals("Hello, World!", app.greet(null));
    }

    @Test
    void greet_withBlank() {
        assertEquals("Hello, World!", app.greet("  "));
    }
}
