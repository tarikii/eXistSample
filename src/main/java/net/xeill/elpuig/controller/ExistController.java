package net.xeill.elpuig.controller;

import javax.xml.xquery.XQConnection;
import javax.xml.xquery.XQDataSource;
import javax.xml.xquery.XQException;
import javax.xml.xquery.XQExpression;
import javax.xml.xquery.XQResultSequence;

import net.xqj.exist.ExistXQDataSource;

/**
 * Esta clase es el controlador del xml, nos permite gestionar las opciones del menu en base a los metodos que se crearan dentro de esta clase
 */
public class ExistController {
    private XQConnection connection;

    /**
     * Este constructor nos conecta con el existDB, para que desde la terminal el xquery pueda ver como es el xml y que pueda acceder a el y modificarlo
     * @throws RuntimeException Si no se conecta, saltara este error
     */
    public ExistController() {
        try {
            XQDataSource xqs = new ExistXQDataSource();
            xqs.setProperty("serverName", "localhost");
            xqs.setProperty("port", "8080");
            xqs.setProperty("user", "admin");
            xqs.setProperty("password", "");
            connection = xqs.getConnection();

        } catch (XQException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Este metodo permite ejecutar consultas que haremos desde el codigo, y la conexion creara la expresion para que esta ejecute el resto.
     * @return xqrs Devuelve el resultado de la consulta
     * @param query Este parametro reocge la consulta que ponemos
     * @throws RuntimeException si ocurre un error con la consulta, enviara un error indicando que la consulta no ha sido un éxito
     */
    public XQResultSequence executeQuery(String query) {
        try {
            XQExpression xqe = connection.createExpression();
            XQResultSequence xqrs = xqe.executeQuery(query);
            return xqrs;
        } catch (XQException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Este metodo ejecuta el comando una vez llamado el metodo
     * @param command Este parametro recoge el comando que queremos ejecutar
     */
    public void executeCommand(String command) {
        try {
            XQExpression xqe = connection.createExpression();
            xqe.executeCommand(command);
        } catch (XQException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Este metodo nos imprime por pantalla el resultado que nos ha dado la consulta que hemos realizado anteriormente
     * @param xqrs Este parametro recoge el resultado de la consulta y lo imprime por pantalla
     * @throws RuntimeException si ocurre un error al imprimir el resultado, saltara un error
     */
    public void printResultSequence(XQResultSequence xqrs) {
        try {
            while (xqrs.next()) {
                System.out.println(xqrs.getItemAsString(null));
            }
        } catch (XQException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Este metodo nos lista todos los characters, y luego los imprime
     */
    public void queryAllCharacters(){
        XQResultSequence xqrs = executeQuery("for $character in doc('/db/apps/foaf/characters.xml')//character return $character");
        printResultSequence(xqrs);
    }

    /**
     * Este metodo nos lista todos los characters, luego imprime SOLO los nombres
     */
    public void queryOnlyNameAllCharacters(){
        XQResultSequence xqrs = executeQuery("for $character in doc('/db/apps/foaf/characters.xml')//character order by $character/name return $character/name");
        printResultSequence(xqrs);
    }

    /**
     * Esta metodo lista los characters que contengan "Pea" en el nombre, luego imprime por pantalla el resultado
     */
    public void queryCharactersPea(){
        XQResultSequence xqrs = executeQuery("for $character in doc ('/db/apps/foaf/characters.xml')//character[name[contains(., 'Pea')]] return $character");
        printResultSequence(xqrs);
    }


    /**
     * Este metodo lista todos los characters que tienen 2 habilidades, luego las imprime por pantalla
     */
    public void queryCharacters2Abilities(){
        XQResultSequence xqrs = executeQuery("for $character in doc('/db/apps/foaf/characters.xml')//character[count(abilities/ability) = 2] return $character");
        printResultSequence(xqrs);
    }

    /**
     * Este metodo imprime por pantalla todos los characters que sean de tipo 2, es decir, los characters de tipo 2 son los zombies. Luego los imprime por pantalla
     */
    public void queryCharactersZombies(){
        XQResultSequence xqrs = executeQuery("for $character in doc('/db/apps/foaf/characters.xml')//character[id_character_type = 2] return $character");
        printResultSequence(xqrs);
    }

    /**
     * Este metodo nos busca el character que escribira el usuario por pantalla, luego lo imprime por pantalla
     * @param nameCharacter El nombre del character que busca el usuario
     */
    public void queryCharacterSearch(String nameCharacter) {
        XQResultSequence xqrs = executeQuery("for $character in doc('/db/apps/foaf/characters.xml')//character where contains(lower-case($character/name), lower-case('" + nameCharacter +"')) return $character");
        printResultSequence(xqrs);
    }

    /**
     * Este metodo trata de que el usuario escriba el nombre del character que quiere actualizar, luego se pedira el nuevo y lo pondra. Una vez hecho esto,
     * se sustituira el nombre viejo por el nuevo que ha puesto el usuario, luego se imprimira por pantalla.
     * @param oldName El nombre antiguo del character
     * @param newName El nombre nuevo del character con el que se va a actualizar
     */
    public void updateCharacterName(String oldName, String newName) {
        String xquery = "update value \n" +
                "doc('/db/apps/foaf/characters.xml')//character[name='" + oldName + "']/name \n" +
                " with '" + newName + "'";
        executeCommand(xquery);

        String query = "for $character in doc('/db/apps/foaf/characters.xml')//character[name='" + newName + "'] return $character";
        XQResultSequence xqrs = executeQuery(query);
        printResultSequence(xqrs);
    }

    /**
     * Este metodo hace lo mismo que el anterior, pero actualizara varios registros a la vez del character que buscara por el nombre. Luego lo imprime por pantalla
     * @param oldName El nombre antiguo del character
     * @param newName El nombre nuevo del character con el que se va a actualizar
     * @param newImage La nueva imagen del character
     * @param newHealth La nuva vida del character
     */
    public void updateMultipleCharactersInfo(String oldName, String newName, String newImage, String newHealth){
        String xqueryName = "update value \n" +
                "doc('/db/apps/foaf/characters.xml')//character[name='" + oldName + "']/name \n" +
                " with '" + newName + "'";
        executeCommand(xqueryName);

        String xqueryImage = "update value \n" +
                "doc('/db/apps/foaf/characters.xml')//character[name='" + newName + "']/image \n" +
                " with '" + newImage + "'";
        executeCommand(xqueryImage);

        String xqueryHealth = "update value \n" +
                "doc('/db/apps/foaf/characters.xml')//character[name='" + newName + "']/health \n" +
                " with '" + newHealth + "'";
        executeCommand(xqueryHealth);


        String queryName = "for $character in doc('/db/apps/foaf/characters.xml')//character[name='" + newName + "'] return $character";
        XQResultSequence xqrs = executeQuery(queryName);
        printResultSequence(xqrs);
    }

    /**
     * Este metodo pide al usuario que nombre tiene el character que quiere borrar, acto seguido lo borra si existe
     * @param nameCharacter El nombre del character que desea borrar el usuario
     */
    public void deleteCharacter(String nameCharacter){
        String delete = "update delete doc('/db/apps/foaf/characters.xml')//character[name='" + nameCharacter + "']";
        executeCommand(delete);
    }

    /**
     * Este metodo pide al usuario que tipo de character quiere borrar, el 1 o el 2, siendo el 1 plantas, y el 2 los zombies.
     * @param deleteCharacterType El tipo de character que desea borrar el usuario
     */
    public void deleteMultipleCharacter(int deleteCharacterType){
        String delete = "update delete doc('/db/apps/foaf/characters.xml')//character[id_character_type='" + deleteCharacterType + "']";
        executeCommand(delete);
    }
}