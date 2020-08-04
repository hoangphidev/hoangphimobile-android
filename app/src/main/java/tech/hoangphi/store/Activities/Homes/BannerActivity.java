package tech.hoangphi.store.Activities.Homes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import tech.hoangphi.store.Models.Banner;
import tech.hoangphi.store.R;

public class BannerActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private ImageView imv_bg_banner;
    private TextView tv_title_banner, tv_description_banner;
    private Banner banner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_banner);

        setToolbar();
        init();
        getDataIntent();
    }

    private void setToolbar() {
        toolbar = findViewById(R.id.toolbar_banner);
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
        imv_bg_banner = findViewById(R.id.imv_bg_banner);
        tv_title_banner = findViewById(R.id.tv_title_banner);
        tv_description_banner = findViewById(R.id.tv_description_banner);
    }

    private void getDataIntent() {
        Intent intent = getIntent();
        if (intent != null){
            if (intent.hasExtra("banner")){
                banner = (Banner) intent.getSerializableExtra("banner");
                Picasso.with(getApplicationContext()).load(banner.getImages()).into(imv_bg_banner);
                tv_title_banner.setText(banner.getTitle());
                tv_description_banner.setText(banner.getDescription());
            }
        }
    }
}
