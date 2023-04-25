package net.xeill.elpuig;

import net.xeill.elpuig.controller.ExistController;
import net.xeill.elpuig.view.Menu;

import javax.xml.xquery.XQException;
import javax.xml.xquery.XQResultSequence;
import java.util.Scanner;

/**
 * Esta clase es la que gestionara lo que el usuario ponga por terminal, llamando a los metodos necesarios de cada opción
 */
public class Main {

    /**
     * Constructor vacio del main
     */
    public Main(){}

    /**
     * El metodo que basicamente ejecutara nuestra aplicación, mostrara el menu y donde el usuario escribira la opcion que desea
     * @param args los argumentos por parametro que se le pasan
     * @throws XQException Si ocurre un error, saltara esta excepcion
     */
    public static void main(String[] args) throws XQException {
        ExistController ec = new ExistController();
        Scanner scanner = new Scanner(System.in);
        Menu menu = new Menu();
        String nameCharacter, newNameCharacter, newImageCharacter, newHealthCharacter, characterDelete;
        int option = 0;
        int characterTypeDelete = 0;

        option = menu.mainMenu();

        while (option != 0){
            switch (option){
                case 1:
                    ec.queryAllCharacters();
                    break;

                case 2:
                    ec.queryOnlyNameAllCharacters();
                    break;

                case 3:
                    ec.queryCharactersPea();
                    break;

                case 4:
                    ec.queryCharacters2Abilities();
                    break;

                case 5:
                    ec.queryCharactersZombies();
                    break;

                case 6:
                    System.out.print("El nombre del character que deseas buscar: ");
                    nameCharacter = scanner.nextLine();
                    ec.queryCharacterSearch(nameCharacter);
                    break;

                case 7:
                    System.out.print("El nombre del character que deseas modificar: ");
                    nameCharacter = scanner.nextLine();
                    System.out.println("Que nuevo nombre le quieres poner al character?");
                    newNameCharacter = scanner.nextLine();
                    ec.updateCharacterName(nameCharacter,newNameCharacter);
                    break;

                case 8:
                    System.out.print("El nombre del character que deseas modificar: ");
                    nameCharacter = scanner.nextLine();
                    System.out.println("Que nuevo nombre le quieres poner al character?");
                    newNameCharacter = scanner.nextLine();
                    System.out.println("Que nueva image le quieres poner al character?");
                    newImageCharacter = scanner.nextLine();
                    System.out.println("Que nueva health le quieres poner al character?");
                    newHealthCharacter = scanner.nextLine();
                    ec.updateMultipleCharactersInfo(nameCharacter,newNameCharacter,newImageCharacter,newHealthCharacter);
                    break;

                case 9:
                    System.out.print("El nombre que tiene el character que quieres exterminar: ");
                    characterDelete = scanner.nextLine();
                    ec.deleteCharacter(characterDelete);
                    break;

                case 10:
                    System.out.println("Que tipo de character quieres borrar: ");
                    System.out.println("1 - Plantas");
                    System.out.println("2 - Zombies");
                    characterTypeDelete = scanner.nextInt();
                    ec.deleteMultipleCharacter(characterTypeDelete);
                    break;
            }
            option = menu.mainMenu();
        }

    }
}

