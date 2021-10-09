import org.junit.jupiter.api.*;
import com.gb.retrofit.api.MiniMarketService;
import com.gb.retrofit.model.ProductDto;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;
import java.util.List;

import org.hamcrest.CoreMatchers;

import static org.hamcrest.MatcherAssert.assertThat;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ProductTests {
    static MiniMarketService marketService;
    static Integer productId;
    ProductDto dto = new ProductDto()
            .withTitle("Coca cola")
            .withCategoryTitle("Food")
            .withPrice(35);

    @BeforeAll
    public static void beforeAll() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://localhost:8189/market/api/v1/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        marketService = retrofit.create(MiniMarketService.class);
    }

    @Order(1)
    @Test
    @DisplayName("Create product")
    void testCreateProduct() throws IOException {
        Response<ProductDto> response = marketService.createProduct(dto)
                .execute();
        assertThat(response.isSuccessful(), CoreMatchers.is(true));
        System.out.println(response);

        System.out.println("product: " + response.body().toString());
        productId = response.body().getId();
        assertThat(productId, CoreMatchers.notNullValue());
        System.out.println(productId);

    }
    @Order(2)
    @Test
    @DisplayName("Get product")
    void testGetProduct() throws IOException {
        Response<ProductDto> response = marketService.getProduct(productId)
                .execute();
        assertThat(response.isSuccessful(), CoreMatchers.is(true));
        System.out.println(response);

        assertThat(response.body().getTitle(), CoreMatchers.is("Coca cola"));
        assertThat(response.body().getCategoryTitle(), CoreMatchers.is("Food"));
        assertThat(response.body().getPrice(), CoreMatchers.is(35));
        System.out.println("product: " + response.body().toString());

    }

    @Order(3)
    @Test
    @DisplayName("Update product")
    void testUpdateProduct() throws IOException {
        dto.withCategoryTitle("Drinks");
        dto.withId(productId);
        System.out.println("product: " + dto.toString());
        Response<ProductDto> response = marketService.updateProduct(dto)
                .execute();
       // assertThat(response.isSuccessful(), CoreMatchers.is(true));
        System.out.println(response);

        /*assertThat(response.body().getId(), CoreMatchers.is(productId));
        assertThat(response.body().getCategoryTitle(), CoreMatchers.is("Drinks"));
        System.out.println("product: " + response.body().toString());*/

    }
    @Order(4)
    @DisplayName("Delete product")
    void testDeleteProduct() throws IOException {
        System.out.println(dto.toString());
        Response<ProductDto> response = marketService.deleteProduct(productId)
                .execute();
      //  assertThat(response.isSuccessful(), CoreMatchers.is(true));
        System.out.println(response);

    }
    @Order(5)
    @Test
    @DisplayName("Get all products")
    void testGetAllProducts() throws IOException {
        Response<List<ProductDto>> response = marketService.getProducts()
                .execute();
        assertThat(response.isSuccessful(), CoreMatchers.is(true));
        System.out.println(response);

      //  List<ProductDto> products = marketService.getProducts().execute().body();
        System.out.println("products: " + response.body());
    }
}

