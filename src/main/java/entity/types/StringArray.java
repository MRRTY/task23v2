package entity.types;

import java.util.Arrays;

public class StringArray {
    private String[] vals;

    public StringArray(String vals) {
        this.vals = vals.split(",");
    }

    public String[] getVals() {
        return vals;
    }

    public void setVals(String[] vals) {
        this.vals = vals;
    }

    @Override
    public String toString() {
        return "StringArray{" +
                "vals=" + Arrays.toString(vals) +
                '}';
    }
}
