package com.example.demo.Security;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.error.OAuth2AccessDeniedHandler;
import org.springframework.security.oauth2.provider.token.RemoteTokenServices;
import org.springframework.security.oauth2.provider.token.ResourceServerTokenServices;

@Configuration
@EnableResourceServer
public class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {
private static final String RESOURCE_ID = "my_rest_api";
    

@Value("${security.oauth2.client.id}")
private String clientID;

@Value("${security.oauth2.client.client-secret}")
private String clientSecret;

@Value("${security.oauth2.resource.token-info-uri}")
private String uri;


@Bean
@Primary
public ResourceServerTokenServices tokenService() {
	RemoteTokenServices tokenService = new RemoteTokenServices();
	
	tokenService.setClientId(this.clientID);
	tokenService.setClientSecret(this.clientSecret);
	tokenService.setCheckTokenEndpointUrl(this.uri);
	
	return tokenService;
}

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) {
        resources.tokenServices(tokenService()).resourceId(RESOURCE_ID).stateless(false);
    }
 
    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.
        anonymous().disable().authorizeRequests()
        .antMatchers("/activity/**").hasRole("ADMIN")
        .antMatchers("/activityType/**").hasRole("ADMIN")
        .antMatchers("/activityPlace/**").hasRole("ADMIN")
        .and().exceptionHandling().accessDeniedHandler(new OAuth2AccessDeniedHandler());
    }

}
