package GroceryStore.project;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
	@EnableWebSecurity
	public class SecurityConfig extends WebSecurityConfigurerAdapter{

		    @Override
		    protected void configure(HttpSecurity http) throws Exception {
		        http.authorizeRequests().antMatchers(HttpMethod.POST, "/{id}/order").permitAll();

		        http
		            .authorizeRequests()
		                // Allow all users to access these endpoints
		                .antMatchers(HttpMethod.POST, "/users").permitAll()
		                .antMatchers(HttpMethod.GET, "/users/**").permitAll()
		                .antMatchers(HttpMethod.GET, "/products").permitAll()
		                .antMatchers(HttpMethod.GET, "/products/**").permitAll()
		                .antMatchers(HttpMethod.POST, "/orders").permitAll()
		                .antMatchers(HttpMethod.GET, "/orders/**").permitAll()
		                // Allow only users with the "USER" role to access these endpoints
		                .antMatchers(HttpMethod.POST, "/users/**").hasRole("USER")
		                .antMatchers(HttpMethod.GET, "/users/**").hasRole("USER")
		                .antMatchers(HttpMethod.GET, "/products/**").hasRole("USER")
		                .antMatchers(HttpMethod.POST, "/orders/**").hasRole("USER")
		                .antMatchers(HttpMethod.GET, "/orders/**").hasRole("USER")
		                // Allow only users with the "ADMIN" role to access these endpoints
		                .antMatchers(HttpMethod.PUT, "/users/**").hasRole("ADMIN")
		                .antMatchers(HttpMethod.DELETE, "/users/**").hasRole("ADMIN")
		                .antMatchers(HttpMethod.PUT, "/products/**").hasRole("ADMIN")
		                .antMatchers(HttpMethod.DELETE, "/products/**").hasRole("ADMIN")
		                .antMatchers(HttpMethod.PUT, "/categories/**").hasRole("ADMIN")
		                .antMatchers(HttpMethod.DELETE, "/categories/**").hasRole("ADMIN")
		                .and()
		            .httpBasic(); 

		        http.csrf().disable();
		    }
		    @Bean
		    public PasswordEncoder passwordEncoder() {
		        return new BCryptPasswordEncoder();
		    }
		   
	}
