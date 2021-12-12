package database.elastic.awses7;

import database.elastic.RepositoriesBase;

public class AWSES7Repositories extends RepositoriesBase {

    public AWSES7Repositories(String elasticUrl, String elasticPort) {
        this.elasticSearch = new AWSES7(elasticUrl, elasticPort).getInstance().get();
    }

}
