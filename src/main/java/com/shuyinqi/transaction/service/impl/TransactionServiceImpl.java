package com.shuyinqi.transaction.service.impl;

import com.shuyinqi.transaction.dao.TransactionDao;
import com.shuyinqi.transaction.service.TransactionService;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * Created by jiayusun on 2016/6/13.
 */
@Service
@Log4j
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    private TransactionDao transactionDao;
    @Override
    public String diffSave() throws Exception {
        int a = transactionDao.insertObjectA();

        int b = transactionDao.insertObjectB();
        return null;
    }

    public int sortObject() throws Exception {
        saveObjectA();
        saveObjectB();
        return 0;
    }

    public int saveObjectA()throws Exception{
        return transactionDao.insertObjectA();
    };

    public int saveObjectB()throws Exception{
        return transactionDao.insertObjectB();
    }

}
