package com.wahyubagus.tugasppb;

public class Dataholder {

    String judul, nama, harga,deskripsi;


    public Dataholder(){
    }

    public Dataholder(String judul, String nama, String harga, String deskripsi) {
        this.judul = judul;
        this.nama = nama;
        this.harga = harga;
        this.deskripsi = deskripsi;


    }

    public String getJudul() {
        return judul;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getHarga() {
        return harga;
    }

    public void setHarga(String harga) {
        this.harga = harga;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }


}
