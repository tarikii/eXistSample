package net.xeill.elpuig.controller;

import javax.xml.xquery.XQConnection;
import javax.xml.xquery.XQDataSource;
import javax.xml.xquery.XQException;
import javax.xml.xquery.XQExpression;
import javax.xml.xquery.XQResultSequence;

import net.xqj.exist.ExistXQDataSource;

public class ExistController {
    private XQConnection connection;
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

    public XQResultSequence executeQuery(String query) {
        try {
            XQExpression xqe = connection.createExpression();
            XQResultSequence xqrs = xqe.executeQuery(query);
            return xqrs;
        } catch (XQException e) {
            throw new RuntimeException(e);
        }
    }

    public void executeCommand(String command) {
        try {
            XQExpression xqe = connection.createExpression();
            xqe.executeCommand(command);
        } catch (XQException e) {
            throw new RuntimeException(e);
        }
    }

    public void printResultSequence(XQResultSequence xqrs) {
        try {
            while (xqrs.next()) {
                System.out.println(xqrs.getItemAsString(null));
            }
        } catch (XQException e) {
            throw new RuntimeException(e);
        }
    }

    public void queryAllCharacters(){
        XQResultSequence xqrs = executeQuery("for $character in doc('/db/apps/foaf/characters.xml')//character return $character");
        printResultSequence(xqrs);
    }

    public void queryOnlyNameAllCharacters(){
        XQResultSequence xqrs = executeQuery("for $character in doc('/db/apps/foaf/characters.xml')//character order by $character/name return $character/name");
        printResultSequence(xqrs);
    }

    public void queryCharactersPea(){
        XQResultSequence xqrs = executeQuery("for $character in doc ('/db/apps/foaf/characters.xml')//character[name[contains(., 'Pea')]] return $character");
        printResultSequence(xqrs);
    }

    public void queryCharacters2Abilities(){
        XQResultSequence xqrs = executeQuery("for $character in doc('/db/apps/foaf/characters.xml')//character[count(abilities/ability) = 2] return $character");
        printResultSequence(xqrs);
    }

    public void queryCharactersZombies(){
        XQResultSequence xqrs = executeQuery("for $character in doc('/db/apps/foaf/characters.xml')//character[id_character_type = 2] return $character");
        printResultSequence(xqrs);
    }

    public void queryCharacterSearch(String nameCharacter) {
        XQResultSequence xqrs = executeQuery("for $character in doc('/db/apps/foaf/characters.xml')//character where contains(lower-case($character/name), lower-case('" + nameCharacter +"')) return $character");
        printResultSequence(xqrs);
    }

    public void updateCharacterName(String oldName, String newName) {
        String xquery = "update value \n" +
                "doc('/db/apps/foaf/characters.xml')//character[name='" + oldName + "']/name \n" +
                " with '" + newName + "'";
        executeCommand(xquery);

        String query = "for $character in doc('/db/apps/foaf/characters.xml')//character[name='" + newName + "'] return $character";
        XQResultSequence xqrs = executeQuery(query);
        printResultSequence(xqrs);
    }

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

    public void deleteCharacter(String nameCharacter){
        String delete = "update delete doc('/db/apps/foaf/characters.xml')//character[name='" + nameCharacter + "']";
        executeCommand(delete);
    }

    public void deleteMultipleCharacter(int deleteCharacterType){
        String delete = "update delete doc('/db/apps/foaf/characters.xml')//character[id_character_type='" + deleteCharacterType + "']";
        executeCommand(delete);
    }
}