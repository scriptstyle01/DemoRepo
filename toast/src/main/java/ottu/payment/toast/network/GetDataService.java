package ottu.payment.toast.network;

import okhttp3.ResponseBody;
import ottu.payment.toast.model.GenerateToken.CreatePaymentTransaction;
import ottu.payment.toast.model.RedirectUrl.CreateRedirectUrl;
import ottu.payment.toast.model.RedirectUrl.RespoRedirectUrl;
import ottu.payment.toast.model.fetchTxnDetail.RespoFetchTxnDetail;
import ottu.payment.toast.model.submitCHD.SubmitCHDToOttoPG;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.Url;

public interface GetDataService {


//    @GET("checkout/v1/pymt-txn/submit/{apiId}")
    @GET("checkout/api/sdk/v1/pymt-txn/submit/{apiId}")
    Call<RespoFetchTxnDetail> fetchTxnDetail(@Path ("apiId") String apiId,
                                                @Query("enableCHD") boolean value);

//    @POST("route")
    @POST()
    Call<ResponseBody> respoSubmitCHD(@Url String submitUrlCard,@Body SubmitCHDToOttoPG submitCHDToOttoPG);


    @POST("checkout/v1/pymt-txn/")
    Call<RespoFetchTxnDetail> createPaymentTxn(@Body CreatePaymentTransaction transaction);

    @POST()
    Call<RespoRedirectUrl> createRedirectUrl(@Url String url,
                                             @Body CreateRedirectUrl redirectUrl);

    @DELETE()
    Call<ResponseBody> deleteCard1(@Url String token);
}