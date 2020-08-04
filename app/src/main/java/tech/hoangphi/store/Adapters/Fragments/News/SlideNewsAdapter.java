package tech.hoangphi.store.Adapters.Fragments.News;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import tech.hoangphi.store.Activities.News.NewsActivity;
import tech.hoangphi.store.Models.News;
import tech.hoangphi.store.R;
import tech.hoangphi.store.Services.APIRetrofitClient;

public class SlideNewsAdapter extends PagerAdapter {
    Context context;
    ArrayList<News> arrNews;

    public SlideNewsAdapter(Context context, ArrayList<News> arrNews) {
        this.context = context;
        this.arrNews = arrNews;
    }

    @Override
    public int getCount() {
        return arrNews.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, final int position) {
        View view = LayoutInflater.from(context).inflate(R.layout.line_fragment_slide, null);
        ImageView imv_slide = view.findViewById(R.id.imv_slide);
        TextView tv_title_slide = view.findViewById(R.id.tv_title_slide);
        tv_title_slide.setText(arrNews.get(position).getTitle());
        Picasso.with(context).load(arrNews.get(position).getImages()).into(imv_slide);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, NewsActivity.class);
                intent.putExtra("news",arrNews.get(position));
                context.startActivity(intent);
                Call<News> callback = APIRetrofitClient.getInstance().getDataService().sendView(arrNews.get(position).getId());
                callback.enqueue(new Callback<News>() {
                    @Override
                    public void onResponse(Call<News> call, Response<News> response) {

                    }

                    @Override
                    public void onFailure(Call<News> call, Throwable t) {

                    }
                });
            }
        });
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}
