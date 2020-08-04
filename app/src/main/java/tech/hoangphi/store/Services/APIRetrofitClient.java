package tech.hoangphi.store.Services;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class APIRetrofitClient {
//    private static String base_url = "https://shophoangphimobile.000webhostapp.com/api/";
//    private static String base_url = "http://192.168.150.2/master/public/api/";
    private static String base_url = "https://hoangphi.works/api/";
    private static Retrofit retrofit = null;
    private static APIRetrofitClient mInstance;

    private APIRetrofitClient() {
        retrofit = new Retrofit.Builder()
                .baseUrl(base_url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static synchronized APIRetrofitClient getInstance() {
        if (mInstance == null) {
            mInstance = new APIRetrofitClient();
        }
        return mInstance;
    }

    public DataService getDataService() {
        return retrofit.create(DataService.class);
    }
}
