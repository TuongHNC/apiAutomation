package enumerations.dataflowenum;

public enum ComponentDataSet {

    ALL("all"),
    TOOLSQABOOKSTORE("toolsQABookStore");

    //------------------------------------------------------------------------------------------------------------------

    private final String value;

    ComponentDataSet(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
