package moviebuddy.data;

import moviebuddy.ApplicationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.io.FileNotFoundException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;

public abstract class AbstractFileSystemMovieReader {
    private final Logger log = LoggerFactory.getLogger(getClass());
    private String metadata;

    public void setMetadata(String metadata) {
        this.metadata = Objects.requireNonNull(metadata, "metadata is required value");
    }

    public String getMetadata() {
        return metadata;
    }

    @PostConstruct
    public void postConstruct() throws FileNotFoundException, URISyntaxException {
        URL metadataUrl = ClassLoader.getSystemResource(metadata);
        if (Objects.isNull(metadataUrl)) {
            throw new FileNotFoundException(metadata);
        }
        if (!Files.isReadable(Path.of(metadataUrl.toURI()))) {
            throw new ApplicationException(String.format("cannot read to metadata. [%s]", metadataUrl));
        }
    }

    @PreDestroy
    public void preDestroy() {
        log.info("Destroyed bean");
    }
}