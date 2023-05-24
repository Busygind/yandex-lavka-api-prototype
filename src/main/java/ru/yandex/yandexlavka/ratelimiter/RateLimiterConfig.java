package ru.yandex.yandexlavka.ratelimiter;

import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.Bucket;
import io.github.bucket4j.Refill;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Duration;

@Component
public class RateLimiterConfig {

    private static final int MAX_REQUESTS_PER_SECOND = 1;
    private static final int NUMBER_OF_ENDPOINTS = 9;

    @Getter
    private final Bucket[] buckets;

    @Autowired
    public RateLimiterConfig() {
        buckets = new Bucket[NUMBER_OF_ENDPOINTS];
        for (int i = 0; i < NUMBER_OF_ENDPOINTS; i++) {
            Bandwidth limit = Bandwidth.classic(MAX_REQUESTS_PER_SECOND, Refill.greedy(MAX_REQUESTS_PER_SECOND, Duration.ofSeconds(1)));
            buckets[i] = Bucket.builder()
                    .addLimit(limit)
                    .build();
        }
    }

}
