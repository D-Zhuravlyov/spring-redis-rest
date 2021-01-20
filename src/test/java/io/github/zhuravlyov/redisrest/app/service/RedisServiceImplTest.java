package io.github.zhuravlyov.redisrest.app.service;

import io.github.zhuravlyov.redisrest.app.dto.RedisMessageDto;
import io.github.zhuravlyov.redisrest.app.service.impl.RedisServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.redis.core.BoundZSetOperations;
import org.springframework.data.redis.core.RedisTemplate;

import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class RedisServiceImplTest {
    private final String TEST_CONTENT = "TEST_CONTENT";

    @Mock
    private BoundZSetOperations<String, String> boundZSetOps;
    @Mock
    private RedisTemplate<String, String> redisTemplate;

    private RedisServiceImpl sut;

    @BeforeEach
    void setUp() {
        sut = new RedisServiceImpl(redisTemplate);
    }

    @Test
    void shouldCallOpsForHashPut() {
        final RedisMessageDto dto = new RedisMessageDto();
        dto.setContent(TEST_CONTENT);
        sut.setOpsForHash(boundZSetOps);
        sut.saveMessageToRedis(dto);
        verify(boundZSetOps, times(1)).add(eq(TEST_CONTENT), anyDouble());
    }

    @Test
    void shouldCallRedisTemplateOpsForHash() {
        final RedisMessageDto dto = new RedisMessageDto();
        dto.setContent(TEST_CONTENT);
        sut.initOpsForHash();
        verify(redisTemplate, times(1)).opsForHash();
    }
}
