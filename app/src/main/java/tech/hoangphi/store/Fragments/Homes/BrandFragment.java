package tech.hoangphi.store.Fragments.Homes;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import tech.hoangphi.store.Adapters.Fragments.Homes.BrandAdapter;
import tech.hoangphi.store.Models.Brand;
import tech.hoangphi.store.R;
import tech.hoangphi.store.Services.APIRetrofitClient;

public class BrandFragment extends Fragment {
    View view;
    RecyclerView rcv_brand;
    BrandAdapter brandAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_brand, container,false);
        rcv_brand = view.findViewById(R.id.rcv_brand);
        getData();
        return view;
    }

    private void getData() {
        Call<List<Brand>> callback = APIRetrofitClient.getInstance().getDataService().getDataBrand();
        callback.enqueue(new Callback<List<Brand>>() {
            @Override
            public void onResponse(Call<List<Brand>> call, Response<List<Brand>> response) {
                ArrayList<Brand> arrBrand = (ArrayList<Brand>) response.body();
                if (arrBrand.size() > 0){
                    brandAdapter = new BrandAdapter(getActivity(),arrBrand);
                    GridLayoutManager layoutManager = new GridLayoutManager(getActivity(),2);
                    layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
                    rcv_brand.setLayoutManager(layoutManager);
                    rcv_brand.setAdapter(brandAdapter);
                }
            }

            @Override
            public void onFailure(Call<List<Brand>> call, Throwable t) {

            }
        });
    }
}
