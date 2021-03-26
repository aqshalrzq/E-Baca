package com.gdtidm.bacaonline.Artikel.Technology.ComTech;

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

public class AdapterTechnology extends RecyclerView.Adapter<AdapterTechnology.ViewHolder> {

    Context context;
    List<ModelArtikel> listTechh;

    public AdapterTechnology(Context context, List<ModelArtikel> listTechh) {
        this.context = context;
        this.listTechh = listTechh;
    }

    @NonNull
    @Override
    public AdapterTechnology.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_artikel, parent, false);
        AdapterTechnology.ViewHolder holder = new AdapterTechnology.ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterTechnology.ViewHolder holder, int position) {
        holder.judulTech.setText(listTechh.get(position).getJudul());
        holder.penulisTech.setText(listTechh.get(position).getPenulis());
        holder.tanggalTech.setText(listTechh.get(position).getTanggal());

        String gambarTech = "https://onportofol.000webhostapp.com/baca_online/artikel/foto_artikel/technology/" + listTechh.get(position).getGambar();
        if ("null".equals(String.valueOf(listTechh.get(position).getGambar()))) {
            holder.imageTech.setImageResource(R.mipmap.ic_launcher);
        } else {
            Glide.with(context).load(gambarTech).into(holder.imageTech);
        }

        holder.cd_detail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, DetailArtikel.class);
                intent.putExtra("id_artikel", listTechh.get(position).getIdArtikel());
                intent.putExtra("judul", listTechh.get(position).getJudul());
                intent.putExtra("penulis", listTechh.get(position).getPenulis());
                intent.putExtra("tanggal", listTechh.get(position).getTanggal());
                intent.putExtra("kategori", listTechh.get(position).getKategori());
                intent.putExtra("gambar", gambarTech);
                intent.putExtra("deskripsi", listTechh.get(position).getDeskripsi());
                Toast.makeText(context, "Selamat menikmati artikel yang tersedia", Toast.LENGTH_SHORT).show();
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listTechh.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imageTech;
        TextView judulTech, penulisTech, tanggalTech;
        CardView cd_detail;

        public ViewHolder (View itemView) {
            super(itemView);
            imageTech   =   itemView.findViewById(R.id.image_artikel);
            judulTech   =   itemView.findViewById(R.id.txt_judul);
            penulisTech =   itemView.findViewById(R.id.txt_penulis);
            tanggalTech =   itemView.findViewById(R.id.txt_tanggal);
            cd_detail   =   itemView.findViewById(R.id.cd_detail);
        }
    }
}
