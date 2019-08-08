package id.foodbang.utils;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

/**
 * Konversi ke Rupiah
 * Singleton - Multithread Safe
 *
 * @author : MufidJamaluddin
 */
public class RupiahFormat {
    private static DecimalFormat INSTANCE = null;

    public static DecimalFormat getInstance() {
        if (INSTANCE == null) {
            synchronized (RupiahFormat.class) {
                if (INSTANCE == null) {
                    DecimalFormat rupiahFormat = (DecimalFormat) DecimalFormat.getCurrencyInstance();
                    DecimalFormatSymbols formatRp = new DecimalFormatSymbols();

                    formatRp.setCurrencySymbol("Rp");
                    formatRp.setMonetaryDecimalSeparator(',');
                    formatRp.setGroupingSeparator('.');

                    rupiahFormat.setDecimalFormatSymbols(formatRp);

                    INSTANCE = rupiahFormat;
                }

                return INSTANCE;
            }
        }

        return INSTANCE;
    }
}
