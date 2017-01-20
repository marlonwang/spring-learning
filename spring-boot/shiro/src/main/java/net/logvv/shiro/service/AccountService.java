package net.logvv.shiro.service;

import net.logvv.shiro.model.Account;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountService {
    private static final Logger LOGGER = LoggerFactory.getLogger(AccountService.class);

    @Autowired
    private CacheService cacheService;

    public String login(String name,String password)
    {
        Account account = cacheService.findAccountByNamePass(name,password);
        return account.toString();

    }

    public Account fetchAccount(String firstname,String lastname)
    {
        Account account = cacheService.findFromCacheDB(firstname,lastname);
        LOGGER.info("if see 'query account from db' means cache not hit");
        System.out.println("see 'query account from db' means cahe not hit.");

        return account;
    }
}
