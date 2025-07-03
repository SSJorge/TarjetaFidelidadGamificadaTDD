package com.fidelidad;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.Test;
// import static org.junit.jupiter.api.Assertions.assertTrue;

public class AppTest {

    @Test
        public void testConstructor() {
            App app = new App(); // cubre el constructor implícito
            assertNotNull(app);
        }

    @Test
        public void testMain() {
        App.main(new String[]{});
    }
}