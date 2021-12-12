package database.elastic;

import constants.LogConstants;
import org.apache.log4j.Logger;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.reindex.BulkByScrollResponse;
import org.elasticsearch.index.reindex.DeleteByQueryRequest;
import org.elasticsearch.search.builder.SearchSourceBuilder;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class RepositoriesBase implements IRepositoriesBase {

    private static final Logger LOGGER = Logger.getLogger(RepositoriesBase.class);

    protected RestHighLevelClient elasticSearch;

    /**
     * get data with maximum size is 1000 records
     *
     * @param index search index
     * @param term    term for search query
     * @param value   value of term
     * @return SearchResponse contain the data, lets handle it by scripting level
     */
    public SearchResponse getData(String index, String term, String value) {
        return getData(index, term, value, 1000);
    }

    public SearchResponse getData(String index, String term, String value, int size) {
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        BoolQueryBuilder boolQueryBuilder = new BoolQueryBuilder();
        searchSourceBuilder.size(size);
        boolQueryBuilder.must(QueryBuilders.termQuery(term, value));
        searchSourceBuilder.query(boolQueryBuilder);
        return search(index, searchSourceBuilder);
    }

    public SearchResponse getData(String index, TermValue... termValues) {
        return getData(index, 1000, termValues);
    }

    public SearchResponse getData(String index, int size, TermValue... termValues) {
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.size(size);
        searchSourceBuilder.query(boolQueryBuilder(termValues));
        return search(index, searchSourceBuilder);
    }

    public SearchResponse getData(String index, List<WildCard> wildCards ,TermValue... termValues) {
        return getData(index, 1000, wildCards, termValues);
    }

    public SearchResponse getData(String index, int size, List<WildCard> wildCards, TermValue... termValues) {
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.size(size);
        searchSourceBuilder.query(wildcardQueryBuilder(wildCards,termValues));
        return search(index, searchSourceBuilder);
    }

    public SearchResponse getDataByRange(String index, String rangeField, long rangeFrom, long rangeTo, TermValue... termValues) {
        return getDataByRange(index, 1000, rangeField, rangeFrom, rangeTo, termValues);
    }

    public SearchResponse getDataByRange(String index, int size, String rangeField, long rangeFrom, long rangeTo, TermValue... termValues) {
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.size(size);
        searchSourceBuilder.query(this.boolQueryBuilder(termValues)
                .must(QueryBuilders.rangeQuery(rangeField).from(rangeFrom).to(rangeTo)));
        return this.search(index, searchSourceBuilder);
    }

    public SearchResponse search(String index, SearchSourceBuilder yourSearchBuilder) {
        SearchRequest searchRequest = new SearchRequest(index);
        searchRequest.source(yourSearchBuilder);
        return search(searchRequest, RequestOptions.DEFAULT);
    }

    public SearchResponse search(SearchRequest searchRequest, RequestOptions option) {
        try {
            return elasticSearch.search(searchRequest, option);
        } catch (IOException e) {
            LOGGER.error(LogConstants.FAILED_EXECUTE_ELASTIC_QUERY_MESSAGE + e);
        }
        return null;
    }

    public UpdateResponse updateResponse(String index, String docId, String docContent) {

        UpdateRequest updateRequest = new UpdateRequest(index,docId);
        updateRequest.doc(docContent, XContentType.JSON);

        try {
            return elasticSearch.update(updateRequest, RequestOptions.DEFAULT);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public BulkByScrollResponse deleteData(String index, TermValue... termValues) {
        DeleteByQueryRequest deleteByQueryRequest = new DeleteByQueryRequest();
        deleteByQueryRequest.setQuery(boolQueryBuilder(termValues));
        deleteByQueryRequest.indices(index);
        return this.delete(deleteByQueryRequest, RequestOptions.DEFAULT);
    }

    public BulkByScrollResponse deleteData(String index, String name, String prefix) {
        DeleteByQueryRequest deleteByQueryRequest = new DeleteByQueryRequest();
        deleteByQueryRequest.setQuery(QueryBuilders.prefixQuery(name, prefix));
        deleteByQueryRequest.indices(index);
        deleteByQueryRequest.setAbortOnVersionConflict(false);
        try {
            return elasticSearch.deleteByQuery(deleteByQueryRequest, RequestOptions.DEFAULT);
        } catch (IOException e) {
            LOGGER.error("Failed executing Elastic delete query, please check your query for >>>> " + e);
            return null;
        }
    }

    private BulkByScrollResponse delete(DeleteByQueryRequest deleteByQueryRequest, RequestOptions option) {
        try {
            return elasticSearch.deleteByQuery(deleteByQueryRequest, option);
        } catch (IOException var4) {
            LOGGER.error("Failed executing Elastic delete query, please check your query for >>>> " + var4);
            return null;
        }
    }

    public BulkResponse insertData(String index, String type, String id, Map<String, Object> map) {
        BulkRequest bulkRequest = new BulkRequest();
        IndexRequest indexRequest = new IndexRequest(index, type, id).source(map);
        bulkRequest.add(indexRequest);
        try {
            return elasticSearch.bulk(bulkRequest, RequestOptions.DEFAULT);
        } catch (IOException var4) {
            LOGGER.error("Failed executing Elastic insert query, please check your query for >>>> " + var4);
            return null;
        }
    }

    private BoolQueryBuilder boolQueryBuilder(TermValue... termValues) {
        BoolQueryBuilder boolQueryBuilder = new BoolQueryBuilder();
        for (TermValue tv : termValues)
            if (tv.getValue() == null) {
                boolQueryBuilder.must(QueryBuilders.termsQuery(tv.getKey(), tv.getValues()));
            } else {
                boolQueryBuilder.must(QueryBuilders.termsQuery(tv.getKey(), tv.getValue()));
            }
        return boolQueryBuilder;
    }

    private BoolQueryBuilder wildcardQueryBuilder(List<WildCard> wildCards, TermValue... termValues) {
        BoolQueryBuilder boolQueryBuilder = new BoolQueryBuilder();
        for (TermValue tv : termValues)
            if (tv.getValue() == null) {
                boolQueryBuilder.must(QueryBuilders.termsQuery(tv.getKey(), tv.getValues()));
            } else {
                boolQueryBuilder.must(QueryBuilders.termsQuery(tv.getKey(), tv.getValue()));
            }
        for (WildCard wc : wildCards) {
            boolQueryBuilder.must(QueryBuilders.wildcardQuery(wc.getKey(), wc.getValue()));
        }
        return  boolQueryBuilder;
    }

}
