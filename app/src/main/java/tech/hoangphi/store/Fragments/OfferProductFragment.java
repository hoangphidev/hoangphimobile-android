package tech.hoangphi.store.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import tech.hoangphi.store.Adapters.OfferProductAdapter;
import tech.hoangphi.store.Models.Product;
import tech.hoangphi.store.R;
import tech.hoangphi.store.Services.APIRetrofitClient;

public class OfferProductFragment extends Fragment {
    View view;
    RecyclerView rcv_offer_product;
    OfferProductAdapter offerProductAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_offer_product, container, false);

        rcv_offer_product = view.findViewById(R.id.rcv_offer_product);
        getDataOfferProduct();
        return view;
    }

    private void getDataOfferProduct() {
        Call<List<Product>> callback = APIRetrofitClient.getInstance().getDataService().getOfferProduct();
        callback.enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                ArrayList<Product> arrProducts = (ArrayList<Product>) response.body();
                offerProductAdapter = new OfferProductAdapter(getContext(), arrProducts);
                rcv_offer_product.setAdapter(offerProductAdapter);
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {

            }
        });
    }
}
