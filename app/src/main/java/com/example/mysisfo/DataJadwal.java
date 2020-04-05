package com.example.mysisfo;

public class DataJadwal {
    String kodemk="", namamk="", hari="", waktu="", dosen="";
    public DataJadwal(String kodemk, String namamk, String hari, String waktu, String dosen){
        this.kodemk = kodemk;
        this.namamk = namamk;
        this.hari = hari;
        this.waktu = waktu;
        this.dosen = dosen;
    }

    public String getKodemk() { return kodemk; }

    public String getNamamk() {
        return namamk;
    }

    public String getHari() {
        return hari;
    }

    public String getWaktu() {
        return waktu;
    }
    public String getDosen() {
        return dosen;
    }
}
