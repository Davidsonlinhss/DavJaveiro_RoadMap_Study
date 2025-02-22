package configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(
        basePackages = {"proxies", "services", "repository"}
)
public class ProjectConfiguration {
}
