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
import tech.hoangphi.store.Models.News;
import tech.hoangphi.store.R;
import tech.hoangphi.store.Services.APIRetrofitClient;

public class LastNewsFragment extends Fragment {
    private View view;
    private RecyclerView rcv_last_news;
    private LastNewsAdapter lastNewsAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_last_news,container,false);
        rcv_last_news = view.findViewById(R.id.rcv_last_news);
        getData();
        return view;
    }

    private void getData() {
        Call<List<News>> callback = APIRetrofitClient.getInstance().getDataService().getLastNews();
        callback.enqueue(new Callback<List<News>>() {
            @Override
            public void onResponse(Call<List<News>> call, Response<List<News>> response) {
                ArrayList<News> arrNews = (ArrayList<News>) response.body();
                if (response.isSuccessful()){
                    if (arrNews.size() > 0){
                        lastNewsAdapter = new LastNewsAdapter(getContext(),arrNews);
                        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
                        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                        rcv_last_news.setLayoutManager(layoutManager);
                        rcv_last_news.setAdapter(lastNewsAdapter);
                    }
                }
            }

            @Override
            public void onFailure(Call<List<News>> call, Throwable t) {

            }
        });
    }
}
