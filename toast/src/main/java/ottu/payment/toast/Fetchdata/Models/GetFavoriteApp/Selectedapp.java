package ottu.payment.toast.Fetchdata.Models.GetFavoriteApp;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Selectedapp {

    @SerializedName("app_id")
    @Expose
    private String appId;
    @SerializedName("app_name")
    @Expose
    private String appName;
    @SerializedName("package_name")
    @Expose
    private String packageName;
    @SerializedName("app_logo")
    @Expose
    private String appLogo;

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getAppLogo() {
        return appLogo;
    }

    public void setAppLogo(String appLogo) {
        this.appLogo = appLogo;
    }

}
