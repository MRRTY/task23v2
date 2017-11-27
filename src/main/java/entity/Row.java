package entity;

import java.io.Serializable;
import java.util.List;

public class Row implements Serializable {
    private List<Cell> cells;

    public Row(List<Cell> cells) {
        this.cells = cells;
    }

    public List<Cell> getCells() {
        return cells;
    }

    public void setCells(List<Cell> cells) {
        this.cells = cells;
    }
}
