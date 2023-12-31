package moviebuddy.domain;

import moviebuddy.MovieBuddyFactory;
import moviebuddy.MovieBuddyProfile;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.util.List;

/**
 * @author springrunner.kr@gmail.com
 */
//@ExtendWith(SpringExtension.class)
//@ContextConfiguration(classes= { MovieBuddyFactory.class })
@ActiveProfiles(MovieBuddyProfile.CSV_MODE)
@SpringJUnitConfig(classes = {MovieBuddyFactory.class})
public class MovieFinderTest {

	@Autowired
	MovieFinder movieFinder;

//	@Autowired
//	public MovieFinderTest(MovieFinder movieFinder) {
//		this.movieFinder = movieFinder;
//	}

//	@Autowired
//	public void setMovieFinder(MovieFinder movieFinder) {
//		this.movieFinder = movieFinder;
//	}

	@Test
	void NotEmpty_directedBy() {
		List<Movie> movies = movieFinder.directedBy("Michael Bay");
		Assertions.assertEquals(3, movies.size());
	}

	@Test
	void NotEmpty_releasedYearBy() {
		List<Movie> movies = movieFinder.releasedYearBy(2015);
		Assertions.assertEquals(225, movies.size());
	}

}
