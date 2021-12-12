package utilities;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

public class RequestSpecBuilderUtil {

    private RequestSpecBuilderUtil() {
    }

    /**
     * This method is to get request specification by Json with Content Type = "application/json"
     *
     * @return RequestSpecification
     */
    public static RequestSpecification specWithJson(RequestSpecification spec) {
        return specWithJson(0, spec);
    }

    /**
     * This method is to get request specification by Json
     *
     * @param contentTypeNumber 0 for "application/json", 1 for "application/javascript",
     *                          2 for "text/javascript"
     * @return RequestSpecification
     */
    public static RequestSpecification specWithJson(int contentTypeNumber, RequestSpecification spec) {
        return new RequestSpecBuilder()
                .setContentType(ContentType.JSON.getContentTypeStrings()[contentTypeNumber])
                .addRequestSpecification(spec).build();
    }

    /**
     * This method is to get request specification builder by Xml request
     * and use relaxed HTTP validation with SSLContext protocol
     *
     * @param contentTypeNumber 0 for "application/xml", 1 for "text/xml",
     *                          2 for "application/xhtml+xml"
     * @return RequestSpecification
     */
    public static RequestSpecification specWithXml(int contentTypeNumber, RequestSpecification spec) {
        return new RequestSpecBuilder()
                .setRelaxedHTTPSValidation()
                .setContentType(ContentType.XML.getContentTypeStrings()[contentTypeNumber])
                .addRequestSpecification(spec).build();
    }

    /**
     * This method is to get request specification for application/x-www-form-urlencoded request
     * and use relaxed HTTP validation with SSLContext protocol
     *
     * @return RequestSpecification
     */
    public static RequestSpecification specWithUrlencoded(RequestSpecification spec) {
        return new RequestSpecBuilder()
                .setRelaxedHTTPSValidation()
                .setContentType(ContentType.URLENC)
                .addRequestSpecification(spec).build();
    }
}
