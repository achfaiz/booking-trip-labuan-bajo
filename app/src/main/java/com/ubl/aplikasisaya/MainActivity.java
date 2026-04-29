package com.ubl.aplikasisaya;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Spinner spCountry = findViewById(R.id.spCountry);
        String[] countries = {"-- Pilih Jurusan --", "Teknik Informatika", "Sistem Informasi", "Ilmu Komputer"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_dropdown_item,
                countries
        );
        spCountry.setAdapter(adapter);

        Button btnRegister = findViewById(R.id.btn_register);
        EditText txtFullName = findViewById(R.id.txt_fullname);
        EditText txtEmail = findViewById(R.id.txt_email);
        EditText txtAddress = findViewById(R.id.txt_address);
        TextView txtResult = findViewById(R.id.txt_result);
        RadioGroup rgSemester = findViewById(R.id.rgSemester);
        CheckBox cbAgree = findViewById(R.id.cbAgree);

        btnRegister.setOnClickListener(v -> {

            String fullName = txtFullName.getText().toString().trim();
            String email = txtEmail.getText().toString().trim();
            String address = txtAddress.getText().toString().trim();
            String jurusan = spCountry.getSelectedItem().toString();
            int selectedId = rgSemester.getCheckedRadioButtonId();

            TextView tvErrorJurusan = findViewById(R.id.tvErrorJurusan);
            TextView tvErrorSemester = findViewById(R.id.tvErrorSemester);
            TextView tvErrorAgreement = findViewById(R.id.tvErrorAgreement);

            tvErrorJurusan.setVisibility(View.GONE);
            tvErrorSemester.setVisibility(View.GONE);
            tvErrorAgreement.setVisibility(View.GONE);

            if (fullName.isEmpty() && email.isEmpty() && address.isEmpty()) {
                Toast.makeText(this, "Semua field harus diisi", Toast.LENGTH_SHORT).show();
                return;
            }

            boolean valid = true;

            if (jurusan.equals("-- Pilih Jurusan --")) {
                tvErrorJurusan.setVisibility(View.VISIBLE);
                valid = false;
            }

            if (selectedId == -1) {
                tvErrorSemester.setVisibility(View.VISIBLE);
                valid = false;
            }
            if (!cbAgree.isChecked()) {
                tvErrorAgreement.setVisibility(View.VISIBLE);
                valid = false;
            }

            if (!valid) return;

            RadioButton selectedRadio = findViewById(selectedId);
            String semester = selectedRadio.getText().toString();
            String title = "Data yang Didaftarkan:";
            String text = title + "\n\n" +
                    String.format(
                            "%-10s : %s\n" +
                                    "%-10s : %s\n" +
                                    "%-10s : %s\n" +
                                    "%-10s : %s\n" +
                                    "%-10s : %s",
                            "Nama", fullName,
                            "Email", email,
                            "Address", address,
                            "Jurusan", jurusan,
                            "Semester", semester
                    );

            SpannableString spannable = new SpannableString(text);

            int end = title.length();
            spannable.setSpan(new StyleSpan(Typeface.BOLD),
                    0, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

            spannable.setSpan(new ForegroundColorSpan(Color.parseColor("#6200EE")),
                    0, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

            txtResult.setText(spannable);
            txtResult.setVisibility(View.VISIBLE);
        });
    }
}