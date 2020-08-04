package tech.hoangphi.store.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bestsoft32.tt_fancy_gif_dialog_lib.TTFancyGifDialog;
import com.bestsoft32.tt_fancy_gif_dialog_lib.TTFancyGifDialogListener;
import com.google.android.material.textfield.TextInputLayout;
import com.squareup.picasso.Picasso;

import java.text.NumberFormat;
import java.util.Locale;
import java.util.Random;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import tech.hoangphi.store.Models.OrderResponse;
import tech.hoangphi.store.Models.Product;
import tech.hoangphi.store.R;
import tech.hoangphi.store.Services.APIRetrofitClient;

public class OrderActivity extends AppCompatActivity {
    private Product product;
    private ImageView imv_product;
    private TextView tv_name_product, tv_description, tv_price_product, tv_total_pay;
    TextInputLayout text_input_name, text_input_phone, text_input_email, text_input_address, text_input_note;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        setToolbar();
        getDataIntent();
        init();
        setContentValues();

        findViewById(R.id.btn_order_confirm).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                orderDetail();
            }
        });
    }



    private void setToolbar() {
        TextView title = findViewById(R.id.toolbar_title_order);
        title.setText(getResources().getString(R.string.toolbar_order_activity));
        Toolbar toolbar = findViewById(R.id.toolbar_order);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_back));
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void getDataIntent() {
        Intent intent = getIntent();
        if (intent.hasExtra("product")) {
            if (intent != null) {
                product = (Product) intent.getSerializableExtra("product");
            }
        }
    }
    private void init() {
        imv_product = findViewById(R.id.imv_product);
        tv_name_product = findViewById(R.id.tv_name_product);
        tv_description = findViewById(R.id.tv_description);
        tv_price_product = findViewById(R.id.tv_price_product);
        tv_total_pay = findViewById(R.id.tv_total_pay);

        text_input_name = findViewById(R.id.text_input_name);
        text_input_phone = findViewById(R.id.text_input_phone);
        //text_input_email = findViewById(R.id.text_input_email);
        text_input_address = findViewById(R.id.text_input_address);
        text_input_note = findViewById(R.id.text_input_note);
    }
    private void setContentValues() {
        Picasso.with(getApplicationContext()).load(product.getImages()).into(imv_product);
        tv_name_product.setText(product.getName() + " " + product.getRom());
        tv_description.setText(product.getRom() + "/" + product.getRam() + "/" + product.getColor());
        String str = NumberFormat.getCurrencyInstance(new Locale("vi", "VN")).format(product.getPrice());
        tv_price_product.setText(str);
        tv_total_pay.setText(getResources().getString(R.string.text_view_total_pay) + " " +str);
    }
    private void orderDetail() {
        String name = text_input_name.getEditText().getText().toString().trim();
        String phone = text_input_phone.getEditText().getText().toString().trim();
        //String email = text_input_email.getEditText().getText().toString().trim();
        String address = text_input_address.getEditText().getText().toString().trim();
        String notes = text_input_note.getEditText().getText().toString().trim();

        int product_id = product.getId();
        Random random = new Random();
        final int order_id = 10000000 + random.nextInt(9999999);

        // Validate input
        if (name.isEmpty()) {
            text_input_name.setError(getResources().getString(R.string.require_name));
            text_input_name.requestFocus();
            return;
        }
        if (phone.isEmpty()) {
            text_input_phone.setError(getResources().getString(R.string.require_phone));
            text_input_phone.requestFocus();
            return;
        }
        if (phone.length() < 10) {
            text_input_phone.setError(getResources().getString(R.string.validate_phone));
            text_input_phone.requestFocus();
            return;
        }
//        if (!email.isEmpty()) {
//            if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
//                text_input_email.setError(getResources().getString(R.string.validate_email));
//                text_input_email.requestFocus();
//                return;
//            }
//        }
        if (address.isEmpty()) {
            text_input_address.setError(getResources().getString(R.string.require_address));
            text_input_address.requestFocus();
            return;
        }
        if (address.length() < 6) {
            text_input_address.setError(getResources().getString(R.string.validate_address));
            text_input_address.requestFocus();
            return;
        }
        // Push order up to server
        Call<OrderResponse> callback = APIRetrofitClient
                .getInstance()
                .getDataService()
                .postOrder(order_id, name, phone, address, product_id, notes);
        callback.enqueue(new Callback<OrderResponse>() {
            @Override
            public void onResponse(Call<OrderResponse> call, Response<OrderResponse> response) {
                if (response.isSuccessful()){
                    new TTFancyGifDialog.Builder(OrderActivity.this)
                            .setTitle(getResources().getString(R.string.order_success))
                            .setMessage(getResources().getString(R.string.order_id) +" "+
                                    + order_id + ".\n\n"
                                    + getResources().getString(R.string.thank_you) + "\n\n"
                                    + getResources().getString(R.string.contact_back))
                            .setPositiveBtnText(getResources().getString(R.string.btn_back_home))
                            .setPositiveBtnBackground("#008577")
                            .setGifResource(R.drawable.ic_success)      //pass your gif, png or jpg
                            .OnPositiveClicked(new TTFancyGifDialogListener() {
                                @Override
                                public void OnClick() {
                                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                                    startActivity(intent);
                                    finish();
                                }
                            })
                            .build();
                }else {
                    Log.d("AAA", response.message());
                }
            }

            @Override
            public void onFailure(Call<OrderResponse> call, Throwable t) {
                Log.d("AAA", "Error: " + t.getMessage());
            }
        });
    }
}
