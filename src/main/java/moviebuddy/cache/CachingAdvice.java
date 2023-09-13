package moviebuddy.cache;

import moviebuddy.domain.Movie;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;

import java.util.List;
import java.util.Objects;

public class CachingAdvice implements MethodInterceptor {

    private final CacheManager cacheManager;

    public CachingAdvice(CacheManager cacheManager) {
        this.cacheManager = Objects.requireNonNull(cacheManager);
    }

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        Cache cache = cacheManager.getCache(invocation.getThis().getClass().getName());
        Object cachedValue = cache.get(invocation.getMethod().getName(), List.class);
        if (Objects.nonNull(cachedValue)) {
            return cachedValue;
        }

        cachedValue = invocation.proceed();
        cache.put(invocation.getMethod().getName(), cachedValue);

        return cachedValue;
    }

}
