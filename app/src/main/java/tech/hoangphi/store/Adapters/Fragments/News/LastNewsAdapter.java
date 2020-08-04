package tech.hoangphi.store.Adapters.Fragments.News;

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

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import tech.hoangphi.store.Activities.News.NewsActivity;
import tech.hoangphi.store.Models.News;
import tech.hoangphi.store.R;
import tech.hoangphi.store.Services.APIRetrofitClient;

public class LastNewsAdapter extends RecyclerView.Adapter<LastNewsAdapter.ViewHolder> {
    private Context context;
    private ArrayList<News> arrNews;

    public LastNewsAdapter(Context context, ArrayList<News> arrNews) {
        this.context = context;
        this.arrNews = arrNews;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.line_fragment_last_news, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        News news = arrNews.get(position);
        Picasso.with(context).load(news.getImages()).into(holder.imv_thumbnail_news);
        holder.tv_last_news_title.setText(news.getTitle());
        holder.tv_last_news_date.setText(parseDate(news.getCreatedAt()));
        holder.tv_last_news_view.setText( formatNumber("###.###",news.getView()) + " lượt xem");
    }

    public String parseDate(String time) {
        String inputPattern = "yyyy-MM-dd HH:mm:ss";
        String outputPattern = "HH:mm:ss dd/MM/yyyy";
        SimpleDateFormat inputFormat = new SimpleDateFormat(inputPattern);
        SimpleDateFormat outputFormat = new SimpleDateFormat(outputPattern);

        Date date = null;
        String str = null;

        try {
            date = inputFormat.parse(time);
            str = outputFormat.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return str;
    }

    public static String formatNumber(String pattern, double value) {
        DecimalFormat formatter = new DecimalFormat();
        String formattedNumber = formatter.format(value);
        return formattedNumber;
    }

    @Override
    public int getItemCount() {
        return arrNews.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imv_thumbnail_news;
        TextView tv_last_news_title, tv_last_news_date, tv_last_news_view;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imv_thumbnail_news = itemView.findViewById(R.id.imv_thumbnail_news);
            tv_last_news_title = itemView.findViewById(R.id.tv_last_news_title);
            tv_last_news_date = itemView.findViewById(R.id.tv_last_news_date);
            tv_last_news_view = itemView.findViewById(R.id.tv_last_news_view);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, NewsActivity.class);
                    intent.putExtra("news",arrNews.get(getPosition()));
                    context.startActivity(intent);
                    Call<News> callback = APIRetrofitClient.getInstance().getDataService().sendView(arrNews.get(getPosition()).getId());
                    callback.enqueue(new Callback<News>() {
                        @Override
                        public void onResponse(Call<News> call, Response<News> response) {

                        }

                        @Override
                        public void onFailure(Call<News> call, Throwable t) {

                        }
                    });
                }
            });
        }
    }
}
