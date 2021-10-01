package retrofit.api;

import retrofit.model.CategoryDto;
import retrofit.model.ProductDto;
import retrofit2.Call;
import retrofit2.http.*;

import java.util.List;

public interface MiniMarketService {

    @POST("products")
    Call<ProductDto> createProduct(@Body ProductDto dto);

    @GET("products/{id}")
    Call<ProductDto> getProduct(@Path("id") long id);

    @PUT("products")
    Call<ProductDto> updateProduct(@Body ProductDto dto);

    @DELETE("products/{id}")
    Call<ProductDto> deleteProduct(@Path("id") long id);

    @GET("products")
    Call<List<ProductDto>> getProducts();

    @GET("categories/{id}")
    Call<CategoryDto> getCategory(@Path("id") long id);






}
