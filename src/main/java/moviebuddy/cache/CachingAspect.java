package moviebuddy.cache;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;

import java.util.List;
import java.util.Objects;

@Aspect
public class CachingAspect {

    private final Logger log = LoggerFactory.getLogger(getClass());
    private final CacheManager cacheManager;

    public CachingAspect(CacheManager cacheManager) {
        this.cacheManager = Objects.requireNonNull(cacheManager);
    }

    @Around("target(moviebuddy.domain.MovieReader)")
    public Object doCachingReturnValue(ProceedingJoinPoint pjp) throws Throwable {
        Cache cache = cacheManager.getCache(pjp.getThis().getClass().getName());
        Object cachedValue = cache.get(pjp.getSignature().getName(), Object.class);

        if (Objects.nonNull(cachedValue)) {
            log.info("returns cached data. [{}]", pjp.getSignature().getName());
            return cachedValue;
        }

        cachedValue = pjp.proceed();
        cache.put(pjp.getSignature().getName(), cachedValue);
        log.info("caching return value. [{}]", pjp);

        return cachedValue;
    }

}
