package entity.types;

public class StringIntl {
    private String begin;
    private String end;

    public StringIntl(String val) {
        this.begin = val.split("\\..")[0];
        this.end = val.split("\\..")[1];
    }

    public String getBegin() {
        return begin;
    }

    public void setBegin(String begin) {
        this.begin = begin;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    @Override
    public String toString() {
        return "StringIntl{" +
                "begin='" + begin + '\'' +
                ", end='" + end + '\'' +
                '}';
    }
}
