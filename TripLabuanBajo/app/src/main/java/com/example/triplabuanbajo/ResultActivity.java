package com.example.triplabuanbajo;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.OutputStream;

public class ResultActivity extends AppCompatActivity {

    TextView txtHasil;
    Button btnShare, btnDownload;
    LinearLayout layoutResult;

    String hasilBooking;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        // Inisialisasi View
        txtHasil = findViewById(R.id.txtHasil);
        btnShare = findViewById(R.id.btnShare);
        btnDownload = findViewById(R.id.btnDownload);
        layoutResult = findViewById(R.id.layoutResult);

        // Ambil data dari Intent
        Intent intent = getIntent();

        String nama = intent.getStringExtra("nama");
        String email = intent.getStringExtra("email");
        String nomor = intent.getStringExtra("nomor");
        String destinasi = intent.getStringExtra("destinasi");
        String jenisTrip = intent.getStringExtra("jenisTrip");
        String fasilitas = intent.getStringExtra("fasilitas");
        String tanggalTrip = intent.getStringExtra("tanggalTrip");

        // Format hasil booking
        hasilBooking =
                "👤 Nama : " + nama +
                        "\n\n📧 Email : " + email +
                        "\n\n📱 Nomor HP : " + nomor +
                        "\n\n📍 Destinasi : " + destinasi +
                        "\n\n🧳 Jenis Trip : " + jenisTrip +
                        "\n\n⭐ Fasilitas : " + fasilitas +
                        "\n\n📅 Tanggal Trip : " + tanggalTrip;

        txtHasil.setText(hasilBooking);

        // Tombol Share
        btnShare.setOnClickListener(v -> {

            Intent shareIntent = new Intent(Intent.ACTION_SEND);

            shareIntent.setType("text/plain");

            shareIntent.putExtra(
                    Intent.EXTRA_TEXT,
                    hasilBooking
            );

            startActivity(
                    Intent.createChooser(
                            shareIntent,
                            "Share Booking Via"
                    )
            );
        });

        // Tombol Download
        btnDownload.setOnClickListener(v -> {

            // Sembunyikan tombol
            btnShare.setVisibility(View.GONE);
            btnDownload.setVisibility(View.GONE);

            // Capture layout
            saveLayoutAsImage(layoutResult);

            // Tampilkan lagi tombol
            btnShare.setVisibility(View.VISIBLE);
            btnDownload.setVisibility(View.VISIBLE);
        });
    }

    // Function simpan layout jadi gambar
    private void saveLayoutAsImage(View view) {

        Bitmap bitmap = Bitmap.createBitmap(
                view.getWidth(),
                view.getHeight(),
                Bitmap.Config.ARGB_8888
        );

        Canvas canvas = new Canvas(bitmap);
        view.draw(canvas);

        ContentValues values = new ContentValues();

        values.put(
                MediaStore.Images.Media.DISPLAY_NAME,
                "booking_" + System.currentTimeMillis() + ".png"
        );

        values.put(
                MediaStore.Images.Media.MIME_TYPE,
                "image/png"
        );

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {

            values.put(
                    MediaStore.Images.Media.RELATIVE_PATH,
                    Environment.DIRECTORY_PICTURES + "/TripLabuanBajo"
            );
        }

        Uri uri = getContentResolver().insert(
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                values
        );

        try {

            OutputStream outputStream =
                    getContentResolver().openOutputStream(uri);

            bitmap.compress(
                    Bitmap.CompressFormat.PNG,
                    100,
                    outputStream
            );

            outputStream.close();

            Toast.makeText(
                    this,
                    "Berhasil disimpan ke galeri",
                    Toast.LENGTH_SHORT
            ).show();

        } catch (Exception e) {

            e.printStackTrace();

            Toast.makeText(
                    this,
                    "Gagal menyimpan gambar",
                    Toast.LENGTH_SHORT
            ).show();
        }
    }
}