package cn.itcast.service.impl;

import cn.itcast.dao.AccountDao;
import cn.itcast.domain.Account;
import cn.itcast.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("accountService")
public class AccountServiceImpl implements AccountService {
    @Autowired
    private AccountDao accountDao;

    @Override
    public List<Account> findAll() {
        System.out.println("业务层，查询所有账户");
        List<Account> list=accountDao.findAll();
        for(Account account:list){
            System.out.println(account.toString());
        }
        return list;
    }

    @Override
    public void saveAccount(Account account) {
        System.out.println("业务层，保存账户");
        accountDao.saveAccount(account);
    }
}
