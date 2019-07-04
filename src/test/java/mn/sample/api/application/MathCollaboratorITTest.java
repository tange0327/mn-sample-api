package mn.sample.api.application;

import static org.junit.jupiter.api.Assertions.*;

import javax.inject.Inject;

import static org.mockito.Mockito.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import io.micronaut.http.HttpRequest;
import io.micronaut.http.client.RxHttpClient;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.test.annotation.MicronautTest;
import io.micronaut.test.annotation.MockBean;
import mn.sample.api.domain.HelloService;
import mn.sample.api.domain.HelloServiceImpl;

@MicronautTest
class MathCollaboratorITTest {
	
    @Inject
    HelloService helloService;
    
    @Inject
    @Client("/")
    RxHttpClient client; 
	
    @ParameterizedTest
    @CsvSource({"2,4", "3,9"})
    void testComputeNumToSquare(Integer num, Integer square) {
        final Integer result = client.toBlocking().retrieve(HttpRequest.GET("/hello/compute/" + num), Integer.class); 

        assertEquals(
                square,
                result
        );
        verify(helloService).compute(num); 
    }

}
