package io.github.zhuravlyov.redisrest.app.service.impl;

import io.github.zhuravlyov.redisrest.app.dto.RedisMessageDto;
import io.github.zhuravlyov.redisrest.app.service.RedisService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.BoundZSetOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.time.Instant;

@Service
@RequiredArgsConstructor
public class RedisServiceImpl implements RedisService {

    private final String REDIS_HASH_PREFIX = "MSG";
    private final RedisTemplate<String, String> redisTemplate;
    private BoundZSetOperations<String, String> boundZSetOps;

    @Override
    public void saveMessageToRedis(final RedisMessageDto redisMessageDto) {
        final double timestamp = (double) Instant.now().toEpochMilli();
        boundZSetOps.add(redisMessageDto.getContent(), timestamp);
    }

    @PostConstruct
    public void initOpsForHash() {
        setOpsForHash(redisTemplate.boundZSetOps(REDIS_HASH_PREFIX));
    }

    public void setOpsForHash(final BoundZSetOperations<String, String> boundZSetOps){
        this.boundZSetOps = boundZSetOps;
    }
}
