package com.gdtidm.bacaonline.Artikel.Kinship.ComKinship;

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

public class AdapterKinship extends RecyclerView.Adapter<AdapterKinship.MyViewHolder> {

    Context context;
    List<ModelArtikel> listKinshipp;

    public AdapterKinship(Context context, List<ModelArtikel> listKinshipp) {
        this.context = context;
        this.listKinshipp = listKinshipp;
    }

    @NonNull
    @Override
    public AdapterKinship.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_artikel, parent, false);
        AdapterKinship.MyViewHolder holder = new AdapterKinship.MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterKinship.MyViewHolder holder, int position) {
        holder.tv_judulkinship.setText(listKinshipp.get(position).getJudul());
        holder.tv_tanggalkinship.setText(listKinshipp.get(position).getTanggal());
        holder.tv_penuliskinship.setText(listKinshipp.get(position).getPenulis());

        String gambarKinship = "https://onportofol.000webhostapp.com/baca_online/artikel/foto_artikel/kinship/" + listKinshipp.get(position).getGambar();
        if ("null".equals(String.valueOf(listKinshipp.get(position).getGambar()))) {
            holder.imageKinship.setImageResource(R.mipmap.ic_launcher);
        } else {
            Glide.with(context).load(gambarKinship).into(holder.imageKinship);
        }

        holder.cd_detail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, DetailArtikel.class);
                intent.putExtra("id_artikel", listKinshipp.get(position).getIdArtikel());
                intent.putExtra("judul", listKinshipp.get(position).getJudul());
                intent.putExtra("penulis", listKinshipp.get(position).getPenulis());
                intent.putExtra("tanggal", listKinshipp.get(position).getTanggal());
                intent.putExtra("kategori", listKinshipp.get(position).getKategori());
                intent.putExtra("gambar", gambarKinship);
                intent.putExtra("deskripsi", listKinshipp.get(position).getDeskripsi());
                Toast.makeText(context, "Selamat menikmati artikel yang tersedia", Toast.LENGTH_SHORT).show();
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listKinshipp.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView imageKinship;
        TextView tv_judulkinship, tv_tanggalkinship, tv_penuliskinship;
        CardView cd_detail;

        public MyViewHolder(View itemView) {
            super(itemView);
            imageKinship        =   itemView.findViewById(R.id.image_artikel);
            tv_judulkinship     =   itemView.findViewById(R.id.txt_judul);
            tv_penuliskinship   =   itemView.findViewById(R.id.txt_penulis);
            tv_tanggalkinship   =   itemView.findViewById(R.id.txt_tanggal);
            cd_detail           =   itemView.findViewById(R.id.cd_detail);
        }
    }
}
