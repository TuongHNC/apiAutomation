package constants;

public final class LogConstants {

    private LogConstants() {
    }

    //Framework log
    public static String FRAMEWORK_CONFIG_FILE_PATH = "src/main/resources/frameworkconfig.properties";

    //Environment Log
    public static final String CANNOT_GET_PROPERTIES_FROM_FILE = "Cannot get properties from file, please check your file path >>>> ";

    public static final String TARGET_PROPERTIES_NULL = "Target properties is null >>> ";

    public static final String LOAD_CONFIGURATION_PROPERTIES_SUCCESS= "Loaded configuration properties from external file >>> ";

    //Data log
    public static final String DATA_SET_IS_NOT_SUPPORTED = "Data set %s is not supported!";

    public static final String INITIALIZING_TEST_DATA_BY_RESOURCE_FILE = "Initializing test data by resource file >>> ";

    public static final String FORCE_TO_DESTROY_DATA_PROCESS = "Forcing to destroy all data...";

    public static final String FORCE_TO_DESTROY_DATA_COMPLETED = "Completed destroying all data >>>> ";

    //Convert log
    public static final String CONVERT_FILE_PATH = "File path >>>> ";

    public static final String CONVERT_JSON_FILE_TO_OBJECT = "Failed to convert json file to object >>>> ";

    public static final String CONVERT_YML_FILE_TO_OBJECT = "Failed to convert yml file to object >>>> ";

    public static final String CONVERT_JSON_FILE_TO_MAP = "Failed to convert json file to map >>>> ";

    public static final String CONVERT_GENERIC_OBJECT_TO_OBJECT = "Failed to convert generic object to object >>>> ";

    //Environment configuration log
    public static final String ENVIRONMENT_INITIALIZATION_MESSAGE = "******** [API AUTOMATION] INITIALIZING TEST ENVIRONMENT [%s] ********";

    public static final String FAILED_TO_INITIAL_TEST_ENVIRONMENT = "Failed to initializing test environment, please check your yaml config file!!!";

    public static final String TEST_ENVIRONMENT_DOES_NOT_EXISTS = "The test environment [%s] doesn't exists. Please input the correct one! Error >>>> ";

    //Base test log
    public static final String START_THE_TEST = "START THE TEST >>>> ";

    public static final String END_THE_TEST = "END THE TEST >>>> ";

    public static final String ERROR_WITH_CALLING_INVALID_DATA_METHOD = "You are initialized data for ALL so you must use getPreConditionData with return type is GenericModel!!!";

    public static final String ERROR_WITH_EXCEPT_ONE_TEST_DATA_ANNOTATION_PER_TEST_ONLY = "Except one test data annotation per test class only. Please remove the additional one!!!";

    public static final String ERROR_WITH_MISSING_DATA_ANNOTATION_FOR_TEST = "Please add data annotation to initialize data for your test!!!";

    public static final String ERROR_WITH_NO_TEST_ANNOTATION_OF_TEST_NG = "No test method with @Test was found. Please check your test!!!";

    public static final String ERROR_WITH_NO_DATA_WITH_KEY = "No data with key [%s] was found!!!";

    //Mock data log
    public static final String DESTROY_RESTITO_MOCK = "Destroying Restito Mock >>>>";

    public static final String FREE_RESTITO_MOCK = "Free all Restito Mock >>>>";

    public static final String START_RESTITO_MOCK_WITH_CONFIG = "Starting Restito Mock with configs >>>> ";

    public static final String HOST_NAME_AND_PORT = "Hostname and port: ";

    public static final String BASE_PATH = "Base path: ";

    public static final String DATA_SOURCE = "Data source: ";

    //Report log
    public static final String INITIALIZE_EXTENT_REPORT_MANAGER = "Initializing Extent report manager >>>> ";

    public static final String GENERATE_REPORT_FAILED = "Generate report failed!!! Please check your reporter again!!!";

    public static final String GENERATE_REPORT_COMPLETED = "Generate automation report completed!!!";

    //Listener log
    public static final String THE_TEST_STATUS_LOG = "%s >>> %s";

    //Eureka log
    public static final String CALLING_TO_EUREKA_ENDPOINT = "Calling to Eureka endpoint >>>> ";

    public static final String ERROR_WITH_CALLING_TO_EUREKA_SERVER = "Error with calling to Eureka server. The status code >>>> ";

    //Database log
    public static final String CONNECT_TO_DB_MESSAGE = "Connecting to Posgre SQL database >>>> ";

    public static final String SUCCESS_CONNECT_TO_POSGRE_SQL_DB_MESSAGE = "Success";

    public static final String FAILED_CONNECTED_DB_MESSAGE = "Connect to database failure! >>>> ";

    public static final String QUERY_STRING_MESSAGE = "Reading record with query string >>>>   ";

    public static final String QUERY_RESULT_MESSAGE = "Result of database query >>>>   ";

    public static final String FAILED_QUERY_MESSAGE = "Error to execute database query >>>>   ";

    public static final String DELETE_RESULT_MESSAGE = "Deleted data with %s row(s) affected >>>> ";

    public static final String CLOSED_CONNECTED_DATABASE_MESSAGE = "Closed database connection >>>>";

    public static final String FAILED_CLOSED_CONNECTED_DATABASE_MESSAGE = "Something went wrong when closing database connection >>>> ";

    //Elastic query log
    public static final String CONNECT_TO_ELASTIC_SEARCH_MESSAGE = "Connecting to Elastic Search query >>>> ";

    public static final String CONNECT_TO_AWS_ELASTIC_SEARCH_MESSAGE = "Connecting to AWS Elastic Search query >>>> ";

    public static final String CONNECT_TO_ELASTIC_SEARCH_7_MESSAGE = "Connecting to Elastic Search 7 >>>> ";

    public static final String SUCCESS_CONNECT_TO_ELASTIC_SEARCH_MESSAGE = "Success";

    public static final String FAILED_EXECUTE_ELASTIC_QUERY_MESSAGE = "Failed executing Elastic query, please check your query for >>>> ";

    public static final String CLOSED_ELASTIC_SEARCH_CONNECTION = "Closed elastic search connection >>>>";

    public static final String TRY_TO_RECONNECT_ELASTIC_SEARCH = "Try to reconnect Elastic Search In >>>> %d second(s)";

    public static final String SUCCESS_CONNECT_TO_AWS_ELASTIC_SEARCH_MESSAGE = "Connected to AWS Elastic Search successful";

    public static final String SUCCESS_CONNECT_TO_ELASTIC_SEARCH_7_MESSAGE = "Connected to Elastic Search 7 successful";

    public static final String CLOSED_AWS_ELASTIC_SEARCH_CONNECTION = "Closed AWS elastic search connection!!!";

    public static final String TRY_TO_RECONNECT_AWS_ELASTIC_SEARCH = "Try to reconnect AWS Elastic Search in >>>> %d second(s)";

    public static final String TRY_TO_RECONNECT_ELASTIC_SEARCH_7 = "Try to reconnect Elastic Search 7 in >>>> %d second(s)";

    //Hangouts log
    public static final String ERROR_TO_NOTIFY_THE_TEST_RESULT_ON_HANGOUTS = "Error to notify the test result on Hangouts chat!";

}
