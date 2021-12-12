package database.elastic;

import lombok.Data;

import java.util.List;

@Data
public class TermValue {

    private String key;
    private String value;
    private List<String> values;


    public TermValue(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public TermValue(String key, List<String> values) {
        this.key = key;
        this.values = values;
    }

}
