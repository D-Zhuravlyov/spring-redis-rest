package io.github.zhuravlyov.redisrest.app.service;

import io.github.zhuravlyov.redisrest.app.dto.RedisMessageDto;

import java.util.Set;

public interface RedisService {

    void saveMessageToRedis(RedisMessageDto redisMessageDto);

    Set<String> getLastMessage();
}
