package enumerations;

import exceptions.AutomationTestRunException;
import org.apache.commons.lang.StringUtils;

import java.util.stream.Stream;

public enum ApplicationType {

    PARENT_PORTAL("dataflow.ParentPortalDataFlow"),
    PARENT_PORTAL_LITE("dataflow.ParentPortalLiteDataFlow"),
    SYSTEM_MANAGEMENT("dataflow.SystemManagementDataFlow"),
    INSIGHT("dataflow.InsightFlow"),
    COMPONENT_TEST("dataflow.ComponentTestDataFlow");

    private final String url;

    ApplicationType(String url) {
        this.url = url;
    }

    public String getUrl() {
        return this.url;
    }

    public static Stream<ApplicationType> stream() {
        return Stream.of(ApplicationType.values());
    }

    public static ApplicationType of(String value) {
        if (StringUtils.isBlank(value)) {
            throw new AutomationTestRunException("Blank string");
        }
        return ApplicationType.stream()
                .filter(applicationType -> applicationType.getUrl().equals(value))
                .findFirst()
                .orElseThrow(() -> new AutomationTestRunException("Invalid application type, please check again!!!"));
    }
}
