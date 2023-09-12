package moviebuddy;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class CaffeineTest {

    @Test
    void useCache() throws InterruptedException {
        Cache<String, Object> cache = Caffeine.newBuilder()
                .expireAfterWrite(Duration.ofMillis(200))
                .maximumSize(100)
                .build();

        // 캐시를 사용할 때 메모리의 크기를 항상 고려해야한다.

        String key = "iamkimlog";
        Object value = new Object();

        Assertions.assertNull(cache.getIfPresent(key));

        cache.put(key, value);
        Assertions.assertEquals(value, cache.getIfPresent(key));

        TimeUnit.MILLISECONDS.sleep(100);
        Assertions.assertEquals(value, cache.getIfPresent(key));

        TimeUnit.MILLISECONDS.sleep(100);
        Assertions.assertNull(cache.getIfPresent(key));
    }

}
