package ottu.payment.toast.Fetchdata;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class Util_FetchData {

    private String Mypreference = "MyPreference";
    public SharedPreferences prefs;
    public SharedPreferences.Editor editor;
    String pref_g_app = "pref_g_app";
    String pref_g_bnr = "pref_g_bnr";
    String pref_g_ntv = "pref_g_ntv";
    String pref_g_int = "pref_g_int";
    String pref_g_open = "pref_g_open";
    String pref_fb_nb = "pref_fb_nb";
    String pref_fb_ntv = "pref_fb_ntv";
    String pref_fb_int= "pref_fb_int";
    String pref_fb_int2= "pref_fb_int2";
    String pref_fb_int3= "pref_fb_int3";
    String pref_o_ad1= "pref_o_ad1";
    String pref_o_ad2= "pref_o_ad2";
    String pref_o_ad3= "pref_o_ad3";
    String pref_o_ad4= "pref_o_ad4";
    String pref_o_ad5= "pref_o_ad5";
    String pref_adsequnce= "pref_adsequnce";
    String pref_adStatus= "pref_adStatus";
    String pref_setInstall= "pref_setInstall";
    Context context;

    public Util_FetchData(Context context) {
        this.context = context;
         prefs = context.getSharedPreferences(Mypreference, context.MODE_PRIVATE);
        editor = prefs.edit();
        editor.commit();
    }
    public void Util_FetchData1(String Ads_sequence,String pref_g_app, String pref_g_bnr, String pref_g_ntv, String pref_g_int, String pref_g_open
            , String pref_fb_nb, String pref_fb_ntv, String pref_fb_int, String pref_fb_int2, String pref_fb_int3
            , String pref_o_ad1, String pref_o_ad2, String pref_o_ad3, String pref_o_ad4, String pref_o_ad5,String Adstatus) {

        prefs = context.getSharedPreferences(Mypreference, context.MODE_PRIVATE);
        editor = prefs.edit();
        editor.commit();

        set_adsequnce(Ads_sequence);
        setg_app(pref_g_app);
        setg_bnr(pref_g_bnr);
        setg_ntv(pref_g_ntv);
        setg_int(pref_g_int);
        setg_open(pref_g_open);
        setfb_nb(pref_fb_nb);
        setfb_ntv(pref_fb_ntv);
        setfb_int(pref_fb_int);
        setfb_int2(pref_fb_int2);
        setfb_int3(pref_fb_int3);
        seto_ad1(pref_o_ad1);
        seto_ad2(pref_o_ad2);
        seto_ad3(pref_o_ad3);
        seto_ad4(pref_o_ad4);
        seto_ad5(pref_o_ad5);
        set_adStatus(Adstatus);

    }

    public boolean isNetworkAvailable() {
        ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = manager.getActiveNetworkInfo();
        boolean isAvailable = false;
        if (networkInfo != null && networkInfo.isConnected()) {
            // Network is present and connected
            isAvailable = true;
        }
        return isAvailable;
    }

    public void set_adsequnce(String num) {
        editor.putString(pref_adsequnce, num);
        editor.commit();
    }
    public String get_adsequnce() {
        String i = prefs.getString(pref_adsequnce, "20");
        return i;
    }

    public void setsetInstall(boolean num) {
        editor.putBoolean(pref_setInstall, num);
        editor.commit();
    }
    public boolean getsetInstall() {
        boolean i = prefs.getBoolean(pref_setInstall, false);
        return i;
    }
    public void seto_ad5(String num) {
        editor.putString(pref_o_ad5, num);
        editor.commit();
    }
    public void set_adStatus(String num) {
        editor.putString(pref_adStatus, num);
        editor.commit();
    }
    public String get_adStatus() {
        String i = prefs.getString(pref_adStatus, "1");
        return i;
    }
    public String geto_ad5() {
        String i = prefs.getString(pref_o_ad5, "20");
        return i;
    }
    public void seto_ad4(String num) {
        editor.putString(pref_o_ad4, num);
        editor.commit();
    }
    public String geto_ad4() {
        String i = prefs.getString(pref_o_ad4, "20");
        return i;
    }
    public void seto_ad3(String num) {
        editor.putString(pref_o_ad3, num);
        editor.commit();
    }
    public String geto_ad3() {
        String i = prefs.getString(pref_o_ad3, "20");
        return i;
    }
    public void seto_ad2(String num) {
        editor.putString(pref_o_ad2, num);
        editor.commit();
    }
    public String geto_ad2() {
        String i = prefs.getString(pref_o_ad2, "20");
        return i;
    }
    public void seto_ad1(String num) {
        editor.putString(pref_o_ad1, num);
        editor.commit();
    }
    public String geto_ad1() {
        String i = prefs.getString(pref_o_ad1, "20");
        return i;
    }
    public void setfb_int3(String num) {
        editor.putString(pref_fb_int3, num);
        editor.commit();
    }
    public String getfb_int3() {
        String i = prefs.getString(pref_fb_int3, "20");
        return i;
    }
    public void setfb_int2(String num) {
        editor.putString(pref_fb_int2, num);
        editor.commit();
    }
    public String getfb_int2() {
        String i = prefs.getString(pref_fb_int2, "20");
        return i;
    }
    public void setfb_int(String num) {
        editor.putString(pref_fb_int, num);
        editor.commit();
    }
    public String getfb_int() {
        String i = prefs.getString(pref_fb_int, "20");
        return i;
    }
    public void setfb_ntv(String num) {
        editor.putString(pref_fb_ntv, num);
        editor.commit();
    }
    public String getfb_ntv() {
        String i = prefs.getString(pref_fb_ntv, "20");
        return i;
    }
    public void setfb_nb(String num) {
        editor.putString(pref_fb_nb, num);
        editor.commit();
    }
    public String getfb_nb() {
        String i = prefs.getString(pref_fb_nb, "20");
        return i;
    }
    public void setg_open(String num) {
        editor.putString(pref_g_open, num);
        editor.commit();
    }
    public String getg_open() {
        String i = prefs.getString(pref_g_open, "20");
        return i;
    }
    public void setg_int(String num) {
        editor.putString(pref_g_int, num);
        editor.commit();
    }
    public String getg_int() {
        String i = prefs.getString(pref_g_int, "20");
        return i;
    }
    public void setg_ntv(String num) {
        editor.putString(pref_g_ntv, num);
        editor.commit();
    }
    public String getg_ntv() {
        String i = prefs.getString(pref_g_ntv, "20");
        return i;
    }
    public void setg_bnr(String num) {
        editor.putString(pref_g_bnr, num);
        editor.commit();
    }
    public String getg_bnr() {
        String i = prefs.getString(pref_g_bnr, "20");
        return i;
    }
    public void setg_app(String num) {
        editor.putString(pref_g_app, num);
        editor.commit();
    }
    public String getg_app() {
        String i = prefs.getString(pref_g_app, "20");
        return i;
    }
}
