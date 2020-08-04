package tech.hoangphi.store.Activities.Homes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import tech.hoangphi.store.Adapters.Activities.BrandActivityAdapter;
import tech.hoangphi.store.Models.Product;
import tech.hoangphi.store.R;
import tech.hoangphi.store.Services.APIRetrofitClient;

public class BrandActivity extends AppCompatActivity {
    int brand_id;
    TextView toolbar_title_brand;
    RecyclerView rcv_product_by_brand;
    ArrayList<Product> arrProduct;
    BrandActivityAdapter brandActivityAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_brand);
        rcv_product_by_brand = findViewById(R.id.rcv_product_by_brand);
        init();
        getDataIntent();
        getDataProductByBrandID(brand_id);
    }

    private void init() {
        toolbar_title_brand = findViewById(R.id.toolbar_title_brand);
        Toolbar toolbar_brand = findViewById(R.id.toolbar_brand);
        setSupportActionBar(toolbar_brand);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar_brand.setNavigationIcon(getResources().getDrawable(R.drawable.ic_back));
        toolbar_brand.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void getDataIntent() {
        Intent intent = getIntent();
        if (intent != null) {
            if (intent.hasExtra("brand_id")) {
                brand_id = intent.getIntExtra("brand_id", 0);
                String title = intent.getStringExtra("brand_title");
                toolbar_title_brand.setText(getResources().getString(R.string.text_view_phone) + " " + title);
            }
        }
    }

    private void getDataProductByBrandID(int brand_id) {
        Call<List<Product>> callback = APIRetrofitClient
                .getInstance()
                .getDataService()
                .getDataProductByBrandID(brand_id);
        callback.enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                arrProduct = (ArrayList<Product>) response.body();
                brandActivityAdapter = new BrandActivityAdapter(getApplicationContext(), arrProduct);
                rcv_product_by_brand.setAdapter(brandActivityAdapter);
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {

            }
        });
    }
}
