package mn.sample.api.application;

import static io.micronaut.http.HttpRequest.GET;

import java.util.List;

import javax.inject.Inject;

import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.client.RxHttpClient;
import io.micronaut.http.client.annotation.Client;
import io.reactivex.Maybe;
import mn.sample.api.BintrayLowLevelClient;
import mn.sample.api.BintrayPackage;
import mn.sample.api.domain.HelloService;

@Controller("/hello")
public class HelloController {
	
//	@Client("http:/dummy.restapiexample.com/api")
//	
//	@Inject RxHttpClient httpClient;
	BintrayLowLevelClient bintrayLowLevelClient;
	
    HelloService helloService;

    HelloController(HelloService helloService, BintrayLowLevelClient bintrayLowLevelClient) {
        this.helloService = helloService;
        this.bintrayLowLevelClient = bintrayLowLevelClient;
    }
	
    @Get(produces = MediaType.TEXT_PLAIN)
    public Maybe<List<BintrayPackage>> index() {
//        return "hello world!!";
//        return bintrayLowLevelClient.toBlocking().retrieve("/v1/employees/");
        return bintrayLowLevelClient.fetchPackages();
    }
    
    @Get(uri = "/compute/{number}",processes = MediaType.TEXT_PLAIN)
    public String compute(Integer number) {
        return String.valueOf(helloService.compute(number));
    }

//    @Get(uri = "/http",processes = MediaType.TEXT_PLAIN)
//    public Maybe<String> hello() { 
//        return bintrayLowLevelClient.retrieve(GET("/v1/employees/") )
//                         .firstElement(); 
//    }

}
