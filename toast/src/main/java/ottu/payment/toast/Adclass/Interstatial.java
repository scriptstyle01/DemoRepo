package ottu.payment.toast.Adclass;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.util.Log;

import androidx.annotation.NonNull;

import com.facebook.ads.Ad;
import com.facebook.ads.AdError;
import com.facebook.ads.InterstitialAd;
import com.facebook.ads.InterstitialAdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;

import ottu.payment.toast.Fetchdata.Util_FetchData;

import static ottu.payment.toast.Adclass.AppOpenManager.isOpenAdShowing;
import static ottu.payment.toast.Fetchdata.FetchData.Ads_sequence;
import static ottu.payment.toast.Fetchdata.FetchData.fb_int;
import static ottu.payment.toast.Fetchdata.FetchData.g_int;


public class Interstatial {
    Activity activity;
    private ProgressDialog progressDialog = null;
    public static InterstitialAd interstitialAd;
    public static com.google.android.gms.ads.interstitial.InterstitialAd mInterstitialAd;
    static int AdsCount = 0;
    public static boolean isInterstatialAdShowing = false;
    public Interstatial(Activity activity) {

        this.activity = activity;
//        loadInterstitial();
    }



    public void Load_FbInt() {

        Log.e("InInterstatal------", "====requast====" + AdsCount);
        interstitialAd = new InterstitialAd(activity, fb_int);
        InterstitialAdListener interstitialAdListener = new InterstitialAdListener() {
            @Override
            public void onInterstitialDisplayed(Ad ad) {
                // Interstitial ad displayed callback
                Log.e("FB Intrstatialr===load", "Interstitial ad displayed.");
                isInterstatialAdShowing = true;
            }

            @Override
            public void onInterstitialDismissed(Ad ad) {
                // Interstitial dismissed callback
                isInterstatialAdShowing = false;
                Log.e("FB Intrstatialr===load", "Interstitial ad dismissed.");
                Load_FbInt();
            }

            @Override
            public void onError(Ad ad, AdError adError) {
                // Ad error callback
                Log.e("FB Intrstatialr===", "Interstitial ad failed to load: " + adError.getErrorMessage());
                Load_admobInt();
            }

            @Override
            public void onAdLoaded(Ad ad) {
                // Interstitial ad is loaded and ready to be displayed
                Log.d("FB Intrstatialr===", "Interstitial ad is loaded and ready to be displayed!");
                // Show the ad
            }

            @Override
            public void onAdClicked(Ad ad) {
                // Ad clicked callback
                Log.d("FB Intrstatialr===", "Interstitial ad clicked!");
            }

            @Override
            public void onLoggingImpression(Ad ad) {
                // Ad impression logged callback
                Log.d("FB Intrstatialr===", "Interstitial ad impression logged!");
            }
        };

        interstitialAd.loadAd(interstitialAd.buildLoadAdConfig().withAdListener(interstitialAdListener).build());
    }

    @SuppressLint("MissingPermission")
    public void Show_AdmobInt() {

        if (mInterstitialAd == null) {
            AdRequest adRequest = new AdRequest.Builder().build();
            com.google.android.gms.ads.interstitial.InterstitialAd.load(activity,g_int, adRequest, new InterstitialAdLoadCallback() {
                @Override
                public void onAdLoaded(@NonNull com.google.android.gms.ads.interstitial.InterstitialAd interstitialAd) {
                    // The mInterstitialAd reference will be null until
                    // an ad is loaded.
                    mInterstitialAd = interstitialAd;
                    mInterstitialAd.show(activity);
                    Log.i("Show_AdmobInt=====", "onAdLoaded");

                    mInterstitialAd.setFullScreenContentCallback(new FullScreenContentCallback(){
                        @Override
                        public void onAdDismissedFullScreenContent() {
                            // Called when fullscreen content is dismissed.
                            Log.e("Show_AdmobInt=====", "The ad was dismissed.");
                            isInterstatialAdShowing= false;
                            mInterstitialAd = null;
                            Load_FbInt();
                        }
                        @Override
                        public void onAdFailedToShowFullScreenContent(@NonNull com.google.android.gms.ads.AdError adError) {
                            super.onAdFailedToShowFullScreenContent(adError);
                            Log.e("Show_AdmobInt=====", "The ad failed to show." + adError);
                            if (progressDialog.isShowing()){
                                progressDialog.dismiss();
                            }
                            Load_FbInt();
                        }
                        @Override
                        public void onAdShowedFullScreenContent() {
                            mInterstitialAd = null;
                            if (progressDialog.isShowing()){
                                progressDialog.dismiss();
                            }
                            isInterstatialAdShowing = true;
                            Log.e("Show_AdmobInt=====", "The ad was shown.");
                        }
                    });
                }

                @Override
                public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                    // Handle the error
                    Log.i("Show_AdmobInt=====", loadAdError.getMessage());
                    mInterstitialAd = null;
                    if (progressDialog.isShowing()){
                        progressDialog.dismiss();
                    }
                    Load_FbInt();
                }
            });
            Log.e("Admob show call====",  "");
        }else if (mInterstitialAd != null ) {
            Log.e("Admob show ====", "Loaded");
            progressDialog.dismiss();
            mInterstitialAd.show(activity);
        } else {
            Log.e("Admob", "ELSE part of ShowAdmobInt.");
            progressDialog.dismiss();
            Load_FbInt();
        }

    }

    public void Show_FbInt() {
        // Test ID  = CAROUSEL_IMG_SQUARE_APP_INSTALL#YOUR_PLACEMENT_ID
        if (isOpenAdShowing){
            return;
        }
        if (AdsCount > 20) {
            return;
        }
        AdsCount++;
        progressDialog = ProgressDialog.show(activity,"Fetching Data", "Don't Touch the Screen, Please Wait... .");
//        progressDialog = ProgressDialog.show(activity, activity.getResources().getString(R.string.app_name), "Don't Touch the Screen, Please Wait... .");
        Log.e("Show_FbInt === ", "");
        if (interstitialAd == null || !interstitialAd.isAdLoaded()) {
            interstitialAd = new InterstitialAd(activity, fb_int);
            InterstitialAdListener interstitialAdListener = new InterstitialAdListener() {
                @Override
                public void onInterstitialDisplayed(Ad ad) {
                    // Interstitial ad displayed callback
                    Log.e("FB Intrstatialr===", "Interstitial ad displayed.");
                    isInterstatialAdShowing = true;
                }

                @Override
                public void onInterstitialDismissed(Ad ad) {
                    // Interstitial dismissed callback
                    Log.e("FB Intrstatialr===", "Interstitial ad dismissed.");
                    Load_FbInt();
                    isInterstatialAdShowing = false;
                }

                @Override
                public void onError(Ad ad, AdError adError) {
                    // Ad error callback
                    Log.e("FB Intrstatialr===", "Interstitial ad failed to load: " + adError.getErrorMessage());
                    Show_AdmobInt();
                }

                @Override
                public void onAdLoaded(Ad ad) {
                    // Interstitial ad is loaded and ready to be displayed
                    Log.d("FB Intrstatialr===", "Interstitial ad is loaded and ready to be displayed!");
                    // Show the ad
                    progressDialog.dismiss();
                    interstitialAd.show();

                }

                @Override
                public void onAdClicked(Ad ad) {
                    // Ad clicked callback
                    Log.d("FB Intrstatialr===", "Interstitial ad clicked!");
                }

                @Override
                public void onLoggingImpression(Ad ad) {
                    // Ad impression logged callback
                    Log.d("FB Intrstatialr===", "Interstitial ad impression logged!");
                }
            };

            interstitialAd.loadAd(interstitialAd.buildLoadAdConfig().withAdListener(interstitialAdListener).build());
        } else if (interstitialAd != null && interstitialAd.isAdLoaded()) {
            progressDialog.dismiss();
            interstitialAd.show();
        } else {
            Log.e("FB not loded=====", "else part of FBshow");
        }
    }

    public void Show_Int(){
        if (!new Util_FetchData(activity).isNetworkAvailable()){
            return;
        }
        if (isOpenAdShowing){
            return;
        }
        if (Ads_sequence.equals("1")){
            Show_Admob_Fb();
        }else if (Ads_sequence.equals("2")){
            Show_Fb_Admob();
        }else if (Ads_sequence.equals("4")){
            Show_Admob_Int();
        }
    }

    public void Load_Int(){
        if (!new Util_FetchData(activity).isNetworkAvailable()){
            return;
        }
        if (Ads_sequence.equals("1")){
            Load_Admob_Fb();
        }else if (Ads_sequence.equals("2")){
            Load_Fb_Admob();
        }else if (Ads_sequence.equals("4")){
            Load_admobInt();
        }
    }
    public void Show_Admob_Fb(){
        progressDialog = ProgressDialog.show(activity,"Fetching Data", "Don't Touch the Screen, Please Wait... .");
//        progressDialog = ProgressDialog.show(activity, activity.getResources().getString(R.string.app_name), "Don't Touch the Screen, Please Wait... .");
        if (mInterstitialAd == null) {
            AdRequest adRequest = new AdRequest.Builder().build();
            com.google.android.gms.ads.interstitial.InterstitialAd.load(activity,g_int, adRequest, new InterstitialAdLoadCallback() {
                @Override
                public void onAdLoaded(@NonNull com.google.android.gms.ads.interstitial.InterstitialAd interstitialAd1) {
                    mInterstitialAd = interstitialAd1;
                    mInterstitialAd.show(activity);
                    Log.i("Show_AdmobInt=====", "onAdLoaded");

                    mInterstitialAd.setFullScreenContentCallback(new FullScreenContentCallback(){
                        @Override
                        public void onAdDismissedFullScreenContent() {
                            // Called when fullscreen content is dismissed.
                            Log.e("Show_AdmobInt=====", "The ad was dismissed.");
                            isInterstatialAdShowing= false;
                            mInterstitialAd = null;
                            Load_Admob_Fb();
                        }
                        @Override
                        public void onAdFailedToShowFullScreenContent(@NonNull com.google.android.gms.ads.AdError adError) {
                            super.onAdFailedToShowFullScreenContent(adError);
                            Log.e("Show_AdmobInt=====", "The ad failed to show." + adError);
                            if (interstitialAd == null || !interstitialAd.isAdLoaded()) {
                                interstitialAd = new InterstitialAd(activity, fb_int);
                                InterstitialAdListener interstitialAdListener = new InterstitialAdListener() {
                                    @Override
                                    public void onInterstitialDisplayed(Ad ad) {
                                        // Interstitial ad displayed callback
                                        Log.e("FB Intrstatialr===", "Interstitial ad displayed.");
                                        isInterstatialAdShowing = true;
                                    }

                                    @Override
                                    public void onInterstitialDismissed(Ad ad) {
                                        // Interstitial dismissed callback
                                        Log.e("FB Intrstatialr===", "Interstitial ad dismissed.");
                                        Load_Admob_Fb();
                                        isInterstatialAdShowing = false;
                                    }

                                    @Override
                                    public void onError(Ad ad, AdError adError) {
                                        // Ad error callback
                                        Log.e("FB Intrstatialr===", "Interstitial ad failed to load: " + adError.getErrorMessage());
                                        Load_Admob_Fb();
                                    }

                                    @Override
                                    public void onAdLoaded(Ad ad) {
                                        // Interstitial ad is loaded and ready to be displayed
                                        Log.d("FB Intrstatialr===", "Interstitial ad is loaded and ready to be displayed!");
                                        // Show the ad
                                        if (progressDialog.isShowing()) {
                                            progressDialog.dismiss();
                                        }
                                        interstitialAd.show();

                                    }

                                    @Override
                                    public void onAdClicked(Ad ad) {
                                        // Ad clicked callback
                                        Log.d("FB Intrstatialr===", "Interstitial ad clicked!");
                                    }

                                    @Override
                                    public void onLoggingImpression(Ad ad) {
                                        // Ad impression logged callback
                                        Log.d("FB Intrstatialr===", "Interstitial ad impression logged!");
                                    }
                                };

                                interstitialAd.loadAd(interstitialAd.buildLoadAdConfig().withAdListener(interstitialAdListener).build());
                            } else if (interstitialAd != null && interstitialAd.isAdLoaded()) {
                                if (progressDialog.isShowing()) {
                                    progressDialog.dismiss();
                                }
                                interstitialAd.show();
                            } else {
                                Log.e("FB not loded=====", "else part of FBshow");
                            }
                        }
                        @Override
                        public void onAdShowedFullScreenContent() {
                            mInterstitialAd = null;
                            if (progressDialog.isShowing()){
                                progressDialog.dismiss();
                            }
                            isInterstatialAdShowing = true;
                            Log.e("Show_AdmobInt=====", "The ad was shown.");
                        }
                    });
                }

                @Override
                public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                    // Handle the error
                    Log.i("Show_AdmobInt=====", loadAdError.getMessage());
                    mInterstitialAd = null;
                    if (progressDialog.isShowing()){
                        progressDialog.dismiss();
                    }
                    Load_Admob_Fb();
                }
            });
            Log.e("Admob show call====",  "");
        }else if (mInterstitialAd != null ) {
            Log.e("Admob show ====", "Loaded");
            if (progressDialog.isShowing()) {
                progressDialog.dismiss();
            }
            mInterstitialAd.show(activity);
        } else {
            Log.e("Admob", "ELSE part of ShowAdmobInt.");
            if (progressDialog.isShowing()) {
                progressDialog.dismiss();
            }
            Load_Admob_Fb();
        }
    }

    public void Show_Fb_Admob(){
        progressDialog = ProgressDialog.show(activity,"Fetching Data", "Don't Touch the Screen, Please Wait... .");
//        progressDialog = ProgressDialog.show(activity, activity.getResources().getString(R.string.app_name), "Don't Touch the Screen, Please Wait... .");
        interstitialAd = new InterstitialAd(activity, fb_int);
        InterstitialAdListener interstitialAdListener = new InterstitialAdListener() {
            @Override
            public void onInterstitialDisplayed(Ad ad) {
                // Interstitial ad displayed callback
                Log.e("FB Intrstatialr===load", "Interstitial ad displayed.");
                isInterstatialAdShowing = true;
            }

            @Override
            public void onInterstitialDismissed(Ad ad) {
                // Interstitial dismissed callback
                isInterstatialAdShowing = false;
                Log.e("FB Intrstatialr===load", "Interstitial ad dismissed.");
                Load_Fb_Admob();
            }

            @Override
            public void onError(Ad ad, AdError adError) {
                // Ad error callback
                Log.e("FB Intrstatialr===", "Interstitial ad failed to load: " + adError.getErrorMessage());
                if (mInterstitialAd == null) {
                    AdRequest adRequest = new AdRequest.Builder().build();
                    com.google.android.gms.ads.interstitial.InterstitialAd.load(activity,g_int, adRequest, new InterstitialAdLoadCallback() {
                        @Override
                        public void onAdLoaded(@NonNull com.google.android.gms.ads.interstitial.InterstitialAd interstitialAd) {
                            mInterstitialAd = interstitialAd;
                            mInterstitialAd.show(activity);
                            Log.i("Show_AdmobInt=====", "onAdLoaded");

                            mInterstitialAd.setFullScreenContentCallback(new FullScreenContentCallback(){
                                @Override
                                public void onAdDismissedFullScreenContent() {
                                    // Called when fullscreen content is dismissed.
                                    Log.e("Show_AdmobInt=====", "The ad was dismissed.");
                                    isInterstatialAdShowing= false;
                                    mInterstitialAd = null;
                                    Load_Fb_Admob();
                                }
                                @Override
                                public void onAdFailedToShowFullScreenContent(@NonNull com.google.android.gms.ads.AdError adError) {
                                    super.onAdFailedToShowFullScreenContent(adError);
                                    Log.e("Show_AdmobInt=====", "The ad failed to show." + adError);
                                    if (progressDialog.isShowing()){
                                        progressDialog.dismiss();
                                    }
                                    Load_Fb_Admob();
                                }
                                @Override
                                public void onAdShowedFullScreenContent() {
                                    mInterstitialAd = null;
                                    if (progressDialog.isShowing()){
                                        progressDialog.dismiss();
                                    }
                                    isInterstatialAdShowing = true;
                                    Log.e("Show_AdmobInt=====", "The ad was shown.");
                                }
                            });
                        }

                        @Override
                        public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                            // Handle the error
                            Log.i("Show_AdmobInt=====", loadAdError.getMessage());
                            mInterstitialAd = null;
                            if (progressDialog.isShowing()){
                                progressDialog.dismiss();
                            }
                            Load_FbInt();
                        }
                    });
                    Log.e("Admob show call====",  "");
                }else if (mInterstitialAd != null ) {
                    Log.e("Admob show ====", "Loaded");
                    progressDialog.dismiss();
                    mInterstitialAd.show(activity);
                } else {
                    Log.e("Admob", "ELSE part of ShowAdmobInt.");
                    progressDialog.dismiss();
                    Load_FbInt();
                }
            }

            @Override
            public void onAdLoaded(Ad ad) {
                // Interstitial ad is loaded and ready to be displayed
                Log.d("FB Intrstatialr===", "Interstitial ad is loaded and ready to be displayed!");
                interstitialAd.show();
                if (progressDialog.isShowing()){
                    progressDialog.dismiss();
                }
                // Show the ad
            }

            @Override
            public void onAdClicked(Ad ad) {
                // Ad clicked callback
                Log.d("FB Intrstatialr===", "Interstitial ad clicked!");
            }

            @Override
            public void onLoggingImpression(Ad ad) {
                // Ad impression logged callback
                Log.d("FB Intrstatialr===", "Interstitial ad impression logged!");
            }
        };
        interstitialAd.loadAd(interstitialAd.buildLoadAdConfig().withAdListener(interstitialAdListener).build());
    }

    public void Load_Admob_Fb(){
        AdRequest adRequest = new AdRequest.Builder().build();
        com.google.android.gms.ads.interstitial.InterstitialAd.load(activity,g_int, adRequest, new InterstitialAdLoadCallback() {
            @Override
            public void onAdLoaded(@NonNull com.google.android.gms.ads.interstitial.InterstitialAd interstitialAd) {
                // The mInterstitialAd reference will be null until
                // an ad is loaded.
                mInterstitialAd = interstitialAd;
                Log.e("admob int=======", "onAdLoaded");

                mInterstitialAd.setFullScreenContentCallback(new FullScreenContentCallback() {
                    @Override
                    public void onAdFailedToShowFullScreenContent(@NonNull com.google.android.gms.ads.AdError adError) {
                        super.onAdFailedToShowFullScreenContent(adError);
                    }

                    @Override
                    public void onAdShowedFullScreenContent() {
                        super.onAdShowedFullScreenContent();
                        isInterstatialAdShowing = true;
                        Log.e("In Admob Load ======","isADMOB Int Showing = "+ isInterstatialAdShowing );
                    }

                    @Override
                    public void onAdDismissedFullScreenContent() {
                        super.onAdDismissedFullScreenContent();
                        isInterstatialAdShowing = false;
                        mInterstitialAd = null;
                        Load_Admob_Fb();
                        Log.e("In Admob Load ======","isADMOB Int Diasmiss = " );
                    }
                });
            }

            @Override
            public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                // Handle the error
                Log.e("admob int=====load fail", loadAdError.getMessage());
                mInterstitialAd = null;
                interstitialAd = new InterstitialAd(activity, fb_int);
                InterstitialAdListener interstitialAdListener = new InterstitialAdListener() {
                    @Override
                    public void onInterstitialDisplayed(Ad ad) {
                        // Interstitial ad displayed callback
                        Log.e("FB Intrstatialr===load", "Interstitial ad displayed.");
                        isInterstatialAdShowing = true;
                    }

                    @Override
                    public void onInterstitialDismissed(Ad ad) {
                        // Interstitial dismissed callback
                        isInterstatialAdShowing = false;
                        Log.e("FB Intrstatialr===load", "Interstitial ad dismissed.");
                        Load_Admob_Fb();
                    }

                    @Override
                    public void onError(Ad ad, AdError adError) {
                        // Ad error callback
                        Log.e("FB Intrstatialr===", "Interstitial ad failed to load: " + adError.getErrorMessage());

                    }

                    @Override
                    public void onAdLoaded(Ad ad) {
                        // Interstitial ad is loaded and ready to be displayed
                        Log.d("FB Intrstatialr===", "Interstitial ad is loaded and ready to be displayed!");
                        // Show the ad
                    }

                    @Override
                    public void onAdClicked(Ad ad) {
                        // Ad clicked callback
                        Log.d("FB Intrstatialr===", "Interstitial ad clicked!");
                    }

                    @Override
                    public void onLoggingImpression(Ad ad) {
                        // Ad impression logged callback
                        Log.d("FB Intrstatialr===", "Interstitial ad impression logged!");
                    }
                };

                interstitialAd.loadAd(interstitialAd.buildLoadAdConfig().withAdListener(interstitialAdListener).build());

            }

        });
    }
    public void Load_Fb_Admob(){
        interstitialAd = new InterstitialAd(activity, fb_int);
        InterstitialAdListener interstitialAdListener = new InterstitialAdListener() {
            @Override
            public void onInterstitialDisplayed(Ad ad) {
                // Interstitial ad displayed callback
                Log.e("FB Intrstatialr===load", "Interstitial ad displayed.");
                isInterstatialAdShowing = true;
            }

            @Override
            public void onInterstitialDismissed(Ad ad) {
                // Interstitial dismissed callback
                isInterstatialAdShowing = false;
                Log.e("FB Intrstatialr===load", "Interstitial ad dismissed.");
                Load_Fb_Admob();
            }

            @Override
            public void onError(Ad ad, AdError adError) {
                // Ad error callback
                Log.e("FB Intrstatialr===", "Interstitial ad failed to load: " + adError.getErrorMessage());
                AdRequest adRequest = new AdRequest.Builder().build();
                com.google.android.gms.ads.interstitial.InterstitialAd.load(activity,g_int, adRequest, new InterstitialAdLoadCallback() {
                    @Override
                    public void onAdLoaded(@NonNull com.google.android.gms.ads.interstitial.InterstitialAd interstitialAd) {
                        // The mInterstitialAd reference will be null until
                        // an ad is loaded.
                        mInterstitialAd = interstitialAd;
                        Log.e("admob int=======", "onAdLoaded");
                        mInterstitialAd.setFullScreenContentCallback(new FullScreenContentCallback() {
                            @Override
                            public void onAdFailedToShowFullScreenContent(@NonNull com.google.android.gms.ads.AdError adError) {
                                super.onAdFailedToShowFullScreenContent(adError);
                            }

                            @Override
                            public void onAdShowedFullScreenContent() {
                                super.onAdShowedFullScreenContent();
                                isInterstatialAdShowing = true;
                                Log.e("In Admob Load ======","isADMOB Int Showing = "+ isInterstatialAdShowing );
                            }

                            @Override
                            public void onAdDismissedFullScreenContent() {
                                super.onAdDismissedFullScreenContent();
                                isInterstatialAdShowing = false;
                                mInterstitialAd = null;
                                Load_Fb_Admob();
                                Log.e("In Admob Load ======","isADMOB Int Diasmiss = " );
                            }
                        });
                    }

                    @Override
                    public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                        // Handle the error
                        Log.e("admob int=====load fail", loadAdError.getMessage());
                        if (progressDialog.isShowing()){
                            progressDialog.dismiss();
                        }
                        mInterstitialAd = null;

                    }

                });
            }

            @Override
            public void onAdLoaded(Ad ad) {
                // Interstitial ad is loaded and ready to be displayed
                Log.d("FB Intrstatialr===", "Interstitial ad is loaded and ready to be displayed!");
                // Show the ad
            }

            @Override
            public void onAdClicked(Ad ad) {
                // Ad clicked callback
                Log.d("FB Intrstatialr===", "Interstitial ad clicked!");
            }

            @Override
            public void onLoggingImpression(Ad ad) {
                // Ad impression logged callback
                Log.d("FB Intrstatialr===", "Interstitial ad impression logged!");
            }
        };

        interstitialAd.loadAd(interstitialAd.buildLoadAdConfig().withAdListener(interstitialAdListener).build());
    }

    @SuppressLint("MissingPermission")
    public void Load_admobInt() {


        AdRequest adRequest = new AdRequest.Builder().build();
        com.google.android.gms.ads.interstitial.InterstitialAd.load(activity,g_int, adRequest, new InterstitialAdLoadCallback() {
            @Override
            public void onAdLoaded(@NonNull com.google.android.gms.ads.interstitial.InterstitialAd interstitialAd) {
                // The mInterstitialAd reference will be null until
                // an ad is loaded.
                mInterstitialAd = interstitialAd;
                Log.e("admob int=======", "onAdLoaded");

                mInterstitialAd.setFullScreenContentCallback(new FullScreenContentCallback() {
                    @Override
                    public void onAdFailedToShowFullScreenContent(@NonNull com.google.android.gms.ads.AdError adError) {
                        super.onAdFailedToShowFullScreenContent(adError);
                    }

                    @Override
                    public void onAdShowedFullScreenContent() {
                        super.onAdShowedFullScreenContent();
                        isInterstatialAdShowing = true;
                        Log.e("In Admob Load ======","isADMOB Int Showing = "+ isInterstatialAdShowing );
                    }

                    @Override
                    public void onAdDismissedFullScreenContent() {
                        super.onAdDismissedFullScreenContent();
                        isInterstatialAdShowing = false;
                        mInterstitialAd = null;
                        Load_Int();
                        Log.e("In Admob Load ======","isADMOB Int Diasmiss = " );
                    }
                });
            }

            @Override
            public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                // Handle the error
                Log.e("admob int=====load fail", loadAdError.getMessage());
                if (progressDialog.isShowing()){
                    progressDialog.dismiss();
                }
                mInterstitialAd = null;

            }

        });
    }

    private void Show_Admob_Int() {
        progressDialog = ProgressDialog.show(activity,"Fetching Data", "Don't Touch the Screen, Please Wait... .");
//        progressDialog = ProgressDialog.show(activity, activity.getResources().getString(R.string.app_name), "Don't Touch the Screen, Please Wait... .");
        if (mInterstitialAd == null) {
            AdRequest adRequest = new AdRequest.Builder().build();
            com.google.android.gms.ads.interstitial.InterstitialAd.load(activity,g_int, adRequest, new InterstitialAdLoadCallback() {
                @Override
                public void onAdLoaded(@NonNull com.google.android.gms.ads.interstitial.InterstitialAd interstitialAd) {
                    // The mInterstitialAd reference will be null until
                    // an ad is loaded.
                    mInterstitialAd = interstitialAd;
                    mInterstitialAd.show(activity);
                    Log.i("Show_AdmobInt=====", "onAdLoaded");

                    mInterstitialAd.setFullScreenContentCallback(new FullScreenContentCallback(){
                        @Override
                        public void onAdDismissedFullScreenContent() {
                            // Called when fullscreen content is dismissed.
                            Log.e("Show_AdmobInt=====", "The ad was dismissed.");
                            isInterstatialAdShowing= false;
                            mInterstitialAd = null;
                            Load_Int();
                        }
                        @Override
                        public void onAdFailedToShowFullScreenContent(@NonNull com.google.android.gms.ads.AdError adError) {
                            super.onAdFailedToShowFullScreenContent(adError);
                            Log.e("Show_AdmobInt=====", "The ad failed to show." + adError);
                            if (progressDialog.isShowing()){
                                progressDialog.dismiss();
                            }
                            Load_Int();
                        }
                        @Override
                        public void onAdShowedFullScreenContent() {
                            mInterstitialAd = null;
                            if (progressDialog.isShowing()){
                                progressDialog.dismiss();
                            }
                            isInterstatialAdShowing = true;
                            Log.e("Show_AdmobInt=====", "The ad was shown.");
                        }
                    });
                }

                @Override
                public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                    // Handle the error
                    Log.i("Show_AdmobInt=====", loadAdError.getMessage());
                    mInterstitialAd = null;
                    if (progressDialog.isShowing()){
                        progressDialog.dismiss();
                    }
                    Load_Int();
                }
            });
            Log.e("Admob show call====",  "");
        }else if (mInterstitialAd != null ) {
            Log.e("Admob show ====", "Loaded");
            progressDialog.dismiss();
            mInterstitialAd.show(activity);
        } else {
            Log.e("Admob", "ELSE part of ShowAdmobInt.");
            progressDialog.dismiss();
            Load_Int();
        }
    }


}
