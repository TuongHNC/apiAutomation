package apis;

import database.postgresql.DBConnection;
import dataflow.ComponentTestDataFlow;
import dataflow.DataByFlow;
import dataflow.imp.DataByFlowImplement;
import enumerations.ApplicationType;
import enumerations.dataflowenum.ComponentDataSet;
import environment.APIAutomationListener;
import exceptions.AutomationTestRunException;
import model.GenericModel;
import org.apache.log4j.Logger;
import org.testng.annotations.*;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.util.*;
import java.util.function.Supplier;

@Listeners({APIAutomationListener.class})
public abstract class BaseTest extends AbstractBaseRequest {
    private static final Logger LOGGER = Logger.getLogger(BaseTest.class);
    private DataByFlow dataByFlow = new DataByFlowImplement();
    protected static Connection connection;
    private Map<ApplicationType, Supplier<Enum>> enumerationSupplier = new HashMap<ApplicationType, Supplier<Enum>>() {
        {
            this.put(ApplicationType.COMPONENT_TEST, () -> {
                return ((ComponentTestDataFlow)BaseTest.this.getAnnotateFromTestClass()).init();
            });
        }
    };
    private Map<ApplicationType, Supplier<GenericModel>> dataSupplier = new HashMap<ApplicationType, Supplier<GenericModel>>() {
        {
            this.put(ApplicationType.COMPONENT_TEST, () -> {
                return BaseTest.this.dataByFlow.onComponentDataFlow(((ComponentTestDataFlow)BaseTest.this.getAnnotateFromTestClass()).init());
            });
        }
    };

    public BaseTest() {
    }

    @BeforeClass
    public void setUpClass() {
        LOGGER.info("START THE TEST >>>> " + this.getClass().getName());
//        connection = DBConnection.getInstance().get();
    }

    @BeforeMethod
    public void setUpMethod() {
    }

    @AfterMethod
    public void tearDownMethod() {
    }

    @AfterClass
    public void tearDownClass() {
//        DBConnection.getInstance().close();
        this.dataByFlow.destroyAll();
        LOGGER.info("END THE TEST >>>> " + this.getClass().getName());
    }

    //------------------------------------------------------------------------------------------------------------------

    protected String getPreConditionData(String key) {
        if (this.isAll()) {
            throw new AutomationTestRunException("You are initialized data for ALL so you must use getPreConditionData with return type is GenericModel!!!");
        } else {
            try {
                return this.getPreConditionData().getProperties().get(key).toString();
            } catch (NullPointerException var3) {
                throw new AutomationTestRunException(String.format("No data with key [%s] was found!!!", key));
            }
        }
    }

    protected GenericModel getPreConditionData() {
        return (GenericModel)((Supplier)this.dataSupplier.get(ApplicationType.of(this.getAnnotateFromTestClass().annotationType().getName()))).get();
    }

    protected HashMap getHashMapPreConditionData(String key) {
        if (this.isAll()) {
            throw new AutomationTestRunException("You are initialized data for ALL so you must use getPreConditionData with return type is GenericModel!!!");
        } else {
            try {
                return (HashMap)this.getPreConditionData().getProperties().get(key);
            } catch (NullPointerException var3) {
                throw new AutomationTestRunException(String.format("No data with key [%s] was found!!!", key));
            }
        }
    }

    private Annotation getAnnotateFromTestClass() {
        Annotation[] annotations = this.getClass().getAnnotations();
        if (annotations.length > 1) {
            throw new AutomationTestRunException("Except one test data annotation per test class only. Please remove the additional one!!!");
        } else {
            return (Annotation) Arrays.stream(annotations).filter((annotation) -> {
                return annotation.annotationType().getName().contains("DataFlow");
            }).findAny().orElseThrow(() -> {
                return new AutomationTestRunException("Please add data annotation to initialize data for your test!!!");
            });
        }
    }

    private Method getTestMethod() {
        return (Method)Arrays.stream(this.getClass().getDeclaredMethods()).filter((method) -> {
            return method.isAnnotationPresent(Test.class);
        }).findAny().orElseThrow(() -> {
            return new AutomationTestRunException("No test method with @Test was found. Please check your test!!!");
        });
    }

    private boolean isAll() {
        List<Enum> enumList = new ArrayList();
        enumList.add(ComponentDataSet.ALL);
        return enumList.stream().anyMatch((anEnum) -> {
            return anEnum.equals(((Supplier)this.enumerationSupplier.get(ApplicationType.of(this.getAnnotateFromTestClass().annotationType().getName()))).get());
        });
    }
}
