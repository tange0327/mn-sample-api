package mn.sample.api.integration;

import io.micronaut.context.annotation.ConfigurationProperties;
import io.micronaut.context.annotation.Requires;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@ConfigurationProperties(DummyConfiguration.PREFIX)
@Requires(property = DummyConfiguration.PREFIX)
@Data
public class DummyConfiguration {

    public static final String PREFIX = "dummy";
//    public static final String DUMMY_API_URL = "http://dummy.restapiexample.com";

    
    private String apiversion;

    private String organization;

    private String repository;

    private String username;

    private String token;
    
    private String hostname;
    
    private String port;
    
    

    public Map<String, Object> toMap() {
        Map<String, Object> m = new HashMap<>();
        m.put("apiversion", getApiversion());
        m.put("organization", getOrganization());
        m.put("repository", getRepository());
        m.put("username", getUsername());
        m.put("token", getToken());
        m.put("hostname", getHostname());
        m.put("port", getPort());
        return m;
    }
}
