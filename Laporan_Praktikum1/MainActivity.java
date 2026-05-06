package com.example.a13120240038_laprak1;

import android.app.AlertDialog;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private EditText txtNama, txtNim, txtEmail;
    private CheckBox cbMembaca, cbPrograming, cbOlahraga, cbMusik;
    private RadioGroup rgJenisKelamin;
    private Spinner spinnerProdi;
    private String selectedProdi = "";
    private Button btnSubmit, btnReset;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        initViews();
        setupSpinner();
        setupButtonListener();

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private void initViews() {
        txtNama = findViewById(R.id.txtNama);
        txtNim = findViewById(R.id.txtNim);
        txtEmail = findViewById(R.id.txtEmail);

        cbMembaca = findViewById(R.id.cbMembaca);
        cbPrograming = findViewById(R.id.cbPrograming);
        cbOlahraga = findViewById(R.id.cbOlahraga);
        cbMusik = findViewById(R.id.cbMusik);

        rgJenisKelamin = findViewById(R.id.rgJenisKelamin);
        spinnerProdi = findViewById(R.id.spinnerProdi);

        btnSubmit = findViewById(R.id.btnSubmit);
        btnReset = findViewById(R.id.btnReset);
    }

    private void setupSpinner() {
        String[] daftarProdi = {
                "-- Pilih program studi --",
                "Teknik Informatika",
                "Sistem Informasi",
                "Ilmu Komputer",
                "Teknik Elektro",
                "Teknik Mesin"
        };

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_item,
                daftarProdi
        );

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerProdi.setAdapter(adapter);

        spinnerProdi.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedProdi = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                selectedProdi = "";
            }
        });
    }

    private void setupButtonListener() {
        btnSubmit.setOnClickListener(v -> tampilkanData());
        btnReset.setOnClickListener(v -> resetForm());
    }

    private void tampilkanData() {
        String nama = txtNama.getText().toString().trim();
        String nim = txtNim.getText().toString().trim();
        String email = txtEmail.getText().toString().trim();

        if (nama.isEmpty() || nim.isEmpty() || email.isEmpty()) {
            Toast.makeText(this, "Nama, NIM, dan Email wajib diisi!", Toast.LENGTH_SHORT).show();
            return;
        }

        StringBuilder hobi = new StringBuilder();
        if (cbMembaca.isChecked()) hobi.append("Membaca, ");
        if (cbPrograming.isChecked()) hobi.append("Programming, ");
        if (cbOlahraga.isChecked()) hobi.append("Olahraga, ");
        if (cbMusik.isChecked()) hobi.append("Musik, ");

        String hobiStr = hobi.length() > 0
                ? hobi.substring(0, hobi.length() - 2)
                : "Tidak ada hobi dipilih";

        String jenisKelamin = "Belum dipilih";
        int selectedRadioId = rgJenisKelamin.getCheckedRadioButtonId();
        if (selectedRadioId != -1) {
            RadioButton selectedRadio = findViewById(selectedRadioId);
            jenisKelamin = selectedRadio.getText().toString();
        }

        String prodi = selectedProdi.equals("-- Pilih program studi --") || selectedProdi.isEmpty()
                ? "Belum dipilih"
                : selectedProdi;

        String hasil =
                "Nama   : " + nama + "\n" +
                        "NIM    : " + nim + "\n" +
                        "Email  : " + email + "\n" +
                        "Hobi   : " + hobiStr + "\n" +
                        "Jenis Kelamin : " + jenisKelamin + "\n" +
                        "Prodi  : " + prodi;

        TextView messageView = new TextView(this);
        messageView.setText(hasil);
        messageView.setTypeface(Typeface.MONOSPACE);
        messageView.setPadding(50, 30, 50, 10);

        new AlertDialog.Builder(this)
                .setTitle("Data Mahasiswa")
                .setView(messageView)
                .setPositiveButton("OK", null)
                .show();
    }

    private void resetForm() {
        txtNama.setText("");
        txtNim.setText("");
        txtEmail.setText("");

        cbMembaca.setChecked(false);
        cbPrograming.setChecked(false);
        cbOlahraga.setChecked(false);
        cbMusik.setChecked(false);

        rgJenisKelamin.clearCheck();
        spinnerProdi.setSelection(0);

        Toast.makeText(this, "Form telah direset.", Toast.LENGTH_SHORT).show();
    }
}