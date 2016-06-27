package com.shuyinqi.transaction.dao;

import com.google.common.base.Objects;
import com.shuyinqi.transaction.ObjectA;
import com.shuyinqi.transaction.ObjectB;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by jiayusun on 2016/6/13.
 */
@Component
public class TransactionDao {

    @Autowired
    private SqlSession mysqlSession;

    @Autowired
    private SqlSession oraclesqlSession;

    public Integer insertObjectA(){
        ObjectA oa = new ObjectA();
        oa.setAge(10);
        oa.setA_name("mysql");
        return mysqlSession.insert("saveObjectA", oa);
    };

    public Integer insertObjectB() throws Exception {
        ObjectB ob = new ObjectB();
        ob.setAge(11);
        ob.setB_name("oracle");
         oraclesqlSession.insert("saveObjectB", ob);
        if(Objects.equal(ob.getB_name(),"oracle")){
            throw new Exception("1111111");
        }
        return 0;
    };
}
