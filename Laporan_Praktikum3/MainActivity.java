package com.example.laprak_4;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.example.laprak_4.R;

public class MainActivity extends AppCompatActivity {

    public static final String KEY_NIM = "nim";
    public static final String KEY_NAMA = "nama";
    public static final String KEY_KEHADIRAN = "kehadiran";
    public static final String KEY_TUGAS = "tugas";
    public static final String KEY_UTS = "uts";
    public static final String KEY_UAS = "uas";

    EditText edtNim, edtNama;
    TextView txtNilaiAkhir, txtIndeks, txtPredikat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtNim = findViewById(R.id.edt_nim);
        edtNama = findViewById(R.id.edt_nama);
        txtNilaiAkhir = findViewById(R.id.txt_nilai_akhir);
        txtIndeks = findViewById(R.id.txt_indeks);
        txtPredikat = findViewById(R.id.txt_predikat);
    }

    public void bukaActivity2(View view) {

        String nim = edtNim.getText().toString().trim();
        String nama = edtNama.getText().toString().trim();

        if (nim.isEmpty()) {
            edtNim.setError("NIM tidak boleh kosong");
            return;
        }

        if (nama.isEmpty()) {
            edtNama.setError("Nama tidak boleh kosong");
            return;
        }

        Intent i = new Intent(this, com.example.laprak_4.class);
        i.putExtra(KEY_NIM, nim);
        i.putExtra(KEY_NAMA, nama);
        startActivityForResult(i, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {

            double kehadiran = Double.parseDouble(data.getStringExtra(KEY_KEHADIRAN));
            double tugas = Double.parseDouble(data.getStringExtra(KEY_TUGAS));
            double uts = Double.parseDouble(data.getStringExtra(KEY_UTS));
            double uas = Double.parseDouble(data.getStringExtra(KEY_UAS));

            // RUMUS
            double nilaiAkhir = (0.1 * kehadiran) + (0.2 * tugas) + (0.3 * uts) + (0.4 * uas);

            // INDEKS
            String indeks;
            if (nilaiAkhir >= 85) indeks = "A";
            else if (nilaiAkhir >= 75) indeks = "B";
            else if (nilaiAkhir >= 65) indeks = "C";
            else if (nilaiAkhir >= 50) indeks = "D";
            else indeks = "E";

            // PREDIKAT
            String predikat;
            if (nilaiAkhir >= 85) predikat = "Lulus Dengan Pujian";
            else if (nilaiAkhir >= 60) predikat = "Lulus";
            else predikat = "Tidak Lulus";

            txtNilaiAkhir.setText("Nilai Akhir: " + String.format("%.2f", nilaiAkhir));
            txtIndeks.setText("Indeks: " + indeks);
            txtPredikat.setText("Predikat: " + predikat);

            // WARNA
            switch (indeks) {
                case "A": txtIndeks.setTextColor(Color.parseColor("#1B5E20")); break;
                case "B": txtIndeks.setTextColor(Color.parseColor("#388E3C")); break;
                case "C": txtIndeks.setTextColor(Color.parseColor("#F57C00")); break;
                case "D": txtIndeks.setTextColor(Color.parseColor("#D32F2F")); break;
                case "E": txtIndeks.setTextColor(Color.parseColor("#B71C1C")); break;
            }

        } else if (resultCode == RESULT_CANCELED) {
            edtNim.setText("");
            edtNama.setText("");
            txtNilaiAkhir.setText("");
            txtIndeks.setText("");
            txtPredikat.setText("");

            Toast.makeText(this, "Input nilai dibatalkan", Toast.LENGTH_SHORT).show();
        }
    }
}