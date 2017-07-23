package runner;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

/**
 * Created by I Like Milk on 2017/6/19.
 */
@EnableAutoConfiguration
@ComponentScan("controller")
public class Runner {
    public static void main(String[] args) throws Exception {
        SpringApplication.run(Runner.class, args);

    }
}
