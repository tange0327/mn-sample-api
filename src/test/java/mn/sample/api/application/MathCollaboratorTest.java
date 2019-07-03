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
class MathCollaboratorTest {
	
    @Inject
    HelloService helloService;
    
    @Inject
    @Client("/")
    RxHttpClient client; 
	
    @ParameterizedTest
    @CsvSource({"2,4", "3,9"})
    void testComputeNumToSquare(Integer num, Integer square) {

        when( helloService.compute(num) )
            .then(invocation -> Long.valueOf(Math.round(Math.pow(num, 2))).intValue());

        final Integer result = client.toBlocking().retrieve(HttpRequest.GET("/hello/compute/" + num), Integer.class); 

        assertEquals(
                square,
                result
        );
        verify(helloService).compute(num); 
    }

    @MockBean(HelloServiceImpl.class) 
    HelloService mathService() {
        return mock(HelloService.class);
    }

}
