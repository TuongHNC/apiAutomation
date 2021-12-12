package database.elastic.elasticco;

import apis.AbstractBaseRequest;
import constants.LogConstants;
import database.IDatabase;
import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.nio.reactor.IOReactorConfig;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class ElasticCo extends AbstractBaseRequest implements IDatabase<RestHighLevelClient> {

    private static final Logger LOGGER = LogManager.getLogger(ElasticCo.class);

    private static ElasticCo elasticSearch;
    private static RestHighLevelClient restHighLevelClient;

    private static String elasticUrl;
    private static String elasticPort;
    private static String elasticScheme = "https";
    private static int elasticConnectionTimeout = 60000;
    private static int elasticSocketTimeout = 60000;

    protected ElasticCo(String elasticUrl, String elasticPort) {
        this.elasticUrl = elasticUrl;
        this.elasticPort = elasticPort;
    }

    public static ElasticCo getInstance() {
        if (elasticSearch == null) {
            elasticSearch = new ElasticCo(elasticUrl, elasticPort);
        }
        return elasticSearch;
    }

    public RestHighLevelClient get() {
        if (restHighLevelClient == null) {
            connect();
        }
        return restHighLevelClient;
    }

    public RestHighLevelClient connect() {
        LOGGER.info(LogConstants.CONNECT_TO_ELASTIC_SEARCH_MESSAGE);
        restHighLevelClient = new RestHighLevelClient(RestClient.builder(new HttpHost(
                elasticUrl, Integer.parseInt(elasticPort), elasticScheme))
                .setHttpClientConfigCallback(httpClientBuilder -> {
                    final CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
                    credentialsProvider.setCredentials(AuthScope.ANY, new UsernamePasswordCredentials(
                            environmentHandler.getEnvironmentData("elasticUserName"),
                            environmentHandler.getEnvironmentData("elasticPassword")));
                    httpClientBuilder.setDefaultCredentialsProvider(credentialsProvider);
                    return httpClientBuilder.setDefaultIOReactorConfig(
                            IOReactorConfig.custom().setIoThreadCount(100).build());
                })
                .setRequestConfigCallback(requestConfigBuilder -> requestConfigBuilder
                        .setConnectTimeout(elasticConnectionTimeout)
                        .setSocketTimeout(elasticSocketTimeout)));
        LOGGER.info(LogConstants.SUCCESS_CONNECT_TO_ELASTIC_SEARCH_MESSAGE);
        return restHighLevelClient;
    }

    public RestHighLevelClient reconnect(int tryInSeconds) {
        for (int i = 0; i < tryInSeconds; i++) {
            try {
                restHighLevelClient.ping(RequestOptions.DEFAULT);
                TimeUnit.SECONDS.sleep(1);
                LOGGER.info(String.format(LogConstants.TRY_TO_RECONNECT_ELASTIC_SEARCH, i + 1));
            } catch (IllegalStateException e1) {
                connect();
                break;
            } catch (InterruptedException | IOException e) {
                LOGGER.error(e);
            }
        }
        return get();
    }

    public void close() {
        if (restHighLevelClient != null) {
            try {
                restHighLevelClient.close();
                restHighLevelClient = null;
                LOGGER.info(LogConstants.CLOSED_ELASTIC_SEARCH_CONNECTION);
            } catch (IOException e) {
                LOGGER.error(e);
            }
        }
    }

}
