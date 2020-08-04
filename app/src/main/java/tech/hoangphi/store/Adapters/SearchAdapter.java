package tech.hoangphi.store.Adapters;

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

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.ViewHolder> {
    private Context context;
    private ArrayList<Product> arrProducts;

    public SearchAdapter(Context context, ArrayList<Product> arrProducts) {
        this.context = context;
        this.arrProducts = arrProducts;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.line_fragment_search, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Product product = arrProducts.get(position);
        Picasso.with(context).load(product.getImages()).into(holder.imv_search);
        holder.tv_name_search.setText(product.getName());
        String str = NumberFormat.getCurrencyInstance(new Locale("vi", "VN")).format(product.getPrice());
        holder.tv_price_search.setText(str);
        holder.tv_description.setText(product.getRom() + "/" + product.getRam() + "/" + product.getColor());
    }

    @Override
    public int getItemCount() {
        return arrProducts.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imv_search;
        TextView tv_name_search, tv_price_search, tv_description;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imv_search = itemView.findViewById(R.id.imv_search);
            tv_name_search = itemView.findViewById(R.id.tv_name_search);
            tv_price_search = itemView.findViewById(R.id.tv_price_search);
            tv_description = itemView.findViewById(R.id.tv_description);

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
