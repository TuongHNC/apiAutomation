package apis;

import apis.endpoints.APIEndpoint;
import constants.LogConstants;
import enumerations.Service;
import environment.EnvironmentHandler;
import exceptions.AutomationTestRunException;
import io.restassured.RestAssured;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import java.util.HashMap;
import java.util.Map;

public class Eureka {

    private static final Logger LOGGER = Logger.getLogger(Eureka.class);

    private static final EnvironmentHandler environment = EnvironmentHandler.getInstance();

    private static final String APP_XML_ELEMENT = "applications.application.instance.app";
    private static final String HOME_PAGE_URL_XML_ELEMENT = "applications.application.instance.homePageUrl";
    private static final String STATUS_XML_ELEMENT = "applications.application.instance.status";

    private static Eureka eureka;
    private static Map endpointMap;

    private Eureka() {
    }

    public static Eureka getInstance() {
        if(eureka == null) {
            eureka = new Eureka();
        }
        return eureka;
    }

    public String getEndpoint(Service service) {
        if(endpointMap == null) {
            endpointMap = getEndpointMap(getAllApplicationRegistered());
        }
        return endpointMap.get(service.getValue()).toString();
    }

    @SuppressWarnings("unchecked")
    private Map getEndpointMap(Response response) {
        if(response.getStatusCode() != 200) {
            throw new AutomationTestRunException(LogConstants.ERROR_WITH_CALLING_TO_EUREKA_SERVER);
        }
        Map map = new HashMap();
        final XmlPath xmlPath = response.getBody().xmlPath();
        for(int i = 0; i < xmlPath.getList(HOME_PAGE_URL_XML_ELEMENT).size(); i++) {
            if (xmlPath.getList(STATUS_XML_ELEMENT).get(i).toString().equals("DOWN")) {
                continue;
            }
            String tmpVal = StringUtils.chop(xmlPath.getList(HOME_PAGE_URL_XML_ELEMENT).get(i).toString());
            if (tmpVal.contains("ip-")){
                String val = tmpVal.replace("ip-", "").replace("-", ".");
                map.put(xmlPath.getList(APP_XML_ELEMENT).get(i), val);
            }
            else
                map.put(xmlPath.getList(APP_XML_ELEMENT).get(i), tmpVal);{
            }
        }
        return map;
    }

    private Response getAllApplicationRegistered() {
        final String eurekaEndpoint = environment.getEnvironmentData("eurekaUrl").concat(APIEndpoint.EUREKA_GET_ALL_APPLICATION_REGISTERED);
        LOGGER.info(LogConstants.CALLING_TO_EUREKA_ENDPOINT.concat(eurekaEndpoint));
        return RestAssured.get(eurekaEndpoint);
    }
}
