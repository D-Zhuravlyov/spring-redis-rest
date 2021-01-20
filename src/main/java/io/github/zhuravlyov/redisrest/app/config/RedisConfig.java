package io.github.zhuravlyov.redisrest.app.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceClientConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;

@Configuration
public class RedisConfig {

    @Value("${spring.redis.host}")
    private String REDIS_HOSTNAME;

    @Value("${spring.redis.port}")
    private int REDIS_PORT;

    @Value("${spring.redis.password}")
    private String REDIS_PASSWORD;

    @Bean
    public LettuceConnectionFactory redisConnectionFactory() {
        final RedisStandaloneConfiguration configuration = new RedisStandaloneConfiguration();
        configuration.setHostName(REDIS_HOSTNAME);
        configuration.setPort(REDIS_PORT);
        configuration.setPassword(REDIS_PASSWORD);
        final LettuceConnectionFactory lettuceConnectionFactory = new LettuceConnectionFactory(configuration, LettuceClientConfiguration.defaultConfiguration());
        lettuceConnectionFactory.afterPropertiesSet();
        return lettuceConnectionFactory;
    }
}
