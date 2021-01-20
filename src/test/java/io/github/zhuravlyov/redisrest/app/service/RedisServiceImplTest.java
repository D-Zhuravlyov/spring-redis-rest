package io.github.zhuravlyov.redisrest.app.service;

import io.github.zhuravlyov.redisrest.app.dto.RedisMessageDto;
import io.github.zhuravlyov.redisrest.app.service.impl.RedisServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class RedisServiceImplTest {
    private final String TEST_CONTENT = "TEST_CONTENT";
    private final String MESSAGE_PREFIX = "MSG";

    @Mock
    private HashOperations<String, Object, Object> opsForHash;
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
        sut.setOpsForHash(opsForHash);
        sut.saveMessageToRedis(dto);
        verify(opsForHash, times(1)).put(eq(MESSAGE_PREFIX), eq(TEST_CONTENT), anyString());
    }

    @Test
    void shouldCallRedisTemplateOpsForHash() {
        final RedisMessageDto dto = new RedisMessageDto();
        dto.setContent(TEST_CONTENT);
        sut.initOpsForHash();
        verify(redisTemplate, times(1)).opsForHash();
    }
}
