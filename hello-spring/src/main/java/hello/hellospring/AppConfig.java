package hello.hellospring;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(
        basePackages = "hello.hellospring",
        basePackageClasses = AppConfig.class
)
public class AppConfig {
}
