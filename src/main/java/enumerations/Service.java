package enumerations;

public enum Service {

    TOOLS_QA_BOOK_STORE_SERVICE("TOOLSQABOOKSTORESERVICE");

    private String value;

    Service(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }
}
