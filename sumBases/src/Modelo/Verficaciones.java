package Modelo;

import Vista.Most;

public class Verficaciones {
    private static Most m = new Most();
    private static Diccionario d = new Diccionario();

    public static void confirm(int in, int dec) {
        if (in == dec) {
            m.mostraDatos("Resultado suma decimal directa: " + dec);
            m.mostraDatos("Coinciden");
        } else {
            m.mostraDatos("Resultado suma decimal directa: " + dec);
            m.mostraDatos("No coinciden");
        }
    }

    public static boolean baseCorr(String st, int base) {
        boolean flag = true;
        int num;
        if (st.charAt(0) == '-') {
            for (int i = 1; i <= st.length(); i++) {
                num = d.convert1024In(st.charAt(i));
                if (base <= num) {
                    flag = false;
                    break;
                }
            }
        } else {
            for (int i = 0; i < st.length(); i++) {
                num = d.convert1024In(st.charAt(i));
                if (base <= num) {
                    flag = false;
                    break;
                }
            }
        }
        return flag;
    }
}
