
package com.gb.mybatis;

import java.util.List;

import com.gb.db.dao.ProductsMapper;
import com.gb.db.model.Products;
import com.gb.db.model.ProductsExample;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertTrue;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ProductsTests {
    public static ProductsMapper mapper;

    @BeforeAll
    public static void beforeAll() {
        MyBatisHelper myBatisHelper = new MyBatisHelper();
        mapper = myBatisHelper.getProductMapper();
    }

    @Order(1)
   // @Test
    @DisplayName("Create product")
    void testCreateProduct() {
        Products product = new Products();
        product.setPrice(35);
        product.setTitle("Coca cola");
        product.setCategoryId(1L);

        mapper.insert(product);
        System.out.println(product);

        ProductsExample criteria = new ProductsExample();
        criteria.createCriteria()
                .andIdIsNotNull()
                .andPriceEqualTo(35)
                .andCategoryIdEqualTo(1L)
                .andTitleEqualTo("Coca cola");

        List<Products> productsList = mapper.selectByExample(criteria);
        System.out.println(productsList);

        assertTrue(productsList.size() > 0);
    }

    @Order(2)
   // @Test
    @DisplayName("Get product")
    void testGetProduct() {
        Products product = mapper.selectByPrimaryKey(13L);
        Assertions.assertEquals(product.getPrice(), 360);
        Assertions.assertEquals(product.getCategoryId(), 1);
        Assertions.assertEquals(product.getTitle(), "Cheese");
    }

    @Order(3)
    //@Test
    @DisplayName("Update product")
    void testUpdateProduct() {
        Products updateProduct = new Products();
        updateProduct.setId(13L);
        updateProduct.setPrice(450);
        updateProduct.setTitle("Coca cola");
        updateProduct.setCategoryId(1L);
        mapper.updateByPrimaryKey(updateProduct);

        Products product = mapper.selectByPrimaryKey(13L);
        System.out.println(product);
        Assertions.assertEquals(product.getPrice(), 450);
    }

    @Order(4)
   // @Test
    @DisplayName("Delete product")
    void testDeleteProduct() {
        mapper.deleteByPrimaryKey(13L);
        Products product = mapper.selectByPrimaryKey(13L);
        System.out.println(product);
        Assertions.assertNull(product);
    }

    @Order(5)
   // @Test
    @DisplayName("Get all products")
    void testGetAllProducts() {
        ProductsExample criteria = new ProductsExample();
        criteria.createCriteria()
                .andIdIsNotNull();

        List<Products> productsList = mapper.selectByExample(criteria);
        System.out.println(productsList);
        assertTrue(productsList.size() > 0);
    }
}


