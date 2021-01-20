package io.github.zhuravlyov.redisrest.app.service;

import io.github.zhuravlyov.redisrest.app.dto.RedisMessageDto;
import io.github.zhuravlyov.redisrest.app.service.impl.RedisServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.redis.core.BoundZSetOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;

import java.util.Set;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class RedisServiceImplTest {
    private final String TEST_CONTENT = "TEST_CONTENT";
    private final String MESSAGE_PREFIX = "MSG";

    @Mock
    private BoundZSetOperations<String, String> boundZSetOps;
    @Mock
    private RedisTemplate<String, String> redisTemplate;
    @Mock
    private ZSetOperations<String, String> zSetOperationsMock;

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

    @Disabled
    @Test
    void shouldGetLastMessage() {
        int offset = 0;
        int count = 1;
        when(redisTemplate.opsForZSet()).thenReturn(zSetOperationsMock);
        // when(zSetOperationsMock.reverseRangeByScore(eq(MESSAGE_PREFIX), eq(Double.MIN_VALUE), eq(Double.MAX_VALUE), eq(offset), eq(count))).thenReturn(Set.of(TEST_CONTENT));

        Set<String> results = sut.getLastMessage();

        verify(redisTemplate, times(1)).opsForZSet();
        assertThat(results, equalTo(Set.of(TEST_CONTENT)));
    }
}
