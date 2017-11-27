package service;

import entity.Column;
import entity.Database;
import entity.System;
import entity.Table;
import exception.NoSuchTableException;
import exception.NotAllowedTableNameException;

import java.io.*;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class DatabaseManager implements Serializable {
    private Database database;

    public DatabaseManager(Database database) {
        this.database = database;
    }

    public  void createTable(String name, Set<Column> columns){
        if(name.trim().isEmpty() || checkExists(name)){
            throw new NotAllowedTableNameException();
        }else{
            database.getTables().add(new Table(name,columns));
        }
    }

    public TableManager getTableManager(String name){
        return new TableManager(getTableByName(name));
    }

    public void deleteTable(String name){
        if(checkExists(name)){
            database.getTables().remove(getTableByName(name));
        }else {
            throw new NoSuchTableException();
        }
    }


    private Table getTableByName(String name){
        return database.getTables().stream().filter(table -> table.getName().equals(name)).collect(Collectors.toList()).get(0);
    }
    private boolean checkExists(String name){
        return database.getTables().stream().anyMatch(table -> table.getName().equals(name));
    }

    public Database getDatabase() {
        return database;
    }

    public void setDatabase(Database database) {
        this.database = database;
    }

    public void saveDatabase() throws IOException {

        String defaultPath = "D:/test/";
        FileOutputStream fs = new FileOutputStream(defaultPath + database.getName());
        ObjectOutputStream os = new ObjectOutputStream(fs);
        os.writeObject(database);
        os.flush();
        os.close();

    }
}
