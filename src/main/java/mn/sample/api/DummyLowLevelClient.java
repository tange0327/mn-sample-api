package mn.sample.api;

import io.micronaut.core.type.Argument;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.client.RxHttpClient;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.http.uri.UriTemplate;
import io.reactivex.Flowable;
import io.reactivex.Maybe;

import javax.inject.Singleton;
import java.util.List;

@Singleton 
public class DummyLowLevelClient {

    private final RxHttpClient httpClient;
    private final String uri;

    public DummyLowLevelClient(@Client(DummyConfiguration.DUMMY_API_URL) RxHttpClient httpClient,  
                                 DummyConfiguration configuration) {  
        this.httpClient = httpClient;
        String path = "/api/v1/employees";
        uri = UriTemplate.of(path).expand(configuration.toMap());
    }

    public Maybe<List<BintrayPackage>> fetchPackages() {
        HttpRequest<?> req = HttpRequest.GET(uri);  
        Flowable flowable = httpClient.retrieve(req); 
        return (Maybe<List<BintrayPackage>>) flowable.firstElement(); 
    }

}
