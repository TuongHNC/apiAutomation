package environment;

public class Environment {
    public Environment(String envName, String envURL)
    {
        this.envName = envName;
        this.URL = envURL;
    }

    private String envName;
    private String URL;

    public void setEnvName(String envName) {
        this.envName = envName;
    }

    public void setURL(String URL) {
        this.URL = URL;
    }

    public String getEnvName() {
        return envName;
    }

    public String getURL() {
        return URL;
    }
}

