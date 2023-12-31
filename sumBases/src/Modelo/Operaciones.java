package Modelo;

import java.util.ArrayList;
import java.util.Collections;

import Vista.Most;

public class Operaciones {
    private static ArrayList<Integer> arr = new ArrayList<Integer>();
    private static ArrayList<String> num = new ArrayList<>();
    private static ArrayList<Character> oper = new ArrayList<>();
    private static ArrayList<String> re = new ArrayList<>();
    private static Most m = new Most();
    private static Diccionario d = new Diccionario();
    private static Verficaciones v = new Verficaciones();
    private static int a;

    public static void askNum() {
        int op = 0;
        String n;

        try {
            a = m.pedirEntero("Ingrese la base");
        } catch (Exception e) {
            m.mostraDatos("Caracter no válido.");
            System.exit(0);
        }

        do {
            try {
                n = m.pedirString("Ingrese un numero para operar");
                if (v.baseCorr(n, a))
                    num.add(n);
                else {
                    m.mostraDatos("El número ingresado sobrepasa la base.");
                    System.exit(0);
                }
                op = m.pedirEntero("¿Desea ingresar un número nuevo? 1: Sí 0: No");
                if (op != 1 && op != 0) {
                    m.mostraDatos("Valor no válido.");
                    System.exit(0);
                }
            } catch (Exception e) {
                m.mostraDatos("Caracter no válido.");
                System.exit(0);
            }
        } while (op == 1);
        if (op == 0 && num.size() < 2) {
            System.exit(0);
        }
    }

    public static void askOp() {
        char sym;
        for (int i = 1; i < num.size(); i++) {
            try {
                sym = m.pedirChar("Ingrese operacion para " + num.get(i - 1) + " y " + num.get(i));
                oper.add(i - 1, sym);
            } catch (Exception e) {
                m.mostraDatos("Operación vo válida.");
                System.exit(0);
                ;
            }
        }
    }

    public static void arrFiller() {
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i < num.size(); i++) {
            if (num.get(i).length() < num.get(i - 1).length()) {
                sb.append(num.get(i));
                sb.reverse();
                for (int j = num.get(i).length(); j < num.get(i - 1).length(); j++) {
                    sb.append(0);
                }
                sb.reverse();
                num.set(i, sb.toString());
            }
        }
    }

    public static void operar() {
        StringBuilder sb = new StringBuilder("");
        String op1, op2, s, r;
        int con1 = 0, con2, c, p, m1, base = a;

        for (int i = 1; i < num.size(); i++) {
            op1 = num.get(i - 1);
            op2 = num.get(i);
            switch (oper.get(i - 1)) {
                case '+':
                    s = suma(op1, op2, base);
                    sb.append(s);
                    sb.reverse();
                    for (int j = 0; j < s.length(); j++) {
                        c = d.convert1024In(sb.charAt(j));
                        // System.out.println(c);
                        p = (int) Math.pow(base, j);
                        m1 = c * p;
                        con1 += m1;
                    }
                    con2 = sumaDec(op1, op2, base);
                    v.confirm(con1, con2);
                    if (con1 == con2)
                        re.add(s);
                    sb.delete(0, sb.length());
                    con1 = 0;
                    break;
                case '-':
                    r = resta(op1, op2, base);
                    sb.append(r);
                    sb.reverse();
                    for (int j = 0; j < r.length(); j++) {
                        c = d.convert1024In(sb.charAt(j));
                        // System.out.println(c);
                        p = (int) Math.pow(base, j);
                        m1 = c * p;
                        con1 += m1;
                    }
                    con2 = restaDec(op1, op2, base);
                    v.confirm(con1, con2);
                    if (con1 == con2)
                        re.add(r);
                    sb.delete(0, sb.length());
                    con1 = 0;
                    break;
                default:
                    m.mostraDatos("Signo no válido.");
                    System.exit(0);
            }
        }
    }

    public static int strToDec(String st, int base) { // Convierte "numero" ingresado a entero (base 10)
        StringBuilder sb = new StringBuilder();
        sb.append(st);
        sb.reverse();

        int ch, res, pot, fin = 0;

        for (int i = 0; i < st.length(); i++) {
            ch = d.convert1024In(sb.charAt(i)); // Convierte cada caracter a su entero segÃºn el diccionario
            pot = (int) Math.pow(base, i); // Eleva la base a la potencia segun su Ã­ndice
            res = ch * pot;// Multiplica los anteriores
            fin += res;// Acumulador
        }
        // System.out.println(fin);
        return fin;
    }

    public static void resTot() {
        StringBuilder sb = new StringBuilder();
        int fin = strToDec(re.get(0), a), divr, remd;
        String result, op, appndr = "";
        char remh;
        for (int i = 2; i < num.size(); i++) {
            result = re.get(i - 2);
            op = num.get(i);

            // fin += restaDec(result, re.get(i), base);

            if (oper.get(i - 1) == '+')
                fin = sumaDec(result, op, a);
            else
                fin = restaDec(result, op, a);
        }
        m.mostraDatos("Total en decimal: " + String.valueOf(fin));
        divr = fin;
        do {
            remd = divr % a;
            remh = d.convertIn1024(remd);
            divr = (int) Math.floor(divr / a);
            appndr = appndr.concat(String.valueOf(remh));
        } while (divr > 0);
        sb.append(appndr);
        sb.reverse();
        m.mostraDatos("Total en base inicial: " + sb.toString());
    }

    public static String suma(String st1, String st2, int base) {
        StringBuilder sb1 = new StringBuilder();
        sb1.append(st1);
        sb1.reverse();
        StringBuilder sb2 = new StringBuilder();
        sb2.append(st2);
        sb2.reverse();

        int ch, carry = 0;
        String s = "";
        arr.clear();

        for (int i = 0; i < st1.length(); i++) {
            ch = carry + d.convert1024In(sb1.charAt(i)) + d.convert1024In(sb2.charAt(i));

            if (ch < base)
                carry = 0;
            else {
                carry = 1;
                ch -= base;
            }
            System.out.println(carry + " + " + d.convert1024In(sb1.charAt(i)) + " + " + d.convert1024In(sb2.charAt(i))
                    + " = " + ch);
            arr.add(ch);
        }
        if (carry == 1)
            arr.add(1);

        Collections.reverse(arr);

        for (int j = 0; j < arr.size(); j++) {
            s = s.concat(String.valueOf(d.convertIn1024(arr.get(j))));
        }
        m.mostraDatos("Resultado suma directa en la base " + s);
        return s;
    }

    public static String resta(String st1, String st2, int base) {
        StringBuilder sb1 = new StringBuilder();
        sb1.append(st1);
        sb1.reverse();
        StringBuilder sb2 = new StringBuilder();
        sb2.append(st2);
        sb2.reverse();

        int ch, carry = 0, c1, c2;
        String s = "";
        arr.clear();

        for (int i = 0; i < st1.length(); i++) {
            c1 = d.convert1024In(sb1.charAt(i));
            c2 = d.convert1024In(sb2.charAt(i));
            ch = c1 - c2 + carry;

            if (c1 >= c2 && ch >= 0)
                carry = 0;
            if (c1 < c2 || ch < 0) {
                ch += base;
                carry = -1;
            }
            System.out.println(c1 + " - " + c2 + " + " + carry + " = " + ch);
            arr.add(ch);
        }
        Collections.reverse(arr);
        if (st1.length() < st2.length() || d.convert1024In(st1.charAt(0)) < d.convert1024In(st2.charAt(0)))
            s = s.concat("-");
        for (int j = 0; j < arr.size(); j++) {
            s = s.concat(String.valueOf(d.convertIn1024(arr.get(j))));

        }
        m.mostraDatos("Resultado resta directa en la base " + s);
        return s;
    }

    public static int sumaDec(String st1, String st2, int base) {
        StringBuilder sb1 = new StringBuilder();
        sb1.append(st1);
        sb1.reverse();
        StringBuilder sb2 = new StringBuilder();
        sb2.append(st2);
        sb2.reverse();

        int con1 = 0, con2 = 0, c, m1, p, res;

        for (int i = 0; i < st1.length(); i++) {
            c = d.convert1024In(sb1.charAt(i));
            p = (int) Math.pow(base, i);
            m1 = c * p;
            con1 += m1;
        }

        for (int j = 0; j < st2.length(); j++) {
            c = d.convert1024In(sb2.charAt(j));
            p = (int) Math.pow(base, j);
            m1 = c * p;
            con2 += m1;
        }
        res = con1 + con2;
        return res;
    }

    public static int restaDec(String st1, String st2, int base) {
        StringBuilder sb1 = new StringBuilder();
        sb1.append(st1);
        if (sb1.charAt(0) == '-')
            sb1.deleteCharAt(0);
        sb1.reverse();
        StringBuilder sb2 = new StringBuilder();
        sb2.append(st2);
        if (sb2.charAt(0) == '-')
            sb2.deleteCharAt(0);
        sb2.reverse();

        int con1 = 0, con2 = 0, c, m1, p, res;

        for (int i = 0; i < st1.length(); i++) {
            c = d.convert1024In(sb1.charAt(i));
            p = (int) Math.pow(base, i);
            m1 = c * p;
            con1 += m1;
        }

        for (int i = 0; i < st2.length(); i++) {
            c = d.convert1024In(sb2.charAt(i));
            p = (int) Math.pow(base, i);
            m1 = c * p;
            con2 += m1;
        }
        res = con1 - con2;
        // m.mostraDatos(String.valueOf(res));
        return res;
    }
}
