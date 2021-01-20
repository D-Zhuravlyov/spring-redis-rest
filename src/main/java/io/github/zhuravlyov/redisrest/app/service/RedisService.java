package io.github.zhuravlyov.redisrest.app.service;

import io.github.zhuravlyov.redisrest.app.dto.RedisMessageDto;

public interface RedisService {

    void saveMessageToRedis(RedisMessageDto redisMessageDto);
}
