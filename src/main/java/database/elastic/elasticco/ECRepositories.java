package database.elastic.elasticco;

import database.elastic.RepositoriesBase;

public class ECRepositories extends RepositoriesBase {

    public ECRepositories(String elasticUrl, String elasticPort) {

        this.elasticSearch = new ElasticCo(elasticUrl, elasticPort).getInstance().get();
    }
}
