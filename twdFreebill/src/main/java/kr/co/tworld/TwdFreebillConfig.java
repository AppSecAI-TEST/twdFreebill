package kr.co.tworld;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.client.RestTemplate;

@Configuration
public class TwdFreebillConfig {
	
	@Bean
	JedisConnectionFactory jedisConnectionFactory() {

	    JedisConnectionFactory jedisConFactory = new JedisConnectionFactory();
//	    jedisConFactory.setClientName("Redis-ma");
	    
	    jedisConFactory.setHostName("169.56.68.111");
	    jedisConFactory.setPort(19236);
	    jedisConFactory.setPassword("8f05223c-1311-4fc2-b5f8-38302c679092");
	    
	    return jedisConFactory;
	}
	 
	@Bean
	public RedisTemplate<String, Object> redisTemplate() {
	    RedisTemplate<String, Object> template = new RedisTemplate<String, Object>();
	    template.setConnectionFactory(jedisConnectionFactory());
	    return template;
	}	
	
	@LoadBalanced
	@Bean
	public RestTemplate test(){
		return new RestTemplate();
	}	
}