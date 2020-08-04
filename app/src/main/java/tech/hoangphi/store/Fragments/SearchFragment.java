package tech.hoangphi.store.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import tech.hoangphi.store.Models.Product;
import tech.hoangphi.store.R;
import tech.hoangphi.store.Services.APIRetrofitClient;

public class SearchFragment extends Fragment {
    View view;
    Toolbar toolbar;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_search, container, false);
        toolbar = view.findViewById(R.id.toolbar_search);

        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        toolbar.setTitle("");
        setHasOptionsMenu(true);
        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.search_view, menu);
        MenuItem item = menu.findItem(R.id.menu_search);
        SearchView searchView = (SearchView) item.getActionView();
        searchView.onActionViewExpanded();
        searchView.setMaxWidth(Integer.MAX_VALUE);
        searchView.setQueryHint("Nhập tên sản phẩm");

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                //Toast.makeText(getActivity(), query, Toast.LENGTH_SHORT).show();


                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (!newText.isEmpty()){
                    //Toast.makeText(getActivity(), "onQueryTextSubmit", Toast.LENGTH_SHORT).show();
                    Call<List<Product>> callback = APIRetrofitClient.getInstance().getDataService().postSearch(newText);
                    callback.enqueue(new Callback<List<Product>>() {
                        @Override
                        public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                            ArrayList<Product> arrProducts = (ArrayList<Product>) response.body();
                            if (arrProducts.size() > 0) {
                                ResultSearchFragment fragment = new ResultSearchFragment();
                                Bundle bundle = new Bundle();
                                bundle.putSerializable("result", arrProducts);
                                fragment.setArguments(bundle);
                                (getActivity())
                                        .getSupportFragmentManager()
                                        .beginTransaction()
                                        .replace(R.id.container_search, fragment)
                                        .commit();
                            } else {
                                ResultSearchFragment fragment = new ResultSearchFragment();
                                Bundle bundle = new Bundle();
                                bundle.putSerializable("result", new ArrayList<>());
                                fragment.setArguments(bundle);
                                (getActivity())
                                        .getSupportFragmentManager()
                                        .beginTransaction()
                                        .replace(R.id.container_search, fragment)
                                        .commit();
                                Toast.makeText(getActivity(), "Không có sản phẩm phù hợp", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<List<Product>> call, Throwable t) {

                        }
                    });
                    return true;
                }
                else{
                    //Toast.makeText(getActivity(), "adadad", Toast.LENGTH_SHORT).show();
                    ResultSearchFragment fragment = new ResultSearchFragment();
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("result", new ArrayList<>());
                    fragment.setArguments(bundle);
                    (getActivity())
                            .getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.container_search, fragment)
                            .commit();
                    return true;
                }
            }
        });
        super.onCreateOptionsMenu(menu, inflater);
    }
}
