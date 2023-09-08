package moviebuddy.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class BeanScopeTest {

    @Test
    public void Equals_MovieFinderBean() {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(MovieBuddyFactory.class);

        MovieFinder movieFinder = applicationContext.getBean(MovieFinder.class);

        Assertions.assertEquals(movieFinder, applicationContext.getBean(MovieFinder.class));
    }

}
