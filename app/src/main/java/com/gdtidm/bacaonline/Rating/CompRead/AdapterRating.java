package com.gdtidm.bacaonline.Rating.CompRead;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.gdtidm.bacaonline.R;

import java.util.List;

public class AdapterRating extends RecyclerView.Adapter<AdapterRating.MyViewHolder> {

    Context context;
    List<ModelRating> listRating;

    public AdapterRating(Context context, List<ModelRating> listRating) {
        this.context = context;
        this.listRating = listRating;
    }

    @NonNull
    @Override
    public AdapterRating.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_rating, parent, false);
        AdapterRating.MyViewHolder holder = new AdapterRating.MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterRating.MyViewHolder holder, int position) {
        holder.nama.setText(listRating.get(position).getNama());
        holder.deskripsi.setText(listRating.get(position).getDeskripsi());
        holder.rating.setText(listRating.get(position).getRating());
        holder.rating_bar.setRating(Float.parseFloat(listRating.get(position).getRating()));
    }

    @Override
    public int getItemCount() {
        return listRating.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView nama, deskripsi, rating;
        RatingBar rating_bar;

        public MyViewHolder(View itemView) {
            super(itemView);
            nama        =   itemView.findViewById(R.id.text_nama);
            deskripsi   =   itemView.findViewById(R.id.text_deskripsi);
            rating      =   itemView.findViewById(R.id.text_rating);
            rating_bar  =   itemView.findViewById(R.id.rating_bar);
        }
    }
}
