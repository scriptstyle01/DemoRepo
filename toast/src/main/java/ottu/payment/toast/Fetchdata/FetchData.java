package ottu.payment.toast.Fetchdata;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;
import android.widget.Toast;

import ottu.payment.toast.Fetchdata.Models.GetAdId.GetAdId;
import ottu.payment.toast.Fetchdata.Models.InstallRespo.InstallRespo;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FetchData {
    public static String g_app, g_bnr, g_ntv, g_int, g_open, fb_nb, fb_ntv, fb_int, fb_int2, fb_int3, o_ad1, o_ad2, o_ad3, o_ad4, o_ad5 = "01";
    public static String Ads_sequence,Ads_Status;
    
    public void setInstall(Context context){
        if (isNetworkAvailable(context)) {
            Util_FetchData util_fetchData = new Util_FetchData(context);
            if (util_fetchData.getsetInstall()){
                return;
            }
            RetrofitClientInstance.GetDataService apiendPoint = new RetrofitClientInstance().getRetrofitInstance();
            Call<InstallRespo> register = apiendPoint.setInstal(context.getPackageName());
            register.enqueue(new Callback<InstallRespo>() {
                @Override
                public void onResponse(Call<InstallRespo> call, Response<InstallRespo> response) {

                    if (response.body().getStatus().equals("success")) {
                        Util_FetchData util_fetchData = new Util_FetchData(context);
                        util_fetchData.setsetInstall(true);
                    }

                }

                @Override
                public void onFailure(Call<InstallRespo> call, Throwable t) {
                    Toast.makeText(context, "" + t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
    private void FetchAppData(Context context) {
        if (isNetworkAvailable(context)) {
//            final ProgressDialog dialog = new ProgressDialog(Splash_Activity.this);
//            dialog.setMessage("Please wait for a moment. Fetching data.");
//            dialog.setCanceledOnTouchOutside(true);
//            dialog.show();
            RetrofitClientInstance.GetDataService apiendPoint = new RetrofitClientInstance().getRetrofitInstance();
            Call<GetAdId> register = apiendPoint.getIds(context.getPackageName());
            register.enqueue(new Callback<GetAdId>() {
                @Override
                public void onResponse(Call<GetAdId> call, Response<GetAdId> response) {
//                    dialog.dismiss();

                    if (response.body().getStatus().equals("success")) {
                        GetAdId getAdId = response.body();
                        g_app = getAdId.getData().getGoogleAppId();
                        g_bnr = getAdId.getData().getGoogleBanner();
                        g_ntv = getAdId.getData().getGoogleNative();
                        g_int = getAdId.getData().getGoogleInterstitial();
                        g_open = getAdId.getData().getGoogleOpen();
                        fb_nb = getAdId.getData().getFbNativeBanner();
                        fb_ntv = getAdId.getData().getFbNative();
                        fb_int = getAdId.getData().getFbInterstitial();
                        fb_int2 = getAdId.getData().getFbInterstitial2();
                        fb_int3 = getAdId.getData().getFbInterstitial3();
                        o_ad1 = getAdId.getData().getOtherId1();
                        o_ad2 = getAdId.getData().getOtherId2();
                        o_ad3 = getAdId.getData().getOtherId3();
                        o_ad4 = getAdId.getData().getOtherId4();
                        o_ad5 = getAdId.getData().getOtherId5();
                        Ads_sequence = getAdId.getData().getAdsSequence();
                        Ads_Status = getAdId.getData().getAppAdsStatus();

                        Util_FetchData util_fetchData = new Util_FetchData(context);
                        util_fetchData.Util_FetchData1(Ads_sequence,g_app, g_bnr, g_ntv, g_int, g_open, fb_nb, fb_ntv, fb_int, fb_int2, fb_int3, o_ad1, o_ad2, o_ad3, o_ad4, o_ad5,Ads_Status);

                        Log.e("======fc=====",Ads_sequence+"  "+g_app+" "+g_bnr+" "+g_ntv+" "+g_int+" "+g_open+" "+fb_nb+" "+fb_ntv+" "+fb_int+" "+fb_int2+" "+fb_int3+" "+o_ad1+" "+o_ad2+" "+o_ad3+" "+o_ad4+" "+o_ad5+" ");


                    }else {
                        Util_FetchData utilFetchData = new Util_FetchData(context);
                        g_app = utilFetchData.getg_app();
                        g_bnr = utilFetchData.getg_bnr();
                        g_ntv = utilFetchData.getg_ntv();
                        g_int = utilFetchData.getg_int();
                        g_open = utilFetchData.getg_open();
                        fb_nb = utilFetchData.getfb_nb();
                        fb_ntv = utilFetchData.getfb_ntv();
                        fb_int = utilFetchData.getfb_int();
                        fb_int2 = utilFetchData.getfb_int2();
                        fb_int3 = utilFetchData.getfb_int3();
                        o_ad1 = utilFetchData.geto_ad1();
                        o_ad2 = utilFetchData.geto_ad2();
                        o_ad3 = utilFetchData.geto_ad3();
                        o_ad4 = utilFetchData.geto_ad4();
                        o_ad5 = utilFetchData.geto_ad5();
                        Ads_sequence = utilFetchData.get_adsequnce();
                        Ads_Status = utilFetchData.get_adStatus();

                    }

                }

                @Override
                public void onFailure(Call<GetAdId> call, Throwable t) {
//                    dialog.dismiss();
                    Toast.makeText(context, "" + t.getMessage(), Toast.LENGTH_SHORT).show();
                    Util_FetchData utilFetchData = new Util_FetchData(context);
                    g_app = utilFetchData.getg_app();
                    g_bnr = utilFetchData.getg_bnr();
                    g_ntv = utilFetchData.getg_ntv();
                    g_int = utilFetchData.getg_int();
                    g_open = utilFetchData.getg_open();
                    fb_nb = utilFetchData.getfb_nb();
                    fb_ntv = utilFetchData.getfb_ntv();
                    fb_int = utilFetchData.getfb_int();
                    fb_int2 = utilFetchData.getfb_int2();
                    fb_int3 = utilFetchData.getfb_int3();
                    o_ad1 = utilFetchData.geto_ad1();
                    o_ad2 = utilFetchData.geto_ad2();
                    o_ad3 = utilFetchData.geto_ad3();
                    o_ad4 = utilFetchData.geto_ad4();
                    o_ad5 = utilFetchData.geto_ad5();
                    Ads_sequence = utilFetchData.get_adsequnce();
                    Ads_Status = utilFetchData.get_adStatus();

                }
            });
        } else {
            Util_FetchData utilFetchData = new Util_FetchData(context);
            g_app = utilFetchData.getg_app();
            g_bnr = utilFetchData.getg_bnr();
            g_ntv = utilFetchData.getg_ntv();
            g_int = utilFetchData.getg_int();
            g_open = utilFetchData.getg_open();
            fb_nb = utilFetchData.getfb_nb();
            fb_ntv = utilFetchData.getfb_ntv();
            fb_int = utilFetchData.getfb_int();
            fb_int2 = utilFetchData.getfb_int2();
            fb_int3 = utilFetchData.getfb_int3();
            o_ad1 = utilFetchData.geto_ad1();
            o_ad2 = utilFetchData.geto_ad2();
            o_ad3 = utilFetchData.geto_ad3();
            o_ad4 = utilFetchData.geto_ad4();
            o_ad5 = utilFetchData.geto_ad5();
            Ads_sequence = utilFetchData.get_adsequnce();
            Ads_Status = utilFetchData.get_adStatus();

        }
    }
    private boolean isNetworkAvailable(Context context) {
        ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = manager.getActiveNetworkInfo();
        boolean isAvailable = false;
        if (networkInfo != null && networkInfo.isConnected()) {
            // Network is present and connected
            isAvailable = true;
        }
        return isAvailable;
    }
}
