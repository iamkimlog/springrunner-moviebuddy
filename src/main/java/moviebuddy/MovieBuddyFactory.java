package moviebuddy;

import moviebuddy.domain.MovieFinder;
import moviebuddy.domain.MovieReader;
import org.springframework.context.annotation.*;

@Configuration
@ComponentScan(basePackages = "moviebuddy")
@Import({ MovieBuddyFactory.DomainModuleConfig.class, MovieBuddyFactory.DataSourceModuleConfig.class })
public class MovieBuddyFactory {

    @Configuration
    static class DomainModuleConfig {
    }

    @Configuration
    static class DataSourceModuleConfig {
    }

}
