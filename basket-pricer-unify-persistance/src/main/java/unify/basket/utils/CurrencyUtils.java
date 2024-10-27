package unify.basket.utils;

import java.text.NumberFormat;
import java.util.Currency;
import java.util.Locale;

public class CurrencyUtils {


    public static String format(double value) {
        if (value < 1) {
            int newValue = (int) (value * 100);
            return newValue + "p";
        }
        NumberFormat numberFormat = NumberFormat.getCurrencyInstance(Locale.UK);
        numberFormat.setCurrency(Currency.getInstance(Locale.UK));
        return numberFormat.format(value);
    }
}
