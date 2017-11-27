package service;

import entity.Column;
import entity.Database;
import entity.System;
import entity.Table;
import entity.enums.DataType;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.*;

public class DatabaseManagerTest {
    private Database database;
    private DatabaseManager dbm;
    private SystemManager sm;

    @Before
    public void initDatabase(){
        database = new Database("test");
        sm = new SystemManager();
        System.getInstance().getDatabases().add(database);
        dbm = sm.getDatabaseManager(database.getName());
    }
    @After
    public void destroyDatabase(){
        System.getSystem().getDatabases().remove(database);
    }

    @Test
    public void createNormalTable() throws Exception {
        Map<String,DataType> map = new HashMap<>();
        map.put("id",DataType.INTEGER);
        dbm.createTable("testTable",ColumnManager.convertToColumnSet(map));
        assertTrue(dbm.getDatabase().getTables().stream().anyMatch(table -> table.getName().equals("testTable")));

    }
    @Test(expected = exception.NotAllowedTableNameException.class)
    public void createTwoTablesWithSameName() throws Exception {
        Map<String,DataType> map = new HashMap<>();
        map.put("id",DataType.INTEGER);
        dbm.createTable("testTable",ColumnManager.convertToColumnSet(map));
        dbm.createTable("testTable",ColumnManager.convertToColumnSet(map));

    }
    @Test(expected = exception.BadColumnException.class)
    public void createTwoTablesWithNoColumns() throws Exception {
        SystemManager sm = new SystemManager();
        DatabaseManager dbm = sm.getDatabaseManager(database.getName());
        dbm.createTable("testTable",ColumnManager.convertToColumnSet(new HashMap<>()));
    }

    @Test
    public void deleteTable() throws Exception {
        Map<String,DataType> map = new HashMap<>();
        map.put("id",DataType.INTEGER);
        dbm.createTable("testTable",ColumnManager.convertToColumnSet(map));
        dbm.deleteTable("testTable");
        assertFalse(dbm.getDatabase().getTables().stream().anyMatch(table -> table.getName().equals("testTable")));
    }

    @Test(expected = exception.NoSuchTableException.class)
    public void deleteNotExistTable() throws Exception {
        dbm.deleteTable("testTable");
    }
    @Test()
    public void getTableByName() throws Exception {
        Map<String,DataType> map = new HashMap<>();
        map.put("id",DataType.INTEGER);
        dbm.createTable("testTable",ColumnManager.convertToColumnSet(map));
        Method method = dbm.getClass().getDeclaredMethod("getTableByName", String.class);
        method.setAccessible(true);
        Table table = (Table) method.invoke(dbm,"testTable");
        String expected = "testTable";
        String actual = table.getName();
        assertEquals(expected,actual);
    }

    @Test()
    public void checkForExists() throws Exception {
        Map<String,DataType> map = new HashMap<>();
        map.put("id",DataType.INTEGER);
        dbm.createTable("testTable",ColumnManager.convertToColumnSet(map));
        Method method = dbm.getClass().getDeclaredMethod("checkExists", String.class);
        method.setAccessible(true);
        Boolean res = (Boolean) method.invoke(dbm,"testTable");
        assertTrue(res);
    }


}