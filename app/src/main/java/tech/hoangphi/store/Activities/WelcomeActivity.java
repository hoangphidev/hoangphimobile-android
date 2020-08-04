package tech.hoangphi.store.Activities;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import tech.hoangphi.store.Models.Brand;
import tech.hoangphi.store.R;
import tech.hoangphi.store.Services.APIRetrofitClient;

public class WelcomeActivity extends AppCompatActivity {
    private ImageView imv_welcome;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        imv_welcome = findViewById(R.id.imv_welcome);
        Animation animation = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.welcome_animation);
        imv_welcome.setAnimation(animation);
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                if(!isConnected(WelcomeActivity.this)){
                    buildDialog(WelcomeActivity.this).show();
                }else{
                    Call<List<Brand>> callback = APIRetrofitClient.getInstance().getDataService().getDataBrand();
                    callback.enqueue(new Callback<List<Brand>>() {
                        @Override
                        public void onResponse(Call<List<Brand>> call, Response<List<Brand>> response) {
                            if (response.isSuccessful()){
                                startActivity(new Intent(WelcomeActivity.this,MainActivity.class));
                                finish();
                            }
                        }

                        @Override
                        public void onFailure(Call<List<Brand>> call, Throwable t) {
                            AlertDialog.Builder builder = new AlertDialog.Builder(WelcomeActivity.this);
                            builder.setTitle(getResources().getString(R.string.no_server));
                            builder.setMessage(getResources().getString(R.string.no_server_message));
                            builder.setCancelable(false);

                            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {

                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    System.exit(0);
                                }
                            });

                            AlertDialog alertDialog = builder.create();
                            alertDialog.show();
                        }
                    });
                }
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }

    public boolean isConnected(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netinfo = cm.getActiveNetworkInfo();

        if (netinfo != null && netinfo.isConnectedOrConnecting()) {
            android.net.NetworkInfo wifi = cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
            android.net.NetworkInfo mobile = cm.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);

            if((mobile != null && mobile.isConnectedOrConnecting()) || (wifi != null && wifi.isConnectedOrConnecting())){
                return true;
            }
            else{
                return false;
            }
        } else{
            return false;
        }
    }

    public AlertDialog.Builder buildDialog(Context c) {
        AlertDialog.Builder builder = new AlertDialog.Builder(c);
        builder.setTitle(getResources().getString(R.string.no_network));
        builder.setMessage(getResources().getString(R.string.no_network_message));
        builder.setCancelable(false);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });
        return builder;
    }
}
