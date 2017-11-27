package service;

import entity.Column;
import entity.Database;
import entity.System;
import entity.Table;
import entity.enums.DataType;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

public class TableManagerTest {
    private DatabaseManager dbm;
    private SystemManager sm;
    private TableManager tableManager;

    @Before
    public void initDatabase(){
        sm = new SystemManager();
        sm.createDatabase("test");
        dbm = sm.getDatabaseManager("test");
        Map<String,DataType> map = new HashMap<>();
        map.put("id",DataType.INTEGER);
        dbm.createTable("testTable",ColumnManager.convertToColumnSet(map));
        tableManager = dbm.getTableManager("testTable");
    }
    @After
    public void destroyDatabase(){
        sm.removeDatabase("test");
    }

    @Test
    public void createRow() throws Exception {
        Map<Column,Object> map = new HashMap<>();
        Column column = new Column("test",DataType.INTEGER);
        map.put(column,12);
        tableManager.createRow(RowManager.convertToRow(map));
        assertTrue(tableManager.getTable().getRows().size()==1);
    }

    @Test
    public void getRowManagerByRowIndex() throws Exception {
        Map<Column,Object> map = new HashMap<>();
        Column column = new Column("test",DataType.INTEGER);
        map.put(column,12);
        tableManager.createRow(RowManager.convertToRow(map));
        RowManager rm = tableManager.getRowManagerByRowIndex(0);
        assertTrue(rm!=null);
    }

    @Test
    public void getColumnManagerByName() throws Exception {
        ColumnManager cm = tableManager.getColumnManagerByName("id");
        assertTrue(cm!=null);
    }

}