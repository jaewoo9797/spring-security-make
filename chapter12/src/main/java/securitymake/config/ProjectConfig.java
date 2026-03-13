package securitymake.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.oauth2.client.CommonOAuth2Provider;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.registration.InMemoryClientRegistrationRepository;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class ProjectConfig {

	@Bean
	public ClientRegistrationRepository clientRegistrationRepository() {
		return new InMemoryClientRegistrationRepository(this.clientRegistration());
	}

	private ClientRegistration clientRegistration() {
		return CommonOAuth2Provider.GITHUB
			.getBuilder("github")
			.clientId("Iv23li4jvPlAEIH1ulOu")
			.clientSecret("790611e93c38b1dc051c648871aa6496757cca59")
			.build();
	}

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http
			.oauth2Login(Customizer.withDefaults());

		http
			.authorizeHttpRequests(auth ->
				auth.anyRequest()
					.authenticated()
			);

		return http.build();
	}

}
