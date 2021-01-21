package io.github.zhuravlyov.redisrest.app.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Pattern;

@Data
@RequiredArgsConstructor
public class RedisMessageDto {

    /*
     *  Utils class io.github.zhuravlyov.redisrest.app.service.utils.RedisServiceUtils
     *  uses restricted symbol to add unique postfix to content
     *
     * */
    @NotBlank
    @Pattern(regexp = "^[^_]+$", message = "Restricted symbol fount in content")
    private String content;
}
