package service;

import com.sun.org.apache.xpath.internal.operations.Bool;
import entity.Database;
import entity.System;
import org.junit.After;
import org.junit.Test;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;

import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;


public class SystemManagerTest {
    @After
    public void nullifyDatabaseList(){
        System.getInstance().setDatabases(new ArrayList<>());
    }

    @Test(expected = exception.NotAllowedDatabaseNameException.class)
    public void createDatabaseWithEmptyName() throws Exception {
        SystemManager sm = new SystemManager();
        sm.createDatabase("");
    }

    @Test(expected = exception.NotAllowedDatabaseNameException.class)
    public void createTwoDatabasesWithSameName() throws Exception {
        SystemManager sm = new SystemManager();
        sm.createDatabase("name");
        sm.createDatabase("name");
    }

    @Test()
    public void createDatabaseWithNormalName() throws Exception {
        SystemManager sm = new SystemManager();
        sm.createDatabase("name");
        assertTrue(System.getInstance().getDatabases().stream().anyMatch(database -> database.getName().equals("name")));
    }

    @Test()
    public void deleteExistsDatabase() throws Exception {
        SystemManager sm = new SystemManager();
        sm.createDatabase("name");
        sm.removeDatabase("name");
        assertFalse(System.getInstance().getDatabases().stream().anyMatch(database -> database.getName().equals("name")));
    }

    @Test(expected = exception.NoSuchDatabaseException.class)
    public void deleteNotExistsDatabase() throws Exception {
        SystemManager sm = new SystemManager();
        sm.removeDatabase("name");
    }


    @Test(expected = exception.NoSuchDatabaseException.class)
    public void getNotExistDatabaseManager() throws Exception {
        SystemManager sm = new SystemManager();
        sm.getDatabaseManager("sss");
    }

    @Test()
    public void getDatabaseManager() throws Exception {
        SystemManager sm = new SystemManager();
        sm.createDatabase("test");
        DatabaseManager dbm = sm.getDatabaseManager("test");
        assertEquals("test",dbm.getDatabase().getName());
    }



    @Test()
    public void getDatabaseByName() throws Exception {
        SystemManager sm = new SystemManager();
        sm.createDatabase("name");
        Method method = sm.getClass().getDeclaredMethod("getDatabaseByName", String.class);
        method.setAccessible(true);
        Database database = (Database) method.invoke(sm,"name");
        String expected = "name";
        String actual = database.getName();
        assertEquals(expected,actual);
    }

    @Test()
    public void checkForExists() throws Exception {
        SystemManager sm = new SystemManager();
        sm.createDatabase("name");
        Method method = sm.getClass().getDeclaredMethod("checkExistsDatabase", String.class);
        method.setAccessible(true);
        Boolean res = (Boolean) method.invoke(sm,"name");
        assertTrue(res);
    }



}