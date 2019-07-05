package mn.sample.api.integration;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import javax.inject.Singleton;

import io.micronaut.http.HttpMethod;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.client.RxHttpClient;
import io.reactivex.Flowable;
import io.reactivex.Maybe;
@Singleton 
public class DummyLowLevelClient {

    private final RxHttpClient httpClient;
    private final String uri;

    public DummyLowLevelClient(DummyConfiguration configuration) throws MalformedURLException {  
        
    	URL path = new URL(configuration.getHostname() + ":" + configuration.getPort() + "/hello");
    	this.httpClient = RxHttpClient.create(path);
        uri = path.toString();
    }

    public Maybe<List<BintrayPackage>> fetchPackages() {
        HttpRequest<?> req = HttpRequest.create(HttpMethod.GET, uri);  
        Flowable flowable = httpClient.retrieve(req); 
        return (Maybe<List<BintrayPackage>>) flowable.firstElement(); 
    }

}
