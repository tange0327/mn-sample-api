package mn.sample.api.domain;

import javax.inject.Singleton;

@Singleton
public class HelloServiceImpl implements HelloService {
    @Override
    public Integer compute(Integer num) {
        return num * 4;
    }
}
