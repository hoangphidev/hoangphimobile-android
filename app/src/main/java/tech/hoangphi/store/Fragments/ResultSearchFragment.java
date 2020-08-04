package tech.hoangphi.store.Fragments;

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

import tech.hoangphi.store.Adapters.SearchAdapter;
import tech.hoangphi.store.Models.Product;
import tech.hoangphi.store.R;

public class ResultSearchFragment extends Fragment {
    View view;
    //    ImageView imv_thumbnail_order;
//    TextView tv_product_name, tv_description_product, tv_product_price, tv_order_status, tv_customer_phone, tv_customer_name, tv_order_id, tv_order_date;
//    ResultSearch result;
    SearchAdapter searchAdapter;
    RecyclerView rv_search;
    ArrayList<Product> arrProducts;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_result_search, container, false);
        rv_search = view.findViewById(R.id.rc_search);
//        init(view);
        Bundle bundle = getArguments();
        arrProducts = (ArrayList<Product>) bundle.getSerializable("result");

        searchAdapter = new SearchAdapter(getActivity(), arrProducts);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rv_search.setLayoutManager(layoutManager);
        rv_search.setAdapter(searchAdapter);

        //setView();
        return view;
    }

//    private void setView() {
//        tv_order_id.setText(result.getOderId() + "");
//        tv_customer_name.setText(result.getName());
//        tv_customer_phone.setText(result.getPhone());
//        tv_order_date.setText(result.getDate());
//        if (result.getStatus() == 1){
//            tv_order_status.setText("Đã tiếp nhận đơn hàng");
//        }else if (result.getStatus() == 2){
//            tv_order_status.setText("Đơn hàng đang được giao");
//        }else if (result.getStatus() == 3){
//            tv_order_status.setText("Đã giao hàng");
//        }else if (result.getStatus() == 4){
//            tv_order_status.setText("Đơn hàng đã bị huỷ");
//        }
//        tv_product_name.setText(result.getProductName());
//        tv_description_product.setText(result.getDescription());
//        String str = NumberFormat.getCurrencyInstance(new Locale("vi", "VN")).format(result.getPrice());
//        tv_product_price.setText(str);
//        Picasso.with(getContext()).load(result.getImages()).into(imv_thumbnail_order);
//    }

//    private void init(View view) {
//        imv_thumbnail_order = view.findViewById(R.id.imv_thumbnail_order);
//        tv_product_name = view.findViewById(R.id.tv_product_name);
//        tv_description_product = view.findViewById(R.id.tv_description_product);
//        tv_product_price = view.findViewById(R.id.tv_product_price);
//        tv_order_status = view.findViewById(R.id.tv_order_status);
//        tv_customer_phone = view.findViewById(R.id.tv_customer_phone);
//        tv_customer_name = view.findViewById(R.id.tv_customer_name);
//        tv_order_id = view.findViewById(R.id.tv_order_id);
//        tv_order_date = view.findViewById(R.id.tv_order_date);
//    }
}
