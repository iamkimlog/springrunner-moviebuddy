package moviebuddy;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import moviebuddy.data.CachingMovieReader;
import moviebuddy.data.CsvMovieReader;
import moviebuddy.domain.Movie;
import moviebuddy.domain.MovieReader;
import org.springframework.cache.CacheManager;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.context.annotation.*;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

import java.time.Duration;
import java.util.List;

@Configuration
@PropertySource("./application.properties")
@ComponentScan(basePackages = "moviebuddy")
@Import({MovieBuddyFactory.DomainModuleConfig.class, MovieBuddyFactory.DataSourceModuleConfig.class})
public class MovieBuddyFactory {

    @Bean
    public Jaxb2Marshaller jaxb2Marshaller() {
        Jaxb2Marshaller jaxb2Marshaller = new Jaxb2Marshaller();
        jaxb2Marshaller.setPackagesToScan("moviebuddy");
        return jaxb2Marshaller;
    }

    @Bean
    public CacheManager cacheManager() {
        CaffeineCacheManager cacheManager = new CaffeineCacheManager();
        cacheManager.setCaffeine(Caffeine.newBuilder()
                .expireAfterWrite(Duration.ofSeconds(5))
        );

        return cacheManager;
    }

    @Configuration
    static class DomainModuleConfig {
    }

    @Configuration
    static class DataSourceModuleConfig {

        @Primary
        @Bean
        public MovieReader cachingMovieReader(CacheManager cacheManager, MovieReader target) {
            return new CachingMovieReader(cacheManager, target);
        }

    }

}
