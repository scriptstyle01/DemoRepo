package ottu.payment.toast;

import android.content.Context;
import android.widget.Toast;

public class ShowToast {

    public void showToast(Context context,String message){
        Toast.makeText(context, message+"1", Toast.LENGTH_SHORT).show();
    }
}
