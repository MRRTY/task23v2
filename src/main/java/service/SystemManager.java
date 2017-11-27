package service;

import entity.Database;
import entity.System;
import exception.NoSuchDatabaseException;
import exception.NotAllowedDatabaseNameException;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.List;
import java.util.stream.Collectors;

public class SystemManager {
    private static System system = System.getInstance();

    public SystemManager() {
    }

    public void createDatabase(String name){
        if(name.trim().isEmpty() || checkExistsDatabase(name)){
            throw new NotAllowedDatabaseNameException();
        }else {
            system.getDatabases().add(new Database(name));
        }
    }
    public void removeDatabase(String name){
            system.getDatabases().remove(getDatabaseByName(name));
    }

    public DatabaseManager getDatabaseManager(String name){
        return new DatabaseManager(getDatabaseByName(name));
    }


    private boolean checkExistsDatabase(String name){
        return system.getDatabases().stream().anyMatch(database -> database.getName().equals(name));
    }

    private Database getDatabaseByName(String name) {
        if (checkExistsDatabase(name)) {
            return system.getDatabases().stream().filter(database -> database.getName().equals(name)).collect(Collectors.toList()).get(0);
        }else {
            throw new NoSuchDatabaseException();
        }
    }

    public static System getSystem() {
        return system;
    }

    public static void setSystem(System system) {
        SystemManager.system = system;
    }

    public void loadDatabase(String path) throws IOException, ClassNotFoundException {
        FileInputStream fis = new FileInputStream(path);
        ObjectInputStream ois = new ObjectInputStream(fis);
        Database database = (Database) ois.readObject();
        ois.close();
        system.getDatabases().add(database);
    }
}
