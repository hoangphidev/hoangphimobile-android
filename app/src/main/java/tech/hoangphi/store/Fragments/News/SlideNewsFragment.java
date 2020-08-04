package tech.hoangphi.store.Fragments.News;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import java.util.ArrayList;
import java.util.List;

import me.relex.circleindicator.CircleIndicator;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import tech.hoangphi.store.Adapters.Fragments.News.SlideNewsAdapter;
import tech.hoangphi.store.Models.News;
import tech.hoangphi.store.R;
import tech.hoangphi.store.Services.APIRetrofitClient;

public class SlideNewsFragment extends Fragment {
    private View view;
    private SlideNewsAdapter slideNewsAdapter;
    private ViewPager viewpager_slide;
    private CircleIndicator indicator_slide;
    private Runnable runnable;
    private Handler handler;
    private int currentItem;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_slide, container, false);
        viewpager_slide = view.findViewById(R.id.viewpager_slide);
        indicator_slide = view.findViewById(R.id.indicator_slide);
        getData();
        return view;
    }

    private void getData() {
        Call<List<News>> callback = APIRetrofitClient.getInstance().getDataService().getOfferNews();
        callback.enqueue(new Callback<List<News>>() {
            @Override
            public void onResponse(Call<List<News>> call, Response<List<News>> response) {
                ArrayList<News> arrNews = (ArrayList<News>) response.body();
                if (response.isSuccessful()){
                    if (arrNews.size() > 0){
                        slideNewsAdapter = new SlideNewsAdapter(getActivity(),arrNews);
                        viewpager_slide.setAdapter(slideNewsAdapter);
                        indicator_slide.setViewPager(viewpager_slide);
                        handler = new Handler();
                        runnable = new Runnable() {
                            @Override
                            public void run() {
                                currentItem = viewpager_slide.getCurrentItem();
                                currentItem++;
                                if (currentItem >= viewpager_slide.getAdapter().getCount()) currentItem = 0;
                                viewpager_slide.setCurrentItem(currentItem,true);
                                handler.postDelayed(runnable, 7000);
                            }
                        };
                        handler.postDelayed(runnable,7000);
                    }
                }else{
                    Log.d("aaaa", "onResponse: Loi");
                }
            }

            @Override
            public void onFailure(Call<List<News>> call, Throwable t) {

            }
        });
    }
}
