package tech.hoangphi.store.Adapters.Fragments.Homes;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import tech.hoangphi.store.Activities.Homes.BrandActivity;
import tech.hoangphi.store.Models.Brand;
import tech.hoangphi.store.R;

public class BrandAdapter extends RecyclerView.Adapter<BrandAdapter.ViewHolder> {
    private Context context;
    private ArrayList<Brand> arrBrands;

    public BrandAdapter(Context context, ArrayList<Brand> arrBrands) {
        this.context = context;
        this.arrBrands = arrBrands;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.line_fragment_brand, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Brand brand = arrBrands.get(position);
        Picasso.with(context).load(brand.getImages()).into(holder.imv_brand);
    }

    @Override
    public int getItemCount() {
        return arrBrands.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imv_brand;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imv_brand = itemView.findViewById(R.id.imv_brand);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, BrandActivity.class);
                    intent.putExtra("brand_id", arrBrands.get(getPosition()).getId());
                    intent.putExtra("brand_title", arrBrands.get(getPosition()).getName());
                    context.startActivity(intent);
                }
            });
        }
    }
}
