package io.github.zhuravlyov.redisrest.app.service;

import io.github.zhuravlyov.redisrest.app.dto.RedisMessageDto;

import java.time.Instant;
import java.util.List;

public interface RedisService {

    void saveMessageToRedis(RedisMessageDto redisMessageDto);

    List<String> getLastMessage();

    List<String> getMessagesByTimeRange(Instant start, Instant end);
}
