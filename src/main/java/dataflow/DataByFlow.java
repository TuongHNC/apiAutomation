package dataflow;

import enumerations.dataflowenum.ComponentDataSet;
import model.GenericModel;

public interface DataByFlow {

    GenericModel onComponentDataFlow();

    GenericModel onComponentDataFlow(ComponentDataSet componentDataSet);

    void destroyAll();
}
