package moviebuddy.data;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.cache.support.NoOpCacheManager;
import org.springframework.core.io.DefaultResourceLoader;

import java.io.FileNotFoundException;
import java.io.IOException;

public class CsvMovieReaderTest {

    @Test
    public void Valid_Metadata() throws IOException {
        CsvMovieReader movieReader = new CsvMovieReader(new NoOpCacheManager());
        movieReader.setMetadata("movie_metadata.csv");
        movieReader.setResourceLoader(new DefaultResourceLoader());

        movieReader.postConstruct();
    }

    @Test
    public void Invalid_Metadata() {
        CsvMovieReader movieReader = new CsvMovieReader(new NoOpCacheManager());
        movieReader.setResourceLoader(new DefaultResourceLoader());

        Assertions.assertThrows(FileNotFoundException.class, () -> {
            movieReader.setMetadata("invalid");
            movieReader.postConstruct();
        });
    }

}
