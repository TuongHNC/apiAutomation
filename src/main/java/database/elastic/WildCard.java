package database.elastic;

import lombok.Data;

@Data
public class WildCard {

    private String key;
    private String value;


    public WildCard(String key, String value) {
        this.key = key;
        this.value = value;
    }

}
