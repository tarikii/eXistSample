package net.xeill.elpuig.view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Menu {
    private int option;

    public Menu(){
        super();
    }

    public int mainMenu() {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        do {

            System.out.println(" \nMENU PRINCIPAL \n");

            System.out.println("1. Seleccionar todos los characters y listar los atributos.");
            System.out.println("2. Seleccionar todos los characters y listar SOLO sus nombres, ordenados por orden alfabetico.");
            System.out.println("3. Seleccionar todos los characters que contengan \"Pea\" y listar los atributos.");
            System.out.println("4. Seleccionar characters que contengan 2 habilidades y listar los atributos.");
            System.out.println("5. Seleccionar todos los characters que sean zombie (id character type: 2).");
            System.out.println("6. Seleccionar un character en concreto y listar los atributos.");
            System.out.println("7. Seleccionar un character concreto y modificarle el nombre.");
            System.out.println("8. Modificar varios registros de characters a la vez.");
            System.out.println("9. Eliminar un character en concreto en base a su nombre.");
            System.out.println("10. Eliminar 2 characters a la vez en base a su nombre.");
            System.out.println("0. Salir. ");
            System.out.println();

            System.out.println();
            System.out.println("Esculli opció: ");
            try {
                option = Integer.parseInt(br.readLine());
            } catch (NumberFormatException | IOException e) {
                System.out.println("valor no vàlid");
                e.printStackTrace();
            }
        } while (option != 1  && option != 2 && option != 3 && option != 4 && option != 5 && option != 6
                && option != 7 && option != 8 && option != 9 && option != 10 && option != 0);

        return option;
    }
}
