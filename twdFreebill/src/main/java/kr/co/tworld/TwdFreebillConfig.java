package kr.co.tworld;

import java.nio.charset.Charset;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.config.java.AbstractCloudConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.filter.CharacterEncodingFilter;

@Configuration
public class TwdFreebillConfig extends AbstractCloudConfig {

	@Value("${services.redis.name}")
	private String redisName;
	
	@Bean
	public RedisConnectionFactory redisConnectionFactory() {
		return connectionFactory().redisConnectionFactory(redisName);
	}
	
	@Bean
	public RedisTemplate<String, Object> redisTemplate() {
	    RedisTemplate<String, Object> template = new RedisTemplate<String, Object>();
	    template.setConnectionFactory(redisConnectionFactory());
	    return template;
	}	
	
	@Bean
	@LoadBalanced
	public RestTemplate test(){
		return new RestTemplate();
	}

}