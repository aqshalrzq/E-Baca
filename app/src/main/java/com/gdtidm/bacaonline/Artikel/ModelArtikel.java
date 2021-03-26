package com.gdtidm.bacaonline.Artikel;

import androidx.annotation.NonNull;
import com.google.gson.annotations.SerializedName;

public class ModelArtikel {

    @SerializedName("id_artikel")
    private String idArtikel;

    @SerializedName("judul")
    private String judul;

    @SerializedName("penulis")
    private String penulis;

    @SerializedName("tanggal")
    private String tanggal;

    @SerializedName("kategori")
    private String kategori;

    @SerializedName("gambar")
    private String gambar;

    @SerializedName("deskripsi")
    private String deskripsi;

    public String getIdArtikel() {
        return idArtikel;
    }

    public void setIdArtikel(String idArtikel) {
        this.idArtikel = idArtikel;
    }

    public String getJudul() {
        return judul;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public String getPenulis() {
        return penulis;
    }

    public void setPenulis(String penulis) {
        this.penulis = penulis;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    public String getKategori() {
        return kategori;
    }

    public void setKategori(String kategori) {
        this.kategori = kategori;
    }

    public String getGambar() {
        return gambar;
    }

    public void setGambar(String gambar) {
        this.gambar = gambar;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }

    @NonNull
    @Override
    public String toString() {
        return
                "ModelArtikel { " +
                        "id_artikel = '" + idArtikel + '\'' +
                        ", judul = '" + judul + '\'' +
                        ", penulis = '" + penulis + '\'' +
                        ", tanggal = '" + tanggal + '\'' +
                        ", kategori = '" + kategori + '\'' +
                        ", gambar = '" + gambar + '\'' +
                        ", deskripsi = '" + deskripsi + '\'' +
                        "}";
    }
}
