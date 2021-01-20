package io.github.zhuravlyov.redisrest.app.service;

import io.github.zhuravlyov.redisrest.app.dto.RedisMessageDto;

import java.time.Instant;
import java.util.Set;

public interface RedisService {

    void saveMessageToRedis(RedisMessageDto redisMessageDto);

    Set<String> getLastMessage();

    Set<String> getMessagesByTimeRange(Instant start, Instant end);
}
