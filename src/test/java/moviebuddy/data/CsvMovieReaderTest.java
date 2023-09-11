package moviebuddy.data;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.net.URISyntaxException;

public class CsvMovieReaderTest {

    @Test
    public void Valid_Metadata() throws FileNotFoundException, URISyntaxException {
        CsvMovieReader movieReader = new CsvMovieReader();
        movieReader.setMetadata("movie_metadata.csv");

        movieReader.postConstruct();
    }

    @Test
    public void Invalid_Metadata() {
        CsvMovieReader movieReader = new CsvMovieReader();

        Assertions.assertThrows(FileNotFoundException.class, () -> {
            movieReader.setMetadata("invalid");
            movieReader.postConstruct();
        });
    }

}
