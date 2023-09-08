package moviebuddy.domain;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class MovieBuddyFactory {

    @Bean
    public MovieReader movieReader() {
        return new CsvMovieReader();
    }

    @Bean
    public MovieFinder movieFinder(MovieReader movieReader) {
        return new MovieFinder(movieReader);
    }

}
