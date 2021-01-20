package io.github.zhuravlyov.redisrest.app.service.impl;

import io.github.zhuravlyov.redisrest.app.dto.RedisMessageDto;
import io.github.zhuravlyov.redisrest.app.service.RedisService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.time.Instant;

@Service
@RequiredArgsConstructor
public class RedisServiceImpl implements RedisService {

    private final String REDIS_HASH_PREFIX = "MSG";
    private final RedisTemplate<String, String> redisTemplate;
    private HashOperations<String, Object, Object> opsForHash;

    @Override
    public void saveMessageToRedis(final RedisMessageDto redisMessageDto) {
        final Instant timestamp = Instant.now();
        opsForHash.put(REDIS_HASH_PREFIX, redisMessageDto.getContent(), "" + timestamp);
    }

    @PostConstruct
    public void initOpsForHash() {
        setOpsForHash(redisTemplate.opsForHash());
    }

    public void setOpsForHash(HashOperations hash){
        this.opsForHash = hash;
    }
}
