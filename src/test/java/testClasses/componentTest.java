package testClasses;

import apis.BaseTest;
import dataflow.ComponentTestDataFlow;
import enumerations.dataflowenum.ComponentDataSet;
import org.testng.annotations.Test;

@ComponentTestDataFlow(init = ComponentDataSet.TOOLSQABOOKSTORE)
public class componentTest extends BaseTest {

    @Test(groups = {"positive"})
    public void testGetDataFromJsonFile(){
        String username = getPreConditionData("username");
        String password = getPreConditionData("password");

        System.out.println("Username: " + username);
        System.out.println("Password: " + password);
    }
}
