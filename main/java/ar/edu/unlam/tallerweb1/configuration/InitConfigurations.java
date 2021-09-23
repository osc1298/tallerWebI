package ar.edu.unlam.tallerweb1.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

@Configuration
@EnableScheduling
public class InitConfigurations {

	@Bean
	public CronConfiguration initCron() {
		return new CronConfiguration();
	}
	
}
