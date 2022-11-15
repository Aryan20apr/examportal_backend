package com.aryan.examportal_backend.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.aryan.examportal_backend.services.impl.UserDetailServiceImpl;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)//Used for identifying the roles: which apis user can access and which api admin can access
public class SecurityConfig {
	
	
	@Autowired
	UserDetailServiceImpl userDetailServiceImpl;
	
	@Autowired
	private JWTAuthenticationEntryPoint  unAuthorizedHandler;
	
	@Autowired
	private JwtAuthenticationFilter jwtAuthenticationFilter;
	
	  public static final String[] PUBLIC_URLS = { "/generate-token","/user/newuser","/"};
	
	 @Bean
	    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
	        http.csrf()//Cross-Site Request Forgery (CSRF) is an attack that forces authenticated users to submit a request to a Web application against which they are currently authenticated.
	                .disable()
	                .cors()
	                .disable()
	                .authorizeHttpRequests()
	                .antMatchers(PUBLIC_URLS)
	                .permitAll()
					.antMatchers( HttpMethod.OPTIONS )
	                .permitAll()
	                .anyRequest()
	                .authenticated()//Authenticate any other request which is received
	                .and().exceptionHandling()//If authentications fails or no JWT token is provided
	                .authenticationEntryPoint(unAuthorizedHandler)// If at any time, any exception is generated
	                                                                           // due to unauthourization, the commence
	                                                                           // method will be executed inside the
	                                                                           // JwtAuthenticationEntryPoint
	                														// Reject every unautorized request and send error code 401
	                
	                .and()
	                .sessionManagement()
	                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);

	        // Here we have set the jwtAuthenticationEntryPoint and session management
	        // policies.
	        // Session management policies are stateless as jwt authentication works on
	        // statless mechanism

	        //Everytime a request is received, every time we need to check if it has a valid token. It takes out the token from the header before every request is proceeded and checks it
	        http.addFilterBefore(this.jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
	        http.authenticationProvider(daoAuthenticationProvider());
	        DefaultSecurityFilterChain defaultSecurityFilterChain = http.build();

	        return defaultSecurityFilterChain;
	    }
	 @Bean
	    public DaoAuthenticationProvider daoAuthenticationProvider()// Needed for authenticating using the database . Other method is In-memory
	    {
		 	//During authentication, Spring Security calls the loadUserByUserName method of USerDetailService
	        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
	        provider.setUserDetailsService(this.userDetailServiceImpl);
	        provider.setPasswordEncoder(passwordEncoder());
	        return provider;

	    }

	    @Bean
	    public AuthenticationManager authenticationManagerBean(AuthenticationConfiguration configuration) throws Exception {
	        // Required to authenticate the password
	        return configuration.getAuthenticationManager();
	    }

		
		  @Bean public PasswordEncoder passwordEncoder() { return new
		  BCryptPasswordEncoder(); }
		 

	    

}
