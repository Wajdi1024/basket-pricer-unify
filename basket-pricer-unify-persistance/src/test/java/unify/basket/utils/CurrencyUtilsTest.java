package unify.basket.utils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CurrencyUtilsTest {

    @Test
    void format_aboveOne() {
        assertEquals("£10.00", CurrencyUtils.format(10.000));
    }

    @Test
    void format_underOne() {
        assertEquals("50p", CurrencyUtils.format(0.50));
    }

}