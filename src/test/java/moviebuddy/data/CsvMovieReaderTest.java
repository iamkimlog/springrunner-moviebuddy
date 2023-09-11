package moviebuddy.data;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.core.io.DefaultResourceLoader;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;

public class CsvMovieReaderTest {

    @Test
    public void Valid_Metadata() throws IOException {
        CsvMovieReader movieReader = new CsvMovieReader();
        movieReader.setMetadata("movie_metadata.csv");
        movieReader.setResourceLoader(new DefaultResourceLoader());

        movieReader.postConstruct();
    }

    @Test
    public void Invalid_Metadata() {
        CsvMovieReader movieReader = new CsvMovieReader();
        movieReader.setResourceLoader(new DefaultResourceLoader());

        Assertions.assertThrows(FileNotFoundException.class, () -> {
            movieReader.setMetadata("invalid");
            movieReader.postConstruct();
        });
    }

}
