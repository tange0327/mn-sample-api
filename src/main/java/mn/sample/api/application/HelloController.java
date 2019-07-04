package mn.sample.api.application;

import java.util.List;

import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.reactivex.Maybe;
import mn.sample.api.BintrayLowLevelClient;
import mn.sample.api.BintrayPackage;
import mn.sample.api.DummyLowLevelClient;
import mn.sample.api.domain.HelloService;

@Controller("/hello")
public class HelloController {
	
	 HelloService helloService;
	 BintrayLowLevelClient bintrayLowLevelClient;
	 DummyLowLevelClient dummyLowLevelClient;

    HelloController(HelloService helloService, BintrayLowLevelClient bintrayLowLevelClient, DummyLowLevelClient dummyLowLevelClient) {
        this.helloService = helloService;
        this.bintrayLowLevelClient = bintrayLowLevelClient;
        this.dummyLowLevelClient = dummyLowLevelClient;
    }
	
    @Get(value = "/bintray", produces = MediaType.TEXT_PLAIN)
    public Maybe<List<BintrayPackage>> pindexackagesWithLowLevelClient() {
        return bintrayLowLevelClient.fetchPackages();
    }
    
    @Get(value = "/dummy", produces = MediaType.APPLICATION_JSON)
    public Maybe<List<BintrayPackage>> packagesWithLowLevelClient() {
        return dummyLowLevelClient.fetchPackages();
    }
    
    @Get(uri = "/compute/{number}",processes = MediaType.TEXT_PLAIN)
    public String compute(Integer number) {
        return String.valueOf(helloService.compute(number));
    }

//    @Get(uri = "/http",processes = MediaType.TEXT_PLAIN)
//    public Maybe<String> hello() { 
//        return httpClient.retrieve(GET("/v1/employees/") )
//                         .firstElement(); 
//    }

}
