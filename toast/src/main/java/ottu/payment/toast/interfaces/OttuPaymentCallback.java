package ottu.payment.toast.interfaces;

public interface OttuPaymentCallback {

    public void onSuccess(String callback);
    public void onFail(String callback);

}
