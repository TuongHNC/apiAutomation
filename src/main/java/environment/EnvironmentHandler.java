package environment;

import constants.LogConstants;
import exceptions.AutomationTestRunException;
import exceptions.AutomationUtilException;
import model.GenericModel;
import org.apache.log4j.Logger;
import utilities.ConvertUtil;

public class EnvironmentHandler {
    private static final Logger LOGGER = Logger.getLogger(EnvironmentHandler.class);

    private static EnvironmentHandler instance;
    private static GenericModel environmentGenericModel;

    private EnvironmentHandler(Environment targetEnv) {
        Environment environment;

        if (targetEnv != null) {
            environment = targetEnv;
        } else {
            environment = new Environment("STAGE", "src/main/resources/environments/staging.yml");
        }

        LOGGER.info(String.format(LogConstants.ENVIRONMENT_INITIALIZATION_MESSAGE, environment.getEnvName().toUpperCase()));

        try {
            environmentGenericModel = ConvertUtil.convertYmlFileToObject(environment.getURL(), GenericModel.class);
        } catch (AutomationUtilException e) {
            throw new AutomationTestRunException(LogConstants.FAILED_TO_INITIAL_TEST_ENVIRONMENT, e);
        }
    }

    public static EnvironmentHandler getInstance(Environment env) {
        if (instance == null) {
            instance = new EnvironmentHandler(env);
        }
        return instance;
    }

    public static EnvironmentHandler getInstance() {
        if (instance == null) {
            instance = new EnvironmentHandler(null);
        }
        return instance;
    }

    public String getEnvironmentData(String key) {
        return environmentGenericModel.getProperties().get(key).toString();
    }
}
