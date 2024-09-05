package com.github.malahor.shurl

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.redis.connection.RedisStandaloneConfiguration
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory
import org.springframework.data.redis.core.RedisTemplate


@Configuration
class RedisConfig(@Value("\${spring.redis.host}") var host: String, @Value("\${spring.redis.port}") var port: Int) {

    @Bean
    fun jedisConnectionFactory(): JedisConnectionFactory {
        val config = RedisStandaloneConfiguration(host, port)
        return JedisConnectionFactory(config)
    }

    @Bean
    fun redisTemplate(): RedisTemplate<String, Any> {
        val template = RedisTemplate<String, Any>()
        template.connectionFactory = jedisConnectionFactory()
        return template
    }
}