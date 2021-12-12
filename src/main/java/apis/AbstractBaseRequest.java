package apis;

import com.xebialabs.restito.server.StubServer;
import environment.EnvironmentHandler;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import mock.MockService;
import mock.impl.RestitoMockServiceImpl;
import utilities.RequestUtil;

/**
 * All api tests need to be extended this class for specific request initialization
 * This class includes mock instance manager and base http request caller
 */
public abstract class AbstractBaseRequest {

    protected static final EnvironmentHandler environmentHandler = EnvironmentHandler.getInstance();
    protected static final Eureka eureka = Eureka.getInstance();

    private MockService<StubServer> restitoMockService = new RestitoMockServiceImpl();

    /**
     * Want to create a mock service? Use it!
     *
     * @param hostName   example: http://localhost
     * @param basePath   example: /systemManagement/account/1
     * @param dataSource path to data source, only accept .json, .xml file
     */
    protected void upMock(final String hostName, final String basePath, final String dataSource) {
        restitoMockService.up(hostName, basePath, dataSource);
    }

    protected void upMock(final String basePath, final String dataSource) {
        upMock(environmentHandler.getEnvironmentData("mockUrl"), basePath, dataSource);
    }

    protected StubServer getMockInstance() {
        return restitoMockService.getInstance();
    }

    protected void downMock() {
        restitoMockService.down();
    }

    protected Response sendAGetRequest(RequestSpecification additionalSpec) {
        return RequestUtil.get(additionalSpec);
    }

    protected <T> T sendAGetRequest(RequestSpecification additionalSpec, Class<T> clazz) {
        return RequestUtil.get(additionalSpec, clazz);
    }

    protected Response sendAPostRequest(RequestSpecification additionalSpec) {
        return RequestUtil.post(additionalSpec);
    }

    protected <T> T sendAPostRequest(RequestSpecification additionalSpec, Class<T> clazz) {
        return RequestUtil.post(additionalSpec, clazz);
    }

    protected Response sendAPutRequest(RequestSpecification additionalSpec) {
        return RequestUtil.put(additionalSpec);
    }

    protected Response sendAPatchRequest(RequestSpecification additionalSpec) {
        return RequestUtil.patch(additionalSpec);
    }

    protected <T> T sendAPutRequest(RequestSpecification additionalSpec, Class<T> clazz) {
        return RequestUtil.put(additionalSpec, clazz);
    }

    protected Response sendADeleteRequest(RequestSpecification additionalSpec) {
        return RequestUtil.delete(additionalSpec);
    }

    protected Response sendADeleteRequest(RequestSpecification additionalSpec, String param) {
        return RequestUtil.delete(additionalSpec, param);
    }
}
