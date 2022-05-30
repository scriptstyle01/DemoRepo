package demo.repo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import ottu.payment.toast.ui.OttoPaymentSdk;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        new  OttoPaymentSdk(this);
    }
}