package tech.hoangphi.store.Fragments.News;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import tech.hoangphi.store.Adapters.Fragments.News.LastNewsAdapter;
import tech.hoangphi.store.Adapters.Fragments.News.TopNewsAdapter;
import tech.hoangphi.store.Models.News;
import tech.hoangphi.store.R;
import tech.hoangphi.store.Services.APIRetrofitClient;

public class TopNewsFragment extends Fragment {
    private View view;
    private RecyclerView rcv_top_news;
    private TopNewsAdapter topNewsAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_top_news,container,false);
        rcv_top_news = view.findViewById(R.id.rcv_top_news);
        getData();
        return view;
    }

    private void getData() {
        Call<List<News>> callback = APIRetrofitClient.getInstance().getDataService().getTopNews();
        callback.enqueue(new Callback<List<News>>() {
            @Override
            public void onResponse(Call<List<News>> call, Response<List<News>> response) {
                ArrayList<News> arrNews = (ArrayList<News>) response.body();
                if (response.isSuccessful()){
                    if (arrNews.size() > 0){
                        topNewsAdapter = new TopNewsAdapter(getContext(),arrNews);
                        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
                        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                        rcv_top_news.setLayoutManager(layoutManager);
                        rcv_top_news.setAdapter(topNewsAdapter);
                    }
                }
            }

            @Override
            public void onFailure(Call<List<News>> call, Throwable t) {

            }
        });
    }
}
