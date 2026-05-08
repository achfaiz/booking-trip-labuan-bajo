package com.example.triplabuanbajo.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.triplabuanbajo.BookingActivity;
import com.example.triplabuanbajo.R;
import com.example.triplabuanbajo.model.TripModel;

import java.util.ArrayList;

public class TripAdapter extends RecyclerView.Adapter<TripAdapter.ViewHolder> {

    Context context;
    ArrayList<TripModel> listTrip;

    public TripAdapter(Context context, ArrayList<TripModel> listTrip) {
        this.context = context;
        this.listTrip = listTrip;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context)
                .inflate(R.layout.item_trip, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        TripModel trip = listTrip.get(position);

        holder.txtNama.setText(trip.getNamaTrip());
        holder.txtHarga.setText(trip.getHargaTrip());
        holder.imgTrip.setImageResource(trip.getGambarTrip());

        holder.btnBooking.setOnClickListener(v -> {

            Intent intent = new Intent(context, BookingActivity.class);
            context.startActivity(intent);

        });

        holder.btnDetail.setOnClickListener(v -> {

            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse("https://triplabuanbajo.com"));

            context.startActivity(intent);

        });
    }

    @Override
    public int getItemCount() {
        return listTrip.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imgTrip;
        TextView txtNama, txtHarga;
        Button btnBooking, btnDetail;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imgTrip = itemView.findViewById(R.id.imgTrip);
            txtNama = itemView.findViewById(R.id.txtNamaTrip);
            txtHarga = itemView.findViewById(R.id.txtHarga);

            btnBooking = itemView.findViewById(R.id.btnBooking);
            btnDetail = itemView.findViewById(R.id.btnDetail);
        }
    }
}