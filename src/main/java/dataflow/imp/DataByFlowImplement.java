package dataflow.imp;

import constants.LogConstants;
import dataflow.DataByFlow;
import dataflow.JsonEndpoint;
import enumerations.EnvironmentType;
import enumerations.dataflowenum.ComponentDataSet;
import model.GenericModel;
import org.apache.log4j.Logger;
import utilities.ConvertUtil;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public class DataByFlowImplement implements DataByFlow {

    private static final Logger LOGGER = Logger.getLogger(DataByFlowImplement.class);

    private static final ThreadLocal<GenericModel> threadLocalAllComponentData = new ThreadLocal<>();
    private static final ThreadLocal<GenericModel> threadLocalComponentData = new ThreadLocal<>();

    @Override
    public GenericModel onComponentDataFlow() {
        if (threadLocalAllComponentData.get() == null) {
            LOGGER.info(LogConstants.INITIALIZING_TEST_DATA_BY_RESOURCE_FILE);
            threadLocalAllComponentData.set(ConvertUtil.convertJsonFileToObject(
                    String.format(JsonEndpoint.COMPONENT_TEST_DATA_PATH, getEnvironmentName()), GenericModel.class));
        }
        return threadLocalAllComponentData.get();
    }

    @Override
    public GenericModel onComponentDataFlow(ComponentDataSet componentDataSet) {
        if (!componentDataSetSupplier.containsKey(componentDataSet)) {
            throw new IllegalArgumentException(String.format(LogConstants.DATA_SET_IS_NOT_SUPPORTED, componentDataSet.getValue()));
        }

        threadLocalComponentData.set(componentDataSetSupplier.get(componentDataSet).get());
        return threadLocalComponentData.get();
    }

    @Override
    public void destroyAll() {
        LOGGER.info(LogConstants.FORCE_TO_DESTROY_DATA_PROCESS);

        componentDataSetSupplier.clear();

        threadLocalAllComponentData.set(null);
        threadLocalComponentData.set(null);

        LOGGER.info(LogConstants.FORCE_TO_DESTROY_DATA_COMPLETED);
    }

    //------------------------------------------------------------------------------------------------------------------

    private String getEnvironmentName() {
        String currentEnv = System.getProperty("environment");
        if (currentEnv == null) {
            return EnvironmentType.STAGE.name().toLowerCase();
        } else {
            return currentEnv.toLowerCase();
        }
    }

    private Map<ComponentDataSet, Supplier<GenericModel>> componentDataSetSupplier = new HashMap<ComponentDataSet, Supplier<GenericModel>>() {
        {
            for (ComponentDataSet c : ComponentDataSet.values()) {
                put(c, () -> ConvertUtil.convertGenericObjectToObject(
                        onComponentDataFlow().getProperties().get(c.getValue()), GenericModel.class));
            }
        }
    };
}
