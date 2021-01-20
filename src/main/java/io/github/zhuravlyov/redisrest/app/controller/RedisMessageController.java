package io.github.zhuravlyov.redisrest.app.controller;

import io.github.zhuravlyov.redisrest.app.dto.RedisMessageDto;
import io.github.zhuravlyov.redisrest.app.service.RedisService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Set;

@Log4j2
@RequiredArgsConstructor
@RestController
public class RedisMessageController {

    private final RedisService redisService;

    @PostMapping("/publish")
    public void publishMessage(@Valid @RequestBody RedisMessageDto redisMessageDto) {
        redisService.saveMessageToRedis(redisMessageDto);
    }

    @GetMapping("/getLast")
    public Set<String> getLastMessage() {
        return redisService.getLastMessage();
    }

}
