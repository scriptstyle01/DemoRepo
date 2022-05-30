package ottu.payment.toast.Fetchdata;

//import com.google.gson.Gson;
//import com.google.gson.GsonBuilder;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import ottu.payment.toast.Fetchdata.Models.GetAdId.GetAdId;
import ottu.payment.toast.Fetchdata.Models.GetFavoriteApp.GetFavoriteApp;
import ottu.payment.toast.Fetchdata.Models.InstallRespo.InstallRespo;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public class RetrofitClientInstance {

    private static Retrofit retrofit;
    private static final String BASE_URL = "https://falconinfosoft.com/app/api/";

    public static GetDataService getRetrofitInstance() {

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .connectTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .build();
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .client(client)
                    .build();
            return retrofit.create(GetDataService.class);
        }
        return retrofit.create(GetDataService.class);
    }

    public interface GetDataService {

        @FormUrlEncoded
        @POST("app/ads")
        Call<GetAdId> getIds(@Field("package_name") String packageName);
        @FormUrlEncoded
        @POST("app/appInstall")
        Call<InstallRespo> setInstal(@Field("package_name") String packageName);
        @FormUrlEncoded
        @POST("app/getFavoriteApp")
        Call<GetFavoriteApp> getFavApps(@Field("fid") String fid);
    }
}