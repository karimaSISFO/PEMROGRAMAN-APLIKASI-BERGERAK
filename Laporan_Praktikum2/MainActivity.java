package com.example.a13120240038_karima_laprak_2;


import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    TextView tvHarga, tvCounterSesi;
    EditText etNama, etKursi;
    RadioGroup rgKelas;
    CheckBox cbPopcorn, cbMinuman, cbNachos, cbCombo;
    Spinner spStudio, spJam;
    Button btnPesan, btnReset;

    int hargaTiket = 0;
    int maxSesi = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Inisialisasi
        tvHarga = findViewById(R.id.tvHarga);
        tvCounterSesi = findViewById(R.id.tvCounterSesi);
        etNama = findViewById(R.id.etNama);
        etKursi = findViewById(R.id.etKursi);
        rgKelas = findViewById(R.id.rgKelas);
        cbPopcorn = findViewById(R.id.cbPopcorn);
        cbMinuman = findViewById(R.id.cbMinuman);
        cbNachos = findViewById(R.id.cbNachos);
        cbCombo = findViewById(R.id.cbCombo);
        spStudio = findViewById(R.id.spStudio);
        spJam = findViewById(R.id.spJam);
        btnPesan = findViewById(R.id.btnPesan);
        btnReset = findViewById(R.id.btnReset);

        // Spinner
        String[] studio = {"Studio 1","Studio 2","Studio 3","Studio 4","Studio 5"};
        String[] jam = {"10:00","13:00","16:00","19:00"};

        spStudio.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, studio));
        spJam.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, jam));

        // RadioGroup Listener
        rgKelas.setOnCheckedChangeListener((group, checkedId) -> {
            if (checkedId == R.id.rbReguler) hargaTiket = 40000;
            else if (checkedId == R.id.rbPremium) hargaTiket = 65000;
            else if (checkedId == R.id.rbVIP) hargaTiket = 100000;

            tvHarga.setText("Harga: Rp " + hargaTiket);
        });

        // Checkbox limit sesi
        CompoundButton.OnCheckedChangeListener listener = (buttonView, isChecked) -> updateSesi();
        cbPopcorn.setOnCheckedChangeListener(listener);
        cbMinuman.setOnCheckedChangeListener(listener);
        cbNachos.setOnCheckedChangeListener(listener);
        cbCombo.setOnCheckedChangeListener(listener);

        // Tombol Pesan
        btnPesan.setOnClickListener(v -> prosesPesan());

        // Tombol Reset
        btnReset.setOnClickListener(v -> resetForm());
    }

    void updateSesi() {
        int count = 0;
        if (cbPopcorn.isChecked()) count++;
        if (cbMinuman.isChecked()) count++;
        if (cbNachos.isChecked()) count++;
        if (cbCombo.isChecked()) count++;

        tvCounterSesi.setText("Sesi dipilih: " + count);

        // warna counter
        if (count == 0) tvCounterSesi.setTextColor(Color.RED);
        else tvCounterSesi.setTextColor(Color.GREEN);

        // maksimal 3
        if (count >= maxSesi) {
            cbPopcorn.setEnabled(cbPopcorn.isChecked());
            cbMinuman.setEnabled(cbMinuman.isChecked());
            cbNachos.setEnabled(cbNachos.isChecked());
            cbCombo.setEnabled(cbCombo.isChecked());
        } else {
            cbPopcorn.setEnabled(true);
            cbMinuman.setEnabled(true);
            cbNachos.setEnabled(true);
            cbCombo.setEnabled(true);
        }
    }

    void prosesPesan() {
        String nama = etNama.getText().toString();
        String kursi = etKursi.getText().toString();

        if (nama.length() < 3) {
            Toast.makeText(this, "Nama minimal 3 karakter", Toast.LENGTH_SHORT).show();
            return;
        }

        if (kursi.isEmpty()) {
            Toast.makeText(this, "Nomor kursi wajib diisi", Toast.LENGTH_SHORT).show();
            return;
        }

        int total = hargaTiket;

        String snack = "";
        if (cbPopcorn.isChecked()) { snack += "Popcorn "; total += 10000; }
        if (cbMinuman.isChecked()) { snack += "Minuman "; total += 8000; }
        if (cbNachos.isChecked()) { snack += "Nachos "; total += 12000; }
        if (cbCombo.isChecked()) { snack += "Combo "; total += 20000; }

        String hasil = "Nama: " + nama +
                "\nKursi: " + kursi +
                "\nTotal: Rp " + total +
                "\nSnack: " + snack +
                "\nStudio: " + spStudio.getSelectedItem() +
                "\nJam: " + spJam.getSelectedItem();

        new AlertDialog.Builder(this)
                .setTitle("Detail Pesanan")
                .setMessage(hasil)
                .setPositiveButton("OK", null)
                .show();
    }

    void resetForm() {
        etNama.setText("");
        etKursi.setText("");
        rgKelas.clearCheck();

        cbPopcorn.setChecked(false);
        cbMinuman.setChecked(false);
        cbNachos.setChecked(false);
        cbCombo.setChecked(false);

        tvHarga.setText("Harga: Rp 0");
        tvCounterSesi.setText("Sesi dipilih: 0");
    }
}