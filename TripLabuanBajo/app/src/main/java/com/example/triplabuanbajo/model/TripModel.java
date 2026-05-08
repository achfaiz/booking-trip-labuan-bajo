package com.example.triplabuanbajo.model;

public class TripModel {

    private String namaTrip;
    private String hargaTrip;
    private int gambarTrip;

    public TripModel(String namaTrip, String hargaTrip, int gambarTrip) {
        this.namaTrip = namaTrip;
        this.hargaTrip = hargaTrip;
        this.gambarTrip = gambarTrip;
    }

    public String getNamaTrip() {
        return namaTrip;
    }

    public String getHargaTrip() {
        return hargaTrip;
    }

    public int getGambarTrip() {
        return gambarTrip;
    }
}