package utilities;

import io.restassured.internal.RequestSpecificationImpl;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import static io.restassured.RestAssured.given;

public class RequestUtil {

    private RequestUtil() {
    }

    private static final Logger LOGGER = LogManager.getLogger(RequestUtil.class);

    private static final String REQUEST_WITH_MESSAGE = "Sending a request with >>>>>>>>>";
    private static final String REQUEST_ENDPOINT_MESSAGE = "Endpoint >>> ";
    private static final String REQUEST_HEADERS_MESSAGE = "Headers >>> ";
    private static final String REQUEST_BODY_MESSAGE = "Request body >>> ";
    private static final String RESPONSE_STATUS_CODE_MESSAGE = "Response status code >>> ";
    private static final String RESPONSE_BODY_MESSAGE = "Response body >>> ";

    public static Response put(RequestSpecification requestSpecification) {
        showRequestInformation(requestSpecification);
        Response response = given().relaxedHTTPSValidation()
                .spec(requestSpecification)
                .when().put();
        showResponseInformation(response);
        return response;
    }

    public static <T> T get(RequestSpecification requestSpecification, Class<T> clazz) {
        showRequestInformation(requestSpecification);
        Response response = given().relaxedHTTPSValidation()
                .spec(requestSpecification)
                .when().get();
        showResponseInformation(response);
        return response.getBody().as(clazz);
    }

    public static Response get(RequestSpecification requestSpecification) {
        showRequestInformation(requestSpecification);
        Response response = given().relaxedHTTPSValidation()
                .spec(requestSpecification)
                .when().get();
        showResponseInformation(response);
        return response;
    }

    public static Response post(RequestSpecification requestSpecification) {
        showRequestInformation(requestSpecification);
        Response response = given().relaxedHTTPSValidation()
                .spec(requestSpecification)
                .when().post();
        showResponseInformation(response);
        return response;
    }

    public static <T> T post(RequestSpecification requestSpecification, Class<T> clazz) {
        showRequestInformation(requestSpecification);
        Response response = given().relaxedHTTPSValidation()
                .spec(requestSpecification)
                .when().post();
        showResponseInformation(response);
        return response.getBody().as(clazz);
    }

    public static <T> T put(RequestSpecification requestSpecification, Class<T> clazz) {
        showRequestInformation(requestSpecification);
        Response response = given().relaxedHTTPSValidation()
                .spec(requestSpecification)
                .when().put();
        showResponseInformation(response);
        return response.getBody().as(clazz);
    }

    public static <T> T get(RequestSpecification requestSpecification, String getParam, Class<T> clazz) {
        showRequestInformation(requestSpecification);
        Response response = given().relaxedHTTPSValidation()
                .spec(requestSpecification)
                .when().get(getParam);
        showResponseInformation(response);
        return response.getBody().as(clazz);
    }

    public static Response delete(RequestSpecification requestSpecification) {
        showRequestInformation(requestSpecification);
        Response response = given().relaxedHTTPSValidation()
                .spec(requestSpecification)
                .when().delete();
        showResponseInformation(response);
        return response;
    }

    public static Response delete(RequestSpecification requestSpecification, String param) {
        showRequestInformation(requestSpecification);
        Response response = given().relaxedHTTPSValidation()
                .spec(requestSpecification)
                .when().delete(param);
        showResponseInformation(response);
        return response;
    }

    public static Response patch(RequestSpecification requestSpecification) {
        showRequestInformation(requestSpecification);
        Response response = given().relaxedHTTPSValidation()
                .spec(requestSpecification)
                .when().patch();
        showResponseInformation(response);
        return response;
    }

    private static void showRequestInformation(RequestSpecification requestSpecification) {
        final RequestSpecificationImpl requestSpecificationImpl = ((RequestSpecificationImpl) requestSpecification);
        LOGGER.info(REQUEST_WITH_MESSAGE);
        LOGGER.info(REQUEST_ENDPOINT_MESSAGE + requestSpecificationImpl.getBaseUri() + requestSpecificationImpl.getBasePath());
        LOGGER.info(REQUEST_HEADERS_MESSAGE + requestSpecificationImpl.getHeaders().asList().toString());
        if(requestSpecificationImpl.getBody() != null) {
            LOGGER.info(REQUEST_BODY_MESSAGE + requestSpecificationImpl.getBody().toString());
        }
    }

    private static void showResponseInformation(Response response) {
        LOGGER.info(RESPONSE_STATUS_CODE_MESSAGE + response.getStatusCode());
        LOGGER.info(RESPONSE_BODY_MESSAGE + response.getBody().print());
    }
}
