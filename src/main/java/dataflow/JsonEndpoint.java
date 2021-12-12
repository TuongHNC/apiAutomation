package dataflow;

public class JsonEndpoint {

    private JsonEndpoint() {
    }

    private static final String USER_DIRECTORY = System.getProperty("user.dir");

    public static final String COMPONENT_TEST_DATA_PATH = USER_DIRECTORY + "/src/main/resources/assets/testdataassets/%s/component_test_data.json";
}
