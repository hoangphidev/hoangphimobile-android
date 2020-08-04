package tech.hoangphi.store.Services;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import tech.hoangphi.store.Models.Banner;
import tech.hoangphi.store.Models.Brand;
import tech.hoangphi.store.Models.News;
import tech.hoangphi.store.Models.OrderResponse;
import tech.hoangphi.store.Models.Product;
import tech.hoangphi.store.Models.ResultSearch;

public interface DataService {

    @GET("getbrand")
    Call<List<Brand>> getDataBrand();

    @GET("getbanner")
    Call<List<Banner>> getDataBanner();

    @GET("getlatestproduct")
    Call<List<Product>> getLastestProduct();

    @GET("getofferproduct")
    Call<List<Product>> getOfferProduct();

    @GET("gethotproduct")
    Call<List<Product>> getHotProduct();

    @GET("getproductbybrandid/{brand_id}")
    Call<List<Product>> getDataProductByBrandID(@Path("brand_id") int brand_id);

    @FormUrlEncoded
    @POST("order")
    Call<OrderResponse> postOrder(
            @Field("orderid") int orderid,
            @Field("customername") String customername,
            @Field("customerphone") String customerphone,
            //@Field("customeremail") String customeremail,
            @Field("customeraddress") String customeraddress,
            @Field("productid") int productid,
            @Field("productnote") String productnote
    );

//    @FormUrlEncoded
//    @POST("search")
//    Call<ResultSearch> postSearch(@Field("key") String key);

    @FormUrlEncoded
    @POST("search")
    Call<List<Product>> postSearch(@Field("key") String key);

    // News

    @GET("getlastnews")
    Call<List<News>> getLastNews();

    @GET("gettopnews")
    Call<List<News>> getTopNews();

    @GET("getoffernews")
    Call<List<News>> getOfferNews();

    @GET("postview/{id}")
    Call<News> sendView(@Path("id") int id);
}
