package model.configurationmodel;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EnvironmentModel {

    private String envConfigUrl;

    private String serviceUrl;

    private String gatewayUrl;

    private String smGatewayUrl;

    private String pplGatewayUrl;

    private String ppGatewayUrl;

    private String mc2GatewayUrl;

    private String keycloakGatewayUrl;

    private String mockUrl;

    private String eurekaUrl;

    private String clientSecretToken;

    private String apiKeyToken;

    private String ppApiKey;

    private String dbUrl;

    private String dbUserName;

    private String dbPassword;

    private String elasticUrl;

    private int elasticPort;

    private String elasticScheme;

    private String elasticUserName;

    private String elasticPassword;

    private int elasticConnectionTimeout;

    private int elasticSocketTimeout;

    private String broker;

    private String avlEventTopic;

    private String tenantId;

    private String elastic7Url;

    private String elastic7Port;

}
