package tech.hoangphi.store.Activities.News;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import tech.hoangphi.store.Models.News;
import tech.hoangphi.store.R;

public class NewsActivity extends AppCompatActivity {
    News news;
    ImageView imv_bg;
    TextView tv_title, tv_body, toolbar_title_slide, tv_view_news, tv_date_news;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);
        getDataIntent();
        setToolbar();
        imv_bg = findViewById(R.id.imv_bg_slide);
        tv_title = findViewById(R.id.tv_title_slide);
        tv_body = findViewById(R.id.tv_body_slide);
        toolbar_title_slide = findViewById(R.id.toolbar_title_slide);
        tv_view_news = findViewById(R.id.tv_view_news);
        tv_date_news = findViewById(R.id.tv_date_news);

        tv_title.setText(news.getTitle());
        tv_body.setText(news.getBody());
        toolbar_title_slide.setText(news.getTitle());
        Picasso.with(NewsActivity.this).load(news.getImages()).into(imv_bg);
        tv_view_news.setText( formatNumber("###.###",news.getView()) + " lượt xem");
        tv_date_news.setText(parseDate(news.getCreatedAt()));
    }

    private void getDataIntent() {
        Intent intent = getIntent();
        if (intent.hasExtra("news")){
            if (intent != null){
                news = (News) intent.getSerializableExtra("news");
            }
        }
    }

    private void setToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar_slide);
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

    public String parseDate(String time) {
        String inputPattern = "yyyy-MM-dd HH:mm:ss";
        String outputPattern = "HH:mm:ss dd/MM/yyyy";
        SimpleDateFormat inputFormat = new SimpleDateFormat(inputPattern);
        SimpleDateFormat outputFormat = new SimpleDateFormat(outputPattern);

        Date date = null;
        String str = null;

        try {
            date = inputFormat.parse(time);
            str = outputFormat.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return str;
    }

    public static String formatNumber(String pattern, double value) {
        DecimalFormat formatter = new DecimalFormat();
        String formattedNumber = formatter.format(value);
        return formattedNumber;
    }
}
