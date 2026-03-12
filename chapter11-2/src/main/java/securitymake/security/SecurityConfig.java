package securitymake.security;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Autowired
	private JwtAuthenticationFilter jwtAuthenticationFilter;

	@Autowired
	private OtpAuthenticationProvider otpAuthenticationProvider;

	@Autowired
	private UsernamePasswordAuthenticationProvider usernamePasswordAuthenticationProvider;

	@Bean
	public AuthenticationManager authenticationManager() {
		return new ProviderManager(
			List.of(
				usernamePasswordAuthenticationProvider,
				otpAuthenticationProvider
			)
		);
	}

	@Bean
	public InitialAuthenticationFilter initialAuthenticationFilter(AuthenticationManager authenticationManager) {
		return new InitialAuthenticationFilter(authenticationManager());
	}

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http
			.csrf(AbstractHttpConfigurer::disable)
			.addFilterAt(initialAuthenticationFilter(authenticationManager()), BasicAuthenticationFilter.class)
			.addFilterAfter(jwtAuthenticationFilter, BasicAuthenticationFilter.class);

		http.authorizeHttpRequests(auth -> auth
			.anyRequest()
			.authenticated());

		return http.build();
	}

}
