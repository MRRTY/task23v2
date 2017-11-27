package service;

import entity.Column;
import entity.Table;
import entity.enums.DataType;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import static org.junit.Assert.*;

public class ColumnManagerTest {

    @Test
    public void convertToColumnSet() throws Exception {
        Map<String,DataType> map = new HashMap<>();
        map.put("id",DataType.INTEGER);
        Set<Column> columns = ColumnManager.convertToColumnSet(map);
        assertTrue(columns.stream().anyMatch(column -> column.getName().equals("id")));

    }

}