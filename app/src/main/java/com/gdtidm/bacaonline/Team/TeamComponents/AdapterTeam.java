package com.gdtidm.bacaonline.Team.TeamComponents;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.gdtidm.bacaonline.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class AdapterTeam extends RecyclerView.Adapter<AdapterTeam.MyViewHolder> {

    Context context;
    List<ItemTeam>listTeam;

    public AdapterTeam(Context context, List<ItemTeam> listTeam) {
        this.context = context;
        this.listTeam = listTeam;
    }

    @NonNull
    @Override
    public AdapterTeam.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_team, parent, false);
        AdapterTeam.MyViewHolder holder = new AdapterTeam.MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterTeam.MyViewHolder holder, int position) {
        holder.nama.setText(listTeam.get(position).getNama());
        holder.absen.setText(listTeam.get(position).getAbsen());
        holder.kelas.setText(listTeam.get(position).getKelas());

        String image = "https://onportofol.000webhostapp.com/baca_online/team/foto_team/" + listTeam.get(position).getGambar();
        Picasso.get().load(image).into(holder.img);
    }

    @Override
    public int getItemCount() {
        return listTeam.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView nama, kelas, absen;
        ImageView img;

        public MyViewHolder(View itemView) {
            super(itemView);
            nama    =   itemView.findViewById(R.id.teks_nama);
            kelas   =   itemView.findViewById(R.id.teks_kelas);
            absen   =   itemView.findViewById(R.id.teks_noabsen);
            img     =   itemView.findViewById(R.id.imageAnggota);
        }
    }

}
