
package com.gb.mybatis;

import java.util.List;

import com.gb.db.dao.ProductsMapper;
import com.gb.db.model.Products;
import com.gb.db.model.ProductsExample;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class Test {

    public static void main(String[] args) {
        SqlSessionFactory sessionFactory =
                new SqlSessionFactoryBuilder()
                        .build(Test.class.getResourceAsStream("mybatis-config.xml"));

        SqlSession session = sessionFactory.openSession();

        ProductsMapper mapper = session.getMapper(ProductsMapper.class);
        Products product = mapper.selectByPrimaryKey(12L);
        System.out.println(product);

        ProductsExample example = new ProductsExample();
        example.createCriteria()
                .andTitleEqualTo("Milk");

        List<Products> example1 = mapper.selectByExample(example);
        System.out.println(example1);
    }
}


