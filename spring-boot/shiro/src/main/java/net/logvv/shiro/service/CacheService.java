package net.logvv.shiro.service;

import net.logvv.shiro.model.Account;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * Created by marlon on 2017/1/19.
 */
@Service
public class CacheService {

    @CachePut(value = "cacheManager", keyGenerator = "cacheKeyGenerator")
    public Account findFromCacheDB(String firstname,String lastname)
    {
        System.out.println("query account from db, name:" + firstname);

        return new Account(firstname,lastname);
    }

    @Cacheable(value = "cacheManeger", keyGenerator = "cacheKeyGenerator")
    public Account findAccountByNamePass(String name,String password)
    {
        System.out.println("query from db.");
        return new Account(name,password);
    }

    @CacheEvict(value = "cacheManager", keyGenerator = "cacheKeyGenerator", allEntries = true)
    public void evictAccount(String cacheKey)
    {

    }
}
