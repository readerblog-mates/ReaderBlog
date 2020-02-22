package readerblog.mates.readerblog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import readerblog.mates.readerblog.config.AppProperties;

@SpringBootApplication
@EnableConfigurationProperties(AppProperties.class)
public class ReaderBlogApplication {

	public static void main(String[] args) {
		SpringApplication.run(ReaderBlogApplication.class, args);
	}

}
