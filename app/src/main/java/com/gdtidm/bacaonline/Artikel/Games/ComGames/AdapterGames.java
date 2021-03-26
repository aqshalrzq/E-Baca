package com.gdtidm.bacaonline.Artikel.Games.ComGames;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.gdtidm.bacaonline.Artikel.DetailArtikel;
import com.gdtidm.bacaonline.Artikel.ModelArtikel;
import com.gdtidm.bacaonline.R;

import java.util.List;

public class AdapterGames extends RecyclerView.Adapter<AdapterGames.MyViewHolder> {

    Context context;
    List<ModelArtikel> listGamess;

    public AdapterGames(Context context, List<ModelArtikel> listGamess) {
        this.context = context;
        this.listGamess = listGamess;
    }

    @NonNull
    @Override
    public AdapterGames.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_artikel, parent, false);
        AdapterGames.MyViewHolder holder = new AdapterGames.MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterGames.MyViewHolder holder, int position) {
        holder.tv_judulgames.setText(listGamess.get(position).getJudul());
        holder.tv_tanggalgames.setText(listGamess.get(position).getTanggal());
        holder.tv_penulisgames.setText(listGamess.get(position).getPenulis());

        String gambarGames = "https://onportofol.000webhostapp.com/baca_online/artikel/foto_artikel/games/" + listGamess.get(position).getGambar();
        if ("null".equals(String.valueOf(listGamess.get(position).getGambar()))) {
            holder.imageGames.setImageResource(R.mipmap.ic_launcher);
        } else {
            Glide.with(context).load(gambarGames).into(holder.imageGames);
        }

        holder.cd_detail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, DetailArtikel.class);
                intent.putExtra("id_artikel", listGamess.get(position).getIdArtikel());
                intent.putExtra("judul", listGamess.get(position).getJudul());
                intent.putExtra("penulis", listGamess.get(position).getPenulis());
                intent.putExtra("tanggal", listGamess.get(position).getTanggal());
                intent.putExtra("kategori", listGamess.get(position).getKategori());
                intent.putExtra("gambar", gambarGames);
                intent.putExtra("deskripsi", listGamess.get(position).getDeskripsi());
                Toast.makeText(context, "Selamat menikmati artikel yang tersedia", Toast.LENGTH_SHORT).show();
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listGamess.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView imageGames;
        TextView tv_judulgames, tv_penulisgames, tv_tanggalgames;
        CardView cd_detail;

        public MyViewHolder(View itemView) {
            super(itemView);
            imageGames      =   itemView.findViewById(R.id.image_artikel);
            tv_judulgames   =   itemView.findViewById(R.id.txt_judul);
            tv_penulisgames =   itemView.findViewById(R.id.txt_penulis);
            tv_tanggalgames =   itemView.findViewById(R.id.txt_tanggal);
            cd_detail       =   itemView.findViewById(R.id.cd_detail);
        }
    }

}
