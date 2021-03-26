package com.gdtidm.bacaonline.Artikel.Education.ComEdu;

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

public class AdapterEducation extends RecyclerView.Adapter<AdapterEducation.MyViewHolder> {

    Context context;
    List<ModelArtikel> listEdu;

    public AdapterEducation(Context context, List<ModelArtikel> listEdu) {
        this.context = context;
        this.listEdu = listEdu;
    }

    @NonNull
    @Override
    public AdapterEducation.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_artikel, parent, false);
        AdapterEducation.MyViewHolder holder = new AdapterEducation.MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterEducation.MyViewHolder holder, int position) {
        holder.judul.setText(listEdu.get(position).getJudul());
        holder.penulis.setText(listEdu.get(position).getPenulis());
        holder.tahun.setText(listEdu.get(position).getTanggal());
        String linkGbrEdu = "https://onportofol.000webhostapp.com/baca_online/artikel/foto_artikel/education/" + listEdu.get(position).getGambar();

        if ("null".equals(String.valueOf(listEdu.get(position).getGambar()))) {
            holder.gbr_artikel.setImageResource(R.mipmap.ic_launcher);
        } else {
            Glide.with(context).load(linkGbrEdu).into(holder.gbr_artikel);
        }

        holder.cdetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, DetailArtikel.class);
                intent.putExtra("id_artikel", listEdu.get(position).getIdArtikel());
                intent.putExtra("judul", listEdu.get(position).getJudul());
                intent.putExtra("penulis", listEdu.get(position).getPenulis());
                intent.putExtra("tanggal", listEdu.get(position).getTanggal());
                intent.putExtra("kategori", listEdu.get(position).getKategori());
                intent.putExtra("gambar", linkGbrEdu);
                intent.putExtra("deskripsi", listEdu.get(position).getDeskripsi());
                Toast.makeText(context, "Selamat menikmati artikel yang tersedia", Toast.LENGTH_SHORT).show();
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listEdu.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView gbr_artikel;
        TextView judul, penulis, tahun;
        CardView cdetail;

        public MyViewHolder(View itemView) {
            super(itemView);
            gbr_artikel     =   itemView.findViewById(R.id.image_artikel);
            judul           =   itemView.findViewById(R.id.txt_judul);
            penulis         =   itemView.findViewById(R.id.txt_penulis);
            tahun           =   itemView.findViewById(R.id.txt_tanggal);
            cdetail         =   itemView.findViewById(R.id.cd_detail);
        }
    }
}
