package entity;

import java.util.ArrayList;
import java.util.List;

public class System {
    private static final System system = new System();
    private List<Database> databases;

    private System() {
        this.databases = new ArrayList<>();
    }

    public static System getInstance(){
        return system;
    }

    public static System getSystem() {
        return system;
    }

    public List<Database> getDatabases() {
        return databases;
    }

    public void setDatabases(List<Database> databases) {
        this.databases = databases;
    }
}
