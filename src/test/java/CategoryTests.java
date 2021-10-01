import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import retrofit.api.MiniMarketService;
import retrofit.model.CategoryDto;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;

import static org.hamcrest.MatcherAssert.assertThat;

public class CategoryTests {
    static MiniMarketService marketService;

    @BeforeAll
    static void beforeAll() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://localhost:8189/market/api/v1/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        marketService = retrofit.create(MiniMarketService.class);
    }

    @Test
    @DisplayName("Get category")
    void testGetCategory() throws IOException {
        Response<CategoryDto> response = marketService.getCategory(1)
                .execute();
        assertThat(response.isSuccessful(), CoreMatchers.is(true));
        System.out.println("category: " + response.body());

    }
}
