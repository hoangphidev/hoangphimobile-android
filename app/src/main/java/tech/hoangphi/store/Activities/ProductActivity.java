package tech.hoangphi.store.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.text.NumberFormat;
import java.util.Locale;

import tech.hoangphi.store.Models.Product;
import tech.hoangphi.store.R;

public class ProductActivity extends AppCompatActivity {
    int product_id;
    ImageView imv_product;
    TextView tv_name_product, tv_price_product, tv_description, toolbar_title_product;
    TextView tv_screen, tv_os, tv_back_camera, tv_front_camera, tv_cpu, tv_ram, tv_rom, tv_battery, tv_color;
    Product product;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);

        getDataIntent();
        setToolbar();
        init();
        setContentValue();

        if (product.getCount() == 0){
            findViewById(R.id.btn_order).setVisibility(View.INVISIBLE);
        }else {
            findViewById(R.id.btn_order).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getApplicationContext(), OrderActivity.class);
                    intent.putExtra("product", product);
                    startActivity(intent);
                }
            });
        }
    }

    private void getDataIntent() {
        Intent intent = getIntent();
        if (intent.hasExtra("product")) {
            if (intent != null) {
                product = (Product) intent.getSerializableExtra("product");
                product_id = product.getId();
            }
        }
    }

    private void setToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar_product);
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

    private void init() {
        toolbar_title_product = findViewById(R.id.toolbar_title_product);
        imv_product = findViewById(R.id.imv_product);
        tv_name_product = findViewById(R.id.tv_name_product);
        tv_price_product = findViewById(R.id.tv_price_product);
        tv_description = findViewById(R.id.tv_description);

        tv_screen = findViewById(R.id.tv_screen);
        tv_os = findViewById(R.id.tv_os);
        tv_cpu = findViewById(R.id.tv_cpu);
        tv_back_camera = findViewById(R.id.tv_back_camera);
        tv_front_camera = findViewById(R.id.tv_front_camera);
        tv_ram = findViewById(R.id.tv_ram);
        tv_rom = findViewById(R.id.tv_rom);
        tv_battery = findViewById(R.id.tv_battery);
        tv_color = findViewById(R.id.tv_color);
    }

    private void setContentValue() {
        toolbar_title_product.setText(product.getName() + " "
                + product.getRom() + "/"
                + product.getRam() + "/"
                + product.getColor());
        tv_name_product.setText(product.getName() + " " + product.getRom());
        String str = NumberFormat.getCurrencyInstance(new Locale("vi","VN")).format(product.getPrice());
        if (product.getCount() == 0){
            tv_price_product.setText("Tạm hết hàng");
            findViewById(R.id.btn_order).setVisibility(View.INVISIBLE);
        }else {
            tv_price_product.setText(str);
        }
        Picasso.with(getApplicationContext()).load(product.getImages()).into(imv_product);
        tv_description.setText(product.getDescription());

        // config
        tv_screen.setText(product.getScreen());
        tv_os.setText(product.getOs());
        tv_back_camera.setText(product.getBackCamera());
        tv_front_camera.setText(product.getFrontCamera());
        tv_cpu.setText(product.getCpu());
        tv_ram.setText(product.getRam());
        tv_rom.setText(product.getRom());
        tv_battery.setText(product.getBattery());
        tv_color.setText(product.getColor());
    }

}
