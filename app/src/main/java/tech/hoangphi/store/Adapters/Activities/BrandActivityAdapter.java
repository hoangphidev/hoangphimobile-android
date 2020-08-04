package tech.hoangphi.store.Adapters.Activities;

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


public class BrandActivityAdapter extends RecyclerView.Adapter<BrandActivityAdapter.ViewHolder> {
    Context context;
    ArrayList<Product> arrProduct;

    public BrandActivityAdapter(Context context, ArrayList<Product> arrProduct) {
        this.context = context;
        this.arrProduct = arrProduct;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.line_activity_brand,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Product product = arrProduct.get(position);
        Picasso.with(context).load(product.getImages()).into(holder.imv_brand_product);
        holder.tv_name_brand_product.setText(product.getName() + "\n" + product.getRom());
        String str = NumberFormat.getCurrencyInstance(new Locale("vi", "VN")).format(product.getPrice());
        if (product.getCount() == 0){
            holder.tv_price_brand_product.setText("Tạm hết hàng");
        }else {
            holder.tv_price_brand_product.setText(str);
        }
    }

    @Override
    public int getItemCount() {
        return arrProduct.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView imv_brand_product;
        TextView tv_name_brand_product, tv_price_brand_product;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imv_brand_product = itemView.findViewById(R.id.imv_brand_product);
            tv_name_brand_product = itemView.findViewById(R.id.tv_name_brand_product);
            tv_price_brand_product = itemView.findViewById(R.id.tv_price_brand_product);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, ProductActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent.putExtra("product", arrProduct.get(getPosition()));
                    context.startActivity(intent);
                }
            });
        }
    }
}
