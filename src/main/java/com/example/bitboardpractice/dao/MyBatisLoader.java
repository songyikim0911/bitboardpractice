package com.example.bitboardpractice.dao;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.InputStream;

public enum MyBatisLoader {

    INSTANCE;
    //mybatisloader.instance.getfactory.opensession으로 사용예정

    private SqlSessionFactory sqlSessionFactory;

    MyBatisLoader(){
        try{
            String resource = "mybatis-config.xml";
            InputStream inputStream = Resources.getResourceAsStream(resource);
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        }catch(Exception e){
            e.printStackTrace();
        }

    }


    public SqlSessionFactory getFactory(){
        return this.sqlSessionFactory;
    }

}