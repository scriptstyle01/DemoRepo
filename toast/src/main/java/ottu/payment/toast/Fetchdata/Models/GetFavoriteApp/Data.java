package ottu.payment.toast.Fetchdata.Models.GetFavoriteApp;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;


public class Data {

    @SerializedName("favoriteapp_id")
    @Expose
    private String favoriteappId;
    @SerializedName("app_id")
    @Expose
    private String appId;
    @SerializedName("selectedapp")
    @Expose
    private List<Selectedapp> selectedapp = null;

    public String getFavoriteappId() {
        return favoriteappId;
    }

    public void setFavoriteappId(String favoriteappId) {
        this.favoriteappId = favoriteappId;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public List<Selectedapp> getSelectedapp() {
        return selectedapp;
    }

    public void setSelectedapp(List<Selectedapp> selectedapp) {
        this.selectedapp = selectedapp;
    }

}
