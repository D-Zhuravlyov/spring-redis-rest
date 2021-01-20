package io.github.zhuravlyov.redisrest.app.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@RequiredArgsConstructor
public class RedisMessageDto {

    @NotBlank
    private String content;
}
