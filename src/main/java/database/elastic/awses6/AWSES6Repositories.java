package database.elastic.awses6;

import database.elastic.RepositoriesBase;

public class AWSES6Repositories extends RepositoriesBase {

    public AWSES6Repositories(String elasticUrl, String elasticPort) {
        this.elasticSearch = new AWSES6(elasticUrl, elasticPort).getInstance().get();
    }

}
