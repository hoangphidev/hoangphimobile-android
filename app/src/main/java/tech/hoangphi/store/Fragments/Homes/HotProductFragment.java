package tech.hoangphi.store.Fragments.Homes;

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
import tech.hoangphi.store.Adapters.Fragments.Homes.HotProductAdapter;
import tech.hoangphi.store.Models.Product;
import tech.hoangphi.store.R;
import tech.hoangphi.store.Services.APIRetrofitClient;

public class HotProductFragment extends Fragment {
    View view;
    HotProductAdapter hotProductAdapter;
    RecyclerView rcv_hot_product;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_hot_product,container,false);
        rcv_hot_product = view.findViewById(R.id.rcv_hot_product);
        getData();
        return view;
    }

    private void getData() {
        Call<List<Product>> callback = APIRetrofitClient.getInstance().getDataService().getHotProduct();
        callback.enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                ArrayList<Product> arrProducts = (ArrayList<Product>) response.body();
                if (arrProducts.size() > 0){
                    hotProductAdapter = new HotProductAdapter(getActivity(),arrProducts);
                    LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
                    layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
                    rcv_hot_product.setLayoutManager(layoutManager);
                    rcv_hot_product.setAdapter(hotProductAdapter);
                }
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {

            }
        });
    }
}
