package tech.hoangphi.store.Adapters.Fragments.Homes;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

import tech.hoangphi.store.Activities.ProductActivity;
import tech.hoangphi.store.Models.Product;
import tech.hoangphi.store.R;

public class HotProductAdapter extends RecyclerView.Adapter<HotProductAdapter.ViewHolder>{
    Context context;
    ArrayList<Product> arrProducts;

    public HotProductAdapter(Context context, ArrayList<Product> arrProducts) {
        this.context = context;
        this.arrProducts = arrProducts;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.line_fragment_hot_product, parent, false));
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if (arrProducts.size() > 0){
            Product product = arrProducts.get(position);
            Picasso.with(context).load(product.getImages()).into(holder.imv_hot_product);
            holder.tv_name_hot_product.setText(product.getName() + "\n" + product.getRom());
            if (product.getCount() == 0){
                holder.tv_price_hot_product.setText(String.valueOf(R.string.empty_product));
            }else {
                String str = NumberFormat.getCurrencyInstance(new Locale("vi", "VN")).format(product.getPrice());
                holder.tv_price_hot_product.setText(str);
            }
        }
    }

    @Override
    public int getItemCount() {
        return arrProducts.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imv_hot_product;
        TextView tv_name_hot_product, tv_price_hot_product;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imv_hot_product = itemView.findViewById(R.id.imv_hot_product);
            tv_name_hot_product = itemView.findViewById(R.id.tv_name_hot_product);
            tv_price_hot_product = itemView.findViewById(R.id.tv_price_hot_product);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, ProductActivity.class);
                    //intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent.putExtra("product", arrProducts.get(getPosition()));
                    context.startActivity(intent);
                }
            });
        }
    }
}
