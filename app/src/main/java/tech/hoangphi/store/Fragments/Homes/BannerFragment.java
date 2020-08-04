package tech.hoangphi.store.Fragments.Homes;

import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import java.util.ArrayList;
import java.util.List;

import me.relex.circleindicator.CircleIndicator;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import tech.hoangphi.store.Adapters.Fragments.Homes.BannerAdapter;
import tech.hoangphi.store.Models.Banner;
import tech.hoangphi.store.R;
import tech.hoangphi.store.Services.APIRetrofitClient;

public class BannerFragment extends Fragment {
    private View view;
    private BannerAdapter bannerAdapter;
    private ViewPager viewpager_banner;
    private CircleIndicator indicator_banner;
    private Runnable runnable;
    private Handler handler;
    private int currentItem;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_banner, container, false);
        viewpager_banner = view.findViewById(R.id.viewpager_banner);
        indicator_banner = view.findViewById(R.id.indicator_banner);
        getData();
        return view;
    }

    private void getData() {
        Call<List<Banner>> callback = APIRetrofitClient.getInstance().getDataService().getDataBanner();
        callback.enqueue(new Callback<List<Banner>>() {
            @Override
            public void onResponse(Call<List<Banner>> call, Response<List<Banner>> response) {
                ArrayList<Banner> arrBanners = (ArrayList<Banner>) response.body();
                if (arrBanners.size() >0){
                    bannerAdapter = new BannerAdapter(getActivity(),arrBanners);
                    viewpager_banner.setAdapter(bannerAdapter);
                    indicator_banner.setViewPager(viewpager_banner);
                    handler = new Handler();
                    runnable = new Runnable() {
                        @Override
                        public void run() {
                            currentItem = viewpager_banner.getCurrentItem();
                            currentItem++;
                            if (currentItem >= viewpager_banner.getAdapter().getCount()) currentItem = 0;
                            viewpager_banner.setCurrentItem(currentItem,true);
                            handler.postDelayed(runnable, 5000);
                        }
                    };
                    handler.postDelayed(runnable,5000);
                }
            }

            @Override
            public void onFailure(Call<List<Banner>> call, Throwable t) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
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
