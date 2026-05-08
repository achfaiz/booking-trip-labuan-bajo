package com.example.triplabuanbajo;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.triplabuanbajo.adapter.TripAdapter;
import com.example.triplabuanbajo.model.TripModel;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerTrip;

    ArrayList<TripModel> listTrip;

    TripAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerTrip = findViewById(R.id.recyclerTrip);

        listTrip = new ArrayList<>();

        listTrip.add(new TripModel(
                "Labuan Bajo",
                "Rp 1.500.000",
                R.drawable.labuan_bajo));

        listTrip.add(new TripModel(
                "Desa Wae Rebo",
                "Rp 1.200.000",
                R.drawable.wae_rebo));

        adapter = new TripAdapter(this, listTrip);

        recyclerTrip.setLayoutManager(
                new LinearLayoutManager(this));

        recyclerTrip.setAdapter(adapter);
    }
}