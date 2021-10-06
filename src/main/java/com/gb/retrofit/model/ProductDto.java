package com.gb.retrofit.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductDto {

        private Integer id;
        private String title;
        private int price;
        private String categoryTitle;

        public ProductDto withId(Integer id) {
                this.id = id;
                return this;
        }

        public ProductDto withTitle(String title) {
                this.title = title;
                return this;
        }

        public ProductDto withPrice(Integer price) {
                this.price = price;
                return this;
        }

        public ProductDto withCategoryTitle(String categoryTitle) {
                this.categoryTitle = categoryTitle;
                return this;
        }

        public Integer getId() {
                return this.id;
        }

        public String getTitle() {
                return this.title;
        }

        public String getCategoryTitle() {
                return this.categoryTitle;
        }

        public int getPrice() {
                return this.price;
        }

}
