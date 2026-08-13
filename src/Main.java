package com.biblioteca;

import com.biblioteca.persistencia.ConexionBD;
import com.biblioteca.vista.MenuConsola;

public class Main {

    public static void main(String[] args) {
        MenuConsola menu = new MenuConsola();
        menu.iniciar();
        ConexionBD.cerrarConexion();
    }
}
