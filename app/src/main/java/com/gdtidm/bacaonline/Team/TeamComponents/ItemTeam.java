package com.gdtidm.bacaonline.Team.TeamComponents;

import androidx.annotation.NonNull;
import com.google.gson.annotations.SerializedName;

public class ItemTeam {

    @SerializedName("id_nama")
    private String idNama;

    @SerializedName("nama")
    private String nama;

    @SerializedName("kelas")
    private String kelas;

    @SerializedName("absen")
    private String absen;

    @SerializedName("gambar")
    private String gambar;

    public String getIdNama() {
        return idNama;
    }

    public void setIdNama(String idNama) {
        this.idNama = idNama;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getKelas() {
        return kelas;
    }

    public void setKelas(String kelas) {
        this.kelas = kelas;
    }

    public String getAbsen() {
        return absen;
    }

    public void setAbsen(String absen) {
        this.absen = absen;
    }

    public String getGambar() {
        return gambar;
    }

    public void setGambar(String gambar) {
        this.gambar = gambar;
    }

    @NonNull
    @Override
    public String toString() {
        return
                "ItemTeam { " +
                        "id_nama = '" + idNama + '\'' +
                        ", nama = '" + nama + '\'' +
                        ", kelas = '" + kelas + '\'' +
                        ", absen = '" + absen + '\'' +
                        ", gambar = '" + gambar + '\'' +
                        "}";
    }
}
