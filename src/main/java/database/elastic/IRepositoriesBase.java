package database.elastic;

import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.index.reindex.BulkByScrollResponse;
import org.elasticsearch.search.builder.SearchSourceBuilder;

import java.util.List;
import java.util.Map;

public interface IRepositoriesBase {

    SearchResponse getData(String index, String term, String value);

    SearchResponse getData(String index, String term, String value, int size);

    SearchResponse getData(String index, TermValue... termValues);

    SearchResponse getData(String index, int size, TermValue... termValues);

    SearchResponse getData(String index, List<WildCard> wildCards, TermValue... termValues);

    SearchResponse getDataByRange(String index, String rangeField, long rangeFrom, long rangeTo, TermValue... termValues);

    SearchResponse getDataByRange(String index, int size, String rangeField, long rangeFrom, long rangeTo, TermValue... termValues);

    SearchResponse search(String index, SearchSourceBuilder yourSearchBuilder);

    SearchResponse search(SearchRequest searchRequest, RequestOptions option);

    UpdateResponse updateResponse(String index, String docId, String docContent);

    BulkByScrollResponse deleteData(String index, TermValue... termValues);

    BulkByScrollResponse deleteData(String index, String name, String prefix);

    BulkResponse insertData(String index, String type, String id, Map<String, Object> map);

}
