package com.gdtidm.bacaonline.Artikel.Religion.ComReg;

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

public class AdapterReligion extends RecyclerView.Adapter<AdapterReligion.MyViewHolder> {

    Context context;
    List<ModelArtikel> list_religion;

    public AdapterReligion(Context context, List<ModelArtikel> list_religion) {
        this.context = context;
        this.list_religion = list_religion;
    }

    @NonNull
    @Override
    public AdapterReligion.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_artikel, parent, false);
        AdapterReligion.MyViewHolder holder = new AdapterReligion.MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterReligion.MyViewHolder holder, int position) {
        holder.tvjudulRel.setText(list_religion.get(position).getJudul());
        holder.tvPenulisRel.setText(list_religion.get(position).getPenulis());
        holder.tvTanggalRel.setText(list_religion.get(position).getTanggal());

        String gambarRel = "https://onportofol.000webhostapp.com/baca_online/artikel/foto_artikel/religion/" + list_religion.get(position).getGambar();
        if ("null".equals(String.valueOf(list_religion.get(position).getGambar()))) {
            holder.imgReligion.setImageResource(R.mipmap.ic_launcher);
        } else {
            Glide.with(context).load(gambarRel).into(holder.imgReligion);
        }

        holder.cd_detail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, DetailArtikel.class);
                intent.putExtra("id_artikel", list_religion.get(position).getIdArtikel());
                intent.putExtra("judul", list_religion.get(position).getJudul());
                intent.putExtra("penulis", list_religion.get(position).getPenulis());
                intent.putExtra("tanggal", list_religion.get(position).getTanggal());
                intent.putExtra("kategori", list_religion.get(position).getKategori());
                intent.putExtra("gambar", gambarRel);
                intent.putExtra("deskripsi", list_religion.get(position).getDeskripsi());
                Toast.makeText(context, "Selamat menikmati artikel yang tersedia", Toast.LENGTH_SHORT).show();
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list_religion.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView imgReligion;
        TextView tvjudulRel, tvPenulisRel, tvTanggalRel;
        CardView cd_detail;

        public MyViewHolder(View itemView) {
            super(itemView);
            imgReligion         =   itemView.findViewById(R.id.image_artikel);
            tvjudulRel          =   itemView.findViewById(R.id.txt_judul);
            tvPenulisRel        =   itemView.findViewById(R.id.txt_penulis);
            tvTanggalRel        =   itemView.findViewById(R.id.txt_tanggal);
            cd_detail           =   itemView.findViewById(R.id.cd_detail);
        }
    }
}
