package moviebuddy;

import org.springframework.context.annotation.*;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

@Configuration
@PropertySource("./application.properties")
@ComponentScan(basePackages = "moviebuddy")
@Import({ MovieBuddyFactory.DomainModuleConfig.class, MovieBuddyFactory.DataSourceModuleConfig.class })
public class MovieBuddyFactory {

    @Bean
    public Jaxb2Marshaller jaxb2Marshaller() {
        Jaxb2Marshaller jaxb2Marshaller = new Jaxb2Marshaller();
        jaxb2Marshaller.setPackagesToScan("moviebuddy");
        return jaxb2Marshaller;
    }

    @Configuration
    static class DomainModuleConfig {
    }

    @Configuration
    static class DataSourceModuleConfig {
    }

}
