package com.shuyinqi.transaction.service;

import com.shuyinqi.BaseTest;
import org.junit.Ignore;
import org.junit.Test;

/**
 * Created by jiayusun on 2016/6/14.
 */
public class TransactionServiceTest extends BaseTest{

    TransactionService transactionService = (TransactionService) context.getBean("transactionServiceImpl");

    @Test
    public void testDiffSave()throws Exception{
        transactionService.diffSave();

    }

    @Test
    @Ignore
    public void testSortObject() throws Exception {
        transactionService.sortObject();
    }
}
