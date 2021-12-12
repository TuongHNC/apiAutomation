package enumerations;

public enum Service {

    private String value;

    Service(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }
}
