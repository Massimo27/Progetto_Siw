
package it.uniroma3.siw.silphspa.silphspa;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration @EnableWebSecurity
public class Autorizzazione extends WebSecurityConfigurerAdapter{
	@Autowired
	private Environment environment;
	
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
        .authorizeRequests()
            .antMatchers("/","/cerca/**","classpath:/static/css/","/css/**","/clienteForm","/cliente","/addCliente","/ricercaFotografo","/ricercaAlbum","/cercaFoto","/foto/**","/remove/foto/**","ricerca.html").permitAll()
            .antMatchers(HttpMethod.GET,"/admin").hasAnyAuthority("ADMIN")
            .anyRequest().authenticated()
            .and()
        .formLogin()
        	.defaultSuccessUrl("/home")
            //.loginPage("/accedi")
            //.permitAll()
            .and()
        .logout()
        	.logoutUrl("/logout")
        	.logoutSuccessUrl("/");
            //.permitAll();
		//super.configure(http);
	}
	
	
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception{
		auth.jdbcAuthentication().dataSource(this.buildDataSource()) //Accede al DB
		.authoritiesByUsernameQuery("SELECT nickname,role FROM funzionario WHERE nickname=?")
		.usersByUsernameQuery("SELECT nickname,password,1 as enabled FROM funzionario WHERE nickname=?");

	}
	
	/* Imposta il DataSource con le credenziali di accesso al Database, in questo caso
	 * di postgress*/
	 
	@Bean
	DataSource buildDataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(environment.getProperty("spring.datasource.driver-class-name"));
		dataSource.setUrl(environment.getProperty("spring.datasource.url"));
		dataSource.setUsername(environment.getProperty("spring.datasource.username"));
		dataSource.setPassword(environment.getProperty("spring.datasource.password"));
		return dataSource;
	}

	@Bean
	PasswordEncoder encoder() {
		return new BCryptPasswordEncoder();
	}
}
