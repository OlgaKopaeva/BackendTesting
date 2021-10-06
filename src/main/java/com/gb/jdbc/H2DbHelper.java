package com.gb.jdbc;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class H2DbHelper {

    private Connection connection;
    private Statement statement;

    public void connect() throws Exception {
        Class.forName("org.h2.Driver");
        Properties connectionProperties = new Properties();
        connectionProperties.put("user", "sa");
        connectionProperties.put("password", "");
        connection = DriverManager.getConnection(
                "jdbc:h2:~/olcha/test",
                connectionProperties
        );
        statement = connection.createStatement();
    }

    public Product getProductById(int id) {
        try {
            ResultSet rs = statement.executeQuery(
                    "SELECT " +
                            "P.ID, " +
                            "P.TITLE AS PRODUCT_TITLE, " +
                            "P.PRICE, " +
                            "C.TITLE AS CATEGORY_TITLE " +
                            "FROM products P " +
                            "JOIN CATEGORIES C ON P.CATEGORY_ID = C.ID " +
                            "WHERE P.ID = " + id
            );
            while (rs.next()) {
                int productId = rs.getInt("ID");
                String title = rs.getString("PRODUCT_TITLE");
                String categoryTitle = rs.getString("CATEGORY_TITLE");
                int price = rs.getInt("PRICE");
                return new Product(productId, title, price, categoryTitle);
            }
        } catch (Exception e) {
            throw new RuntimeException(e.getCause());
        }
        throw new RuntimeException("Product not found");
    }

    public int getFirstProductId() {
        int firstProductId = 0;
        try {
            ResultSet rs = statement.executeQuery(
                    "SELECT MIN(P.ID) FROM PRODUCTS P"
            );
            while (rs.next()) {
                firstProductId = rs.getInt("MIN(P.ID)");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return firstProductId;
    }

        public List<Product> getProducts () throws SQLException {
            List<Product> result = new ArrayList<>();
            try {
                ResultSet rs = statement.executeQuery(
                        "SELECT " +
                                "P.ID, " +
                                "P.TITLE AS PRODUCT_TITLE, " +
                                "P.PRICE, " +
                                "C.TITLE AS CATEGORY_TITLE " +
                                "FROM PRODUCTS P " +
                                "JOIN CATEGORIES C ON P.CATEGORY_ID = C.ID "
                );
                while (rs.next()) {
                    int productId = rs.getInt("ID");
                    String title = rs.getString("PRODUCT_TITLE");
                    int price = rs.getInt("PRICE");
                    String categoryTitle = rs.getString("CATEGORY_TITLE");
                    Product currentProduct = new Product(productId, title, price, categoryTitle);
                    result.add(currentProduct);
                }
            } catch (Exception e) {
                throw new RuntimeException(e.getCause());
            }
            return result;
        }

        public void close () throws Exception {
            statement.close();
            connection.close();
        }

    }
