package entity.types;

import java.util.Arrays;

public class MyEnum {
    private String[] values;

    public MyEnum(String values) {
        this.values = values.split(",");
    }

    public String[] getValues() {
        return values;
    }

    public void setValues(String[] values) {
        this.values = values;
    }

    @Override
    public String toString() {
        return "MyEnum{" +
                "values=" + Arrays.toString(values) +
                '}';
    }
}
