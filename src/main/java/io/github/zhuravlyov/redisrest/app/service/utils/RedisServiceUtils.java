package io.github.zhuravlyov.redisrest.app.service.utils;

import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

public class RedisServiceUtils {
    public static final String UNIQUENESS_DIVIDER = "_";
    private static final Random random = new Random();

    public static String generateUniqueContent(final String content) {
        return content + UNIQUENESS_DIVIDER + random.nextDouble();
    }

    public static List<String> retrieveOriginalContent(final Set<String> rawResults) {
        return rawResults.stream()
                .map(content -> content = content.substring(0, content.indexOf(UNIQUENESS_DIVIDER)))
                .collect(Collectors.toList());
    }

    private RedisServiceUtils() {
    }
}

