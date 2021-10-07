package com.gb.mybatis;

import com.gb.db.dao.CategoriesMapper;
import com.gb.db.dao.ProductsMapper;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class MyBatisHelper {
    private SqlSession createSession(){
        SqlSessionFactory sessionFactory =
                new SqlSessionFactoryBuilder()
                        .build(ProductsTests.class.getResourceAsStream("mybatis-config.xml"));
        SqlSession session = sessionFactory.openSession();
        return session;
    }
    public ProductsMapper getProductMapper(){
        ProductsMapper mapper = createSession().getMapper(ProductsMapper.class);
        return mapper;
    }
    public CategoriesMapper getCategoryMapper(){
        CategoriesMapper mapper = createSession().getMapper(CategoriesMapper.class);
        return mapper;
    }
}
