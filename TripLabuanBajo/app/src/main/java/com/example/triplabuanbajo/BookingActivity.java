package com.example.triplabuanbajo;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class BookingActivity extends AppCompatActivity {
    Spinner spinnerDestinasi;
    CheckBox cbHotel, cbMakan, cbDokumentasi;
    DatePicker datePickerTrip;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking);
        sharedPreferences = getSharedPreferences(
                "BookingData",
                MODE_PRIVATE);
        spinnerDestinasi = findViewById(R.id.spinnerDestinasi);
        EditText etNama, etEmail, etNomor;
        Button btnBookingSekarang;
        RadioButton rbOpenTrip, rbPrivateTrip;

        String[] destinasi = {
                "<----------------Pilih Destinasi---------------->",
                "Labuan Bajo",
                "Desa Wae Rebo"
        };

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_dropdown_item,
                destinasi
        );

        spinnerDestinasi.setAdapter(adapter);

        etNama = findViewById(R.id.etNama);
        etEmail = findViewById(R.id.etEmail);
        etNomor = findViewById(R.id.etNomor);
        String savedNama =
                sharedPreferences.getString("nama", "");

        String savedEmail =
                sharedPreferences.getString("email", "");

        etNama.setText(savedNama);
        etEmail.setText(savedEmail);
        cbHotel = findViewById(R.id.cbHotel);
        cbMakan = findViewById(R.id.cbMakan);
        cbDokumentasi = findViewById(R.id.cbDokumentasi);
        datePickerTrip = findViewById(R.id.datePickerTrip);

        btnBookingSekarang = findViewById(R.id.btnBookingSekarang);

        rbOpenTrip = findViewById(R.id.rbOpenTrip);
        rbPrivateTrip = findViewById(R.id.rbPrivateTrip);

        btnBookingSekarang.setOnClickListener(v -> {

            String nama = etNama.getText().toString();
            String email = etEmail.getText().toString();
            String nomor = etNomor.getText().toString();

            String pilihDestinasi = spinnerDestinasi.getSelectedItem().toString();

            String jenisTrip = "";
            if (rbOpenTrip.isChecked()) {
                jenisTrip = "Open Trip";
            } else if (rbPrivateTrip.isChecked()) {
                jenisTrip = "Private Trip";
            }

            if (nama.isEmpty()) {

                etNama.setError("Nama wajib diisi");
                etNama.requestFocus();
                return;

            }

            if (email.isEmpty()) {

                etEmail.setError("Email wajib diisi");
                etEmail.requestFocus();
                return;

            }

            if (!email.contains("@")) {

                etEmail.setError("Email tidak valid");
                etEmail.requestFocus();
                return;

            }

            if (nomor.isEmpty()) {

                etNomor.setError("Nomor HP wajib diisi");
                etNomor.requestFocus();
                return;

            }

            if (nomor.length() < 10) {

                etNomor.setError("Nomor HP minimal 10 digit");
                etNomor.requestFocus();
                return;

            }

            if (pilihDestinasi.equals("<----------------Pilih Destinasi---------------->")) {

                Toast.makeText(this,
                        "Silakan pilih destinasi",
                        Toast.LENGTH_SHORT).show();

                return;

            }

            if (!rbOpenTrip.isChecked() &&
                    !rbPrivateTrip.isChecked()) {

                Toast.makeText(this,
                        "Pilih jenis trip",
                        Toast.LENGTH_SHORT).show();

                return;

            }

            if (!cbHotel.isChecked() &&
                    !cbMakan.isChecked() &&
                    !cbDokumentasi.isChecked()) {

                Toast.makeText(this,
                        "Pilih minimal 1 fasilitas",
                        Toast.LENGTH_SHORT).show();

                return;
            } else {
                String fasilitas = "";

                if (cbHotel.isChecked()) {
                    fasilitas += "Hotel ";
                }

                if (cbMakan.isChecked()) {
                    fasilitas += "Makan ";
                }

                if (cbDokumentasi.isChecked()) {
                    fasilitas += "Dokumentasi ";
                }

                int hari = datePickerTrip.getDayOfMonth();
                int bulan = datePickerTrip.getMonth() + 1;
                int tahun = datePickerTrip.getYear();

                String tanggalTrip = hari + "/" + bulan + "/" + tahun;

                SharedPreferences.Editor editor =
                        sharedPreferences.edit();

                editor.putString("nama", nama);
                editor.putString("email", email);

                editor.apply();

                Intent intent = new Intent(
                        BookingActivity.this,
                        ResultActivity.class);

                intent.putExtra("nama", nama);
                intent.putExtra("email", email);
                intent.putExtra("nomor", nomor);
                intent.putExtra("destinasi", pilihDestinasi);
                intent.putExtra("jenisTrip", jenisTrip);
                intent.putExtra("fasilitas", fasilitas);
                intent.putExtra("tanggalTrip", tanggalTrip);

                startActivity(intent);
            }

        });
    }
}