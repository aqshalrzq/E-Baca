package com.gdtidm.bacaonline.Artikel.Health.ComHealth;

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

public class AdapterHealth extends RecyclerView.Adapter<AdapterHealth.MyViewHolder> {

    Context context;
    List<ModelArtikel> listHealth;

    public AdapterHealth(Context context, List<ModelArtikel> listHealth) {
        this.context = context;
        this.listHealth = listHealth;
    }

    @NonNull
    @Override
    public AdapterHealth.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_artikel, parent, false);
        AdapterHealth.MyViewHolder holder = new AdapterHealth.MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterHealth.MyViewHolder holder, int position) {
        holder.tv_judulhealth.setText(listHealth.get(position).getJudul());
        holder.tv_tanggalhealth.setText(listHealth.get(position).getTanggal());
        holder.tv_penulishealth.setText(listHealth.get(position).getPenulis());

        String gambarHealth = "https://onportofol.000webhostapp.com/baca_online/artikel/foto_artikel/health/" + listHealth.get(position).getGambar();
        if ("null".equals(String.valueOf(listHealth.get(position).getGambar()))) {
            holder.imageHealth.setImageResource(R.mipmap.ic_launcher);
        } else {
            Glide.with(context).load(gambarHealth).into(holder.imageHealth);
        }

        holder.cd_detail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, DetailArtikel.class);
                intent.putExtra("id_artikel", listHealth.get(position).getIdArtikel());
                intent.putExtra("judul", listHealth.get(position).getJudul());
                intent.putExtra("penulis", listHealth.get(position).getPenulis());
                intent.putExtra("tanggal", listHealth.get(position).getTanggal());
                intent.putExtra("kategori", listHealth.get(position).getKategori());
                intent.putExtra("gambar", gambarHealth);
                intent.putExtra("deskripsi", listHealth.get(position).getDeskripsi());
                Toast.makeText(context, "Selamat menikmati artikel yang tersedia", Toast.LENGTH_SHORT).show();
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listHealth.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView imageHealth;
        TextView tv_judulhealth, tv_penulishealth, tv_tanggalhealth;
        CardView cd_detail;

        public MyViewHolder(View itemView) {
            super(itemView);
            imageHealth         =   itemView.findViewById(R.id.image_artikel);
            tv_judulhealth      =   itemView.findViewById(R.id.txt_judul);
            tv_penulishealth    =   itemView.findViewById(R.id.txt_penulis);
            tv_tanggalhealth    =   itemView.findViewById(R.id.txt_tanggal);
            cd_detail           =   itemView.findViewById(R.id.cd_detail);
        }
    }
}
