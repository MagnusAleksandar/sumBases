package sumbases;

import java.util.ArrayList;

import Modelo.*;
import Vista.Most;

public class SumBases {
    private static Operaciones o = new Operaciones();
    private static Most m = new Most();
    private static Diccionario d = new Diccionario();

    public static void main(String[] args) {
        o.askNum();

        o.askOp();
        o.arrFiller();
        o.operar();
        o.resTot();
    }

}
