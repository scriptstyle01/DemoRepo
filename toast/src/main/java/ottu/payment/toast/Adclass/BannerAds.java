package ottu.payment.toast.Adclass;

import android.app.Activity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.facebook.ads.Ad;
import com.facebook.ads.AdError;
import com.facebook.ads.AdOptionsView;
import com.facebook.ads.MediaView;
import com.facebook.ads.NativeAdLayout;
import com.facebook.ads.NativeAdListener;
import com.facebook.ads.NativeBannerAd;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.LoadAdError;

import java.util.ArrayList;
import java.util.List;

import ottu.payment.toast.Fetchdata.Util_FetchData;
import ottu.payment.toast.R;

import static ottu.payment.toast.Fetchdata.FetchData.Ads_sequence;
import static ottu.payment.toast.Fetchdata.FetchData.fb_nb;
import static ottu.payment.toast.Fetchdata.FetchData.g_bnr;


public class BannerAds {

    Activity activity;
    LinearLayout view;
    ViewGroup unityview;

    public BannerAds(Activity activity, LinearLayout view) {
        this.activity = activity;
        this.view = view;

        if (!new Util_FetchData(activity).isNetworkAvailable()){
            return;
        }
//        Fb_nativebanner();
        if (Ads_sequence.equals("1")){
            banner_Admob_Fb();
        }else if (Ads_sequence.equals("2")){
            banner_Fb_Admob();
        }else if (Ads_sequence.equals("4")){
            admogbanner();
        }
//        UnityBanners.destroy();
//        final IUnityBannerListener myBannerListener = new UnityBannerListener();
//        UnityBanners.setBannerListener((IUnityBannerListener) myBannerListener);
//        UnityBanners.loadBanner(activity, "screenshotbnr");
    }

//    private class UnityBannerListener implements IUnityBannerListener {
//
//        @Override
//        public void onUnityBannerLoaded(String s, View view1) {
//            unityview = view.findViewById(R.id.unityads_example_layout_root);
//            if (unityview == null) {
//                unityview.addView(view1);
//            }
////            else {
////                unityview.removeAllViews();
////                unityview.addView(view1);
////            }
//        }
//
//        @Override
//        public void onUnityBannerUnloaded(String placementId) {
//            // When the banner’s no longer in use, remove it from the view hierarchy:
////            bannerView = null;
//        }
//
//        @Override
//        public void onUnityBannerShow(String placementId) {
//            // Called when the banner is first visible to the user.
//        }
//
//        @Override
//        public void onUnityBannerClick(String placementId) {
//            // Called when the banner is clicked.
//        }
//
//        @Override
//        public void onUnityBannerHide(String placementId) {
//            // Called when the banner is hidden from the user.
//        }
//
//        @Override
//        public void onUnityBannerError(String message) {
//            // Called when an error occurred, and the banner failed to load or show.
//            Log.e("Unity Banner======", "error" + message);
//            admogbanner();
//        }
//    }

    //===================================================FB Native Banner
    public void Fb_nativebanner() {

        final NativeBannerAd nativeBannerAd = new NativeBannerAd(activity,fb_nb);
        NativeAdListener nativeAdListener = new NativeAdListener() {
            @Override
            public void onMediaDownloaded(Ad ad) {
                // Native ad finished downloading all assets
                Log.e("FB nativebanner===", "Native ad finished downloading all assets.");
            }

            @Override
            public void onError(Ad ad, AdError adError) {
                // Native ad failed to load
                Log.e("FB nativebanner===", "Native ad failed to load: " + adError.getErrorMessage());
                admogbanner();
            }

            @Override
            public void onAdLoaded(Ad ad) {
                // Native ad is loaded and ready to be displayed
                Log.d("FB nativebanner===", "Native ad is loaded and ready to be displayed!");
                if (nativeBannerAd == null || nativeBannerAd != ad) {
                    return;
                }
                // Inflate Native Banner Ad into Container
                inflateAd(nativeBannerAd);
            }

            @Override
            public void onAdClicked(Ad ad) {
                // Native ad clicked
                Log.d("FB nativebanner===", "Native ad clicked!");
            }

            @Override
            public void onLoggingImpression(Ad ad) {
                // Native ad impression
                Log.d("FB nativebanner===", "Native ad impression logged!");
            }
        };
        // load the ad
        nativeBannerAd.loadAd(nativeBannerAd.buildLoadAdConfig().withAdListener(nativeAdListener).build());


    }

    private void inflateAd(NativeBannerAd nativeBannerAd) {
        // Unregister last ad
        nativeBannerAd.unregisterView();

        // Add the Ad view into the ad container.
        ViewGroup nativeAdLayout = view.findViewById(R.id.native_banner_ad_container);
        LayoutInflater inflater = LayoutInflater.from(activity);
        // Inflate the Ad view.  The layout referenced is the one you created in the last step.
        LinearLayout adView = (LinearLayout) inflater.inflate(R.layout.ads_fb_nativebanner, nativeAdLayout, false);
        nativeAdLayout.addView(adView);

        // Add the AdChoices icon
        RelativeLayout adChoicesContainer = adView.findViewById(R.id.ad_choices_container);
        AdOptionsView adOptionsView = new AdOptionsView(activity, nativeBannerAd, (NativeAdLayout) nativeAdLayout);
        adChoicesContainer.removeAllViews();
        adChoicesContainer.addView(adOptionsView, 0);

        // Create native UI using the ad metadata.
        TextView nativeAdTitle = adView.findViewById(R.id.native_ad_title);
        TextView nativeAdSocialContext = adView.findViewById(R.id.native_ad_social_context);
        TextView sponsoredLabel = adView.findViewById(R.id.native_ad_sponsored_label);
        MediaView nativeAdIconView = adView.findViewById(R.id.native_icon_view);
        Button nativeAdCallToAction = adView.findViewById(R.id.native_ad_call_to_action);

        // Set the Text.
        nativeAdCallToAction.setText(nativeBannerAd.getAdCallToAction());
        nativeAdCallToAction.setVisibility(
                nativeBannerAd.hasCallToAction() ? View.VISIBLE : View.INVISIBLE);
        nativeAdTitle.setText(nativeBannerAd.getAdvertiserName());
        nativeAdSocialContext.setText(nativeBannerAd.getAdSocialContext());
        sponsoredLabel.setText(nativeBannerAd.getSponsoredTranslation());

        // Register the Title and CTA button to listen for clicks.
        List<View> clickableViews = new ArrayList<>();
        clickableViews.add(nativeAdTitle);
        clickableViews.add(nativeAdCallToAction);
        nativeBannerAd.registerViewForInteraction(adView, nativeAdIconView, clickableViews);
    }
    //===================================================FB Native Banner Over

    private void admogbanner() {
//        adView = new AdView(Select_Activity.this);
//        adView.setAdSize(AdSize.BANNER);
//        adView.setAdUnitId("ca-app-pub-3940256099942544/6300978111");


        AdView adView = new AdView(activity);
        adView.setAdUnitId(g_bnr);
        adView.setAdSize(AdSize.BANNER);
        AdRequest adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);
        view.removeAllViews();
        view.addView(adView);
        adView.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                // Code to be executed when an ad finishes loading.
            }
            @Override
            public void onAdFailedToLoad(LoadAdError loadAdError) {
                super.onAdFailedToLoad(loadAdError);
                // Code to be executed when an ad request fails.
//              Toast.makeText(Creation_Activity.this, "errored code"+errorCode, Toast.LENGTH_SHORT).show();
                Log.e("Admob Banner ", "fail==========" + loadAdError);

            }
            @Override
            public void onAdOpened() {
                // Code to be executed when an ad opens an overlay that
                // covers the screen.
            }
            @Override
            public void onAdClicked() {
                // Code to be executed when the user clicks on an ad.
            }
            @Override
            public void onAdClosed() {
                // Code to be executed when the user is about to return
                // to the app after tapping on an ad.
            }
        });
    }

    public void banner_Admob_Fb(){
        AdView adView = new AdView(activity);
        adView.setAdUnitId(g_bnr);
        adView.setAdSize(AdSize.BANNER);
        AdRequest adRequest = new AdRequest.Builder().build();
        adView.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
            }
            @Override
            public void onAdFailedToLoad(LoadAdError loadAdError) {
                super.onAdFailedToLoad(loadAdError);
                Log.e("Admob Banner ", "fail==========" + loadAdError);
                final NativeBannerAd nativeBannerAd = new NativeBannerAd(activity,fb_nb);
                NativeAdListener nativeAdListener = new NativeAdListener() {
                    @Override
                    public void onMediaDownloaded(Ad ad) {
                        // Native ad finished downloading all assets
                        Log.e("FB nativebanner===", "Native ad finished downloading all assets.");
                    }

                    @Override
                    public void onError(Ad ad, AdError adError) {
                        // Native ad failed to load
                        Log.e("FB nativebanner===", "Native ad failed to load: " + adError.getErrorMessage());

                    }

                    @Override
                    public void onAdLoaded(Ad ad) {
                        // Native ad is loaded and ready to be displayed
                        Log.d("FB nativebanner===", "Native ad is loaded and ready to be displayed!");
                        if (nativeBannerAd == null || nativeBannerAd != ad) {
                            return;
                        }
                        // Inflate Native Banner Ad into Container
                        inflateAd(nativeBannerAd);
                    }

                    @Override
                    public void onAdClicked(Ad ad) {
                        // Native ad clicked
                        Log.d("FB nativebanner===", "Native ad clicked!");
                    }

                    @Override
                    public void onLoggingImpression(Ad ad) {
                        // Native ad impression
                        Log.d("FB nativebanner===", "Native ad impression logged!");
                    }
                };
                // load the ad
                nativeBannerAd.loadAd(nativeBannerAd.buildLoadAdConfig().withAdListener(nativeAdListener).build());


            }

            @Override
            public void onAdClosed() {

            }
        });
        adView.loadAd(adRequest);
        view.removeAllViews();
        view.addView(adView);

    }

    public void banner_Fb_Admob(){
        final NativeBannerAd nativeBannerAd = new NativeBannerAd(activity,fb_nb);
        NativeAdListener nativeAdListener = new NativeAdListener() {
            @Override
            public void onMediaDownloaded(Ad ad) {
                // Native ad finished downloading all assets
                Log.e("FB nativebanner===", "Native ad finished downloading all assets.");
            }

            @Override
            public void onError(Ad ad, AdError adError) {
                // Native ad failed to load
                Log.e("FB nativebanner===", "Native ad failed to load: " + adError.getErrorMessage());
                AdView adView = new AdView(activity);
                adView.setAdUnitId(g_bnr);
                adView.setAdSize(AdSize.BANNER);
                AdRequest adRequest = new AdRequest.Builder().build();
                adView.loadAd(adRequest);
                adView.setAdListener(new AdListener() {
                    @Override
                    public void onAdLoaded() {
                        // Code to be executed when an ad finishes loading.
                    }
                    @Override
                    public void onAdFailedToLoad(LoadAdError loadAdError) {
                        super.onAdFailedToLoad(loadAdError);
                        // Code to be executed when an ad request fails.
//              Toast.makeText(Creation_Activity.this, "errored code"+errorCode, Toast.LENGTH_SHORT).show();
                        Log.e("Admob Banner ", "fail==========" + loadAdError);

                    }
                    @Override
                    public void onAdOpened() {
                        // Code to be executed when an ad opens an overlay that
                        // covers the screen.
                    }
                    @Override
                    public void onAdClicked() {
                        // Code to be executed when the user clicks on an ad.
                    }
                    @Override
                    public void onAdClosed() {
                        // Code to be executed when the user is about to return
                        // to the app after tapping on an ad.
                    }
                });
                view.removeAllViews();
                view.addView(adView);
            }

            @Override
            public void onAdLoaded(Ad ad) {
                // Native ad is loaded and ready to be displayed
                Log.d("FB nativebanner===", "Native ad is loaded and ready to be displayed!");
                if (nativeBannerAd == null || nativeBannerAd != ad) {
                    return;
                }
                // Inflate Native Banner Ad into Container
                inflateAd(nativeBannerAd);
            }

            @Override
            public void onAdClicked(Ad ad) {
                // Native ad clicked
                Log.d("FB nativebanner===", "Native ad clicked!");
            }

            @Override
            public void onLoggingImpression(Ad ad) {
                // Native ad impression
                Log.d("FB nativebanner===", "Native ad impression logged!");
            }
        };
        // load the ad
        nativeBannerAd.loadAd(nativeBannerAd.buildLoadAdConfig().withAdListener(nativeAdListener).build());


    }

//    public void startupBanner(){
//        Log.e("startup called ","==========");
//        Banner banner = (Banner) view.findViewById(R.id.startAppBanner);
//        banner.showBanner();
//        banner.setVisibility(View.VISIBLE);
//    }
}
