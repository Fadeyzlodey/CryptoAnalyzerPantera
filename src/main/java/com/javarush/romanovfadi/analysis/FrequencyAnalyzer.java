package com.javarush.romanovfadi.analysis;

import com.javarush.romanovfadi.cipher.CaesarCipher;
import com.javarush.romanovfadi.cipher.Language;

public class FrequencyAnalyzer {

    private static final double[] EN_FREQ = {
            8.167,1.492,2.782,4.253,12.702,2.228,2.015,6.094,6.966,0.153,0.772,4.025,
            2.406,6.749,7.507,1.929,0.095,5.987,6.327,9.056,2.758,0.978,2.360,0.150,1.974,0.074
    };

    private static final double[] RU_FREQ = {
            8.01,1.59,4.54,1.70,2.98,8.45,0.04,0.94,1.65,7.35,1.21,3.49,4.40,3.21,6.70,10.97,
            2.81,4.73,5.47,6.26,2.62,0.26,0.97,0.48,1.44,0.73,0.36,0.04,1.90,1.74,0.32,0.64,2.01
    };

    public static double chiSquared(String text, Language lang) {
        String up = (lang == Language.EN ? CaesarCipher.getEnUp() : CaesarCipher.getRuUp());
        String lo = (lang == Language.EN ? CaesarCipher.getEnLo() : CaesarCipher.getRuLo());
        double[] expFreq = (lang == Language.EN ? EN_FREQ : RU_FREQ);
        int n = up.length();
        int[] counts = new int[n];
        int total = 0;

        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            int idx = up.indexOf(c);
            if (idx >= 0) { counts[idx]++; total++; continue; }
            idx = lo.indexOf(c);
            if (idx >= 0) { counts[idx]++; total++; }
        }
        if (total == 0) return Double.POSITIVE_INFINITY;

        double chi = 0.0;
        for (int i = 0; i < n; i++) {
            double expected = expFreq[i] / 100.0 * total;
            if (expected <= 1e-9) continue;
            double diff = counts[i] - expected;
            chi += diff * diff / expected;
        }
        return chi;
    }

    public static int bestShift(String cipher, Language lang) {
        int alphaLen = (lang == Language.EN ? CaesarCipher.getEnUp().length() : CaesarCipher.getRuUp().length());
        double bestScore = Double.POSITIVE_INFINITY;
        int bestK = 0;
        for (int i = 0; i < alphaLen; i++) {
            String cand = CaesarCipher.transform(cipher, i, false);
            double chi = chiSquared(cand, lang);
            if (chi < bestScore) {
                bestScore = chi;
                bestK = i;
            }
        }
        return bestK;
    }
}
