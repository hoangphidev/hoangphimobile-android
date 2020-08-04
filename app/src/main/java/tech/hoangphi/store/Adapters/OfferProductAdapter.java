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

public class OfferProductAdapter extends RecyclerView.Adapter<OfferProductAdapter.ViewHolder> {
    Context context;
    ArrayList<Product> arrProducts;

    public OfferProductAdapter(Context context, ArrayList<Product> arrProducts) {
        this.context = context;
        this.arrProducts = arrProducts;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.line_fragment_offer_product, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Product product = arrProducts.get(position);
        Picasso.with(context).load(product.getImages()).into(holder.imv_offer_product);
        holder.tv_name_offer_product.setText(product.getName() + "\n" + product.getRom());
        String str = NumberFormat.getCurrencyInstance(new Locale("vi", "VN")).format(product.getPrice());
        holder.tv_price_offer_product.setText(str);
    }

    @Override
    public int getItemCount() {
        return arrProducts.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imv_offer_product;
        TextView tv_name_offer_product, tv_price_offer_product;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imv_offer_product = itemView.findViewById(R.id.imv_offer_product);
            tv_name_offer_product = itemView.findViewById(R.id.tv_name_offer_product);
            tv_price_offer_product = itemView.findViewById(R.id.tv_price_offer_product);
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
