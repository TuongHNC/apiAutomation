package enumerations.dataflowenum;

public enum ComponentDataSet {

    TOOLSQABOOKSTORE("toolsQABookStore");

    private final String value;

    ComponentDataSet(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
