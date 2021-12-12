package database.elastic;

import database.elastic.awses6.AWSES6Repositories;
import database.elastic.awses7.AWSES7Repositories;
import database.elastic.elasticco.ECRepositories;
import enumerations.ElasticSearchType;

public class RepositoryFactory {

    public static IRepositoriesBase getRepository(ElasticSearchType elasticSearchType, String elasticUrl, String elasticPort) {
        switch (elasticSearchType) {

            case ELASTIC_CO:
                return new ECRepositories(elasticUrl, elasticPort);

            case AWS_ELASTIC_SEARCH_6:
                return new AWSES6Repositories(elasticUrl, elasticPort);

            case AWS_ELASTIC_SEARCH_7:
                return new AWSES7Repositories(elasticUrl, elasticPort);

            default:
                throw new IllegalArgumentException("This type is unsupported");
        }
    }

}
