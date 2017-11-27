package service;

import entity.Column;
import entity.Row;
import entity.enums.DataType;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

public class RowManagerTest {

    @Test
    public void editRow() throws Exception {
    }

    @Test
    public void convertToRowReturnRow() throws Exception {
        Column column = new Column("id", DataType.INTEGER);
        Map<Column,Object> map = new HashMap<>();
        map.put(column,12);
        Row row = RowManager.convertToRow(map);
        assertTrue(row instanceof Row);
    }

}