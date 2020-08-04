package tech.hoangphi.store.Adapters.Fragments.Homes;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import tech.hoangphi.store.Activities.Homes.BannerActivity;
import tech.hoangphi.store.Models.Banner;
import tech.hoangphi.store.R;

public class BannerAdapter extends PagerAdapter {
    private Context context;
    private ArrayList<Banner> arrBanners;

    public BannerAdapter(Context context, ArrayList<Banner> arrBanners) {
        this.context = context;
        this.arrBanners = arrBanners;
    }

    @Override
    public int getCount() {
        return arrBanners.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, final int position) {
        View view = LayoutInflater.from(context).inflate(R.layout.line_fragment_banner, null);
        ImageView imv_banner = view.findViewById(R.id.imv_banner);
        Picasso.with(context).load(arrBanners.get(position).getImages()).into(imv_banner);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, BannerActivity.class);
                intent.putExtra("banner",arrBanners.get(position));
                context.startActivity(intent);
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
