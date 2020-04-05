package com.example.mysisfo;

public class DataObject {
    String nim="", nama="", programstudi="", kelas="", tempatlahir="";
    public DataObject(String nim, String nama, String programstudi, String kelas, String tempatlahir){
        this.nim = nim;
        this.nama = nama;
        this.programstudi = programstudi;
        this.kelas = kelas;
        this.tempatlahir = tempatlahir;
    }

    public String getNim() {
        return nim;
    }

    public String getNama() {
        return nama;
    }

    public String getProgramstudi() {
        return programstudi;
    }

    public String getKelas() {
        return kelas;
    }
    public String getTempatlahir() {
        return tempatlahir;
    }
}
