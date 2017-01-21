package net.logvv.shiro.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.thymeleaf.util.ArrayUtils;
import redis.clients.jedis.JedisPoolConfig;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/**
 * @EnableCaching作用和<cache:annotation-driven /> 相同,
 * 必须启用才能保证cache注解生效，取消cache可以直接去掉这两者之一而不用去除所有的cache注解
 * Created by marlon on 2017/1/19.
 */
@Configuration
@EnableCaching
public class SpringRedisConfig {

    /***
     * reis连接工厂,配置数据源
     * @return
     */
    @Bean
    public JedisConnectionFactory connectionFactory()
    {
        JedisConnectionFactory connectionFactory = new JedisConnectionFactory();
        connectionFactory.setHostName("127.0.0.1");
        connectionFactory.setPort(6379);
        connectionFactory.setDatabase(2);
        connectionFactory.setPassword("123456");

        JedisPoolConfig poolConfig = new JedisPoolConfig();
        poolConfig.setMaxIdle(10);
        poolConfig.setMaxTotal(20);
        poolConfig.setMaxWaitMillis(180);
        poolConfig.setTestOnBorrow(true);

        connectionFactory.setUsePool(true);
        connectionFactory.setPoolConfig(poolConfig);

        return connectionFactory;
    }

    /**
     * redis连接实例
     * @param connectionFactory
     * @return
     */
    @Bean
    public RedisTemplate<String,String> redisTemplate(JedisConnectionFactory connectionFactory)
    {
        RedisTemplate<String,String> redisTemplate = new StringRedisTemplate();
        redisTemplate.setConnectionFactory(connectionFactory);

        // TODO set key/value serializer here
        redisTemplate.setKeySerializer(new StringRedisSerializer());

        Jackson2JsonRedisSerializer serializer = new Jackson2JsonRedisSerializer(Object.class);
        ObjectMapper om = new ObjectMapper();
        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        serializer.setObjectMapper(om);

        redisTemplate.setValueSerializer(serializer);
        redisTemplate.afterPropertiesSet();

        return redisTemplate;
    }

    @Bean
    public RedisCacheManager cacheManager(RedisTemplate<String,String> redisTemplate)
    {
        RedisCacheManager cacheManager = new RedisCacheManager(redisTemplate);

        // 设置过期时间
        cacheManager.setDefaultExpiration(1800);

//        cacheManager.setCachePrefix(new RedisCachePrefix() {
//            @Override
//            public byte[] prefix(String s) {
//                return "spring".getBytes(Charset.forName("UTF-8"));
//            }
//        });

        Map<String,Long> map = new HashMap<String, Long>(){
            {
                put("default",180L);
                put("accountCache",180L);// ??? 哪里定义
            }
        };
        cacheManager.setExpires(map);

        return cacheManager;

    }

    @Bean
    public KeyGenerator cacheKeyGenerator()
    {
        KeyGenerator cacheKeyGenerator = new KeyGenerator()
        {
            @Override
            public Object generate(Object target, Method method, Object... params)
            {
                if (ArrayUtils.isEmpty(params))
                {
                    return null;
                }

                // 判断第一个参数是否为对象，不把对象名称作为key的一部分
                int i = 0;
                if (params.length > 1)
                {
                    if(params[0] instanceof Integer || params[0] instanceof String || params[0] instanceof Float
                            || params[0] instanceof Double || params[0] instanceof Character || params[0] instanceof Byte
                            || params[0] instanceof Boolean || params[0] instanceof Short || params[0] instanceof Long )
                    {
                        i = 0; // do nothing
                    }else {
                        i = 1;
                    }
                }

                StringBuffer sb = new StringBuffer();
                for (; i < params.length; i++)
                {
                    sb.append(params[i]);
                    if ((params.length - i) > 1)
                    {
                        sb.append(':');
                    }
                }
                // key全部采用小写
                return sb.toString().toLowerCase(Locale.US);
            }
        };

        return cacheKeyGenerator;

    }

}
