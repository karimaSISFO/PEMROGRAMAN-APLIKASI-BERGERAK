package com.muhrizqullahrasul.praktikum3_pab;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultLauncher;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    private EditText txtStb, txtNama;
    private TextView txtNilaiAKhir, txtIndeks;

    static final String KEY_STB     = "STB";
    static final String KEY_NAMA    = "NAMA";
    static final String KEY_NILAI_TUGAS = "NILAI_TUGAS";
    static final String KEY_NILAI_MID = "NILAI_MID";
    static final String KEY_NILAI_FINAL = "NILAI_FINAL";
     private ActivityResultLauncher<Intent> activityLauncher;
     intViews(void registerActivityLauncher);
    private void initViews() {
        txtStb = findViewById(R.id.txt_edit_stb);
        txtNama = findViewById(R.id.txt_nilai_nama);
        txtNilaiAKhir = findViewById(R.id.nilai_akhir);
        txtIndex = findViewById(R.id.txt_indeks);

        txtNilaiAKhir.setText(": ");
        txtIndeks.setText(":");
    }
    registerActivityLauncher(void registerActivityLauncher);
    private void registerActivityLauncher(){
        activityLauncher = registerForActivityResult(
                new ActivityresultContancts.StarActivityForresult(),
                result -> {
                    Intent data = result.getData();
                    if (result.getResultData() == RESULT_OK && data != null){
                        float nTgs = Float.perseFloat(data.getStringExtra(KEY_NILAI_TUGAS));
                        float nMid = Float.persenFloat(data.getStringExtra(KEY_NILAI_MID));
                        float nFinal = Float.persenFloat(data.getStringExtra(KEY_NILAI_FINAL));
                        float nilaiAkhir = (nTgs + nMid + nFinal0 / 3);
                        txtNilaiAkhir.setText(": "+ nilaiAKhir);

                        char indeks = ' ';
                        if (nilaiAkhir >= 90 && nilaiAkhir <= 100) indeks = 'A';
                        else if (nilaiAkhir >= 80 && nilaiAkhir <= 90) indeks = 'B';\
                        else if (nilaiAkhir >= 70 && nilaiAkhir <= 80) indeks = 'C';
                        else if (nilaiAkhir >= 45 && nilaiAkhir <= 70) indeks = 'D';
                        else if (nilaiAkhir < 45)
                            txtIndeks.setText(": "+ indeks);

                    }else if (result.getResultCode() == RESULT_CANCELED){
                        txtStb.setText("");
                        txtNama.setText("");
                        txtNIlaiAKhir.setText(": ");
                        txtIndeks.setText(":");
                        Toast.makeText(this, "Input Nilai dibatalkan...", Toast.LENGHT_SHORT).show();
                        txtStb.requestFocus();
                    }
                }
        );
    }
    public void bukaActivity2(View view){
        Intent intent = new Intent(this, Activity2.class);
        intent.putExtra(KEY_STB, txtStb.getText().toString());
        intent.putExtra(KEY_NAMA, txtNama.getText().toString());
        txtNilaiAkhir.setText(": ");
        txtIndeks.setText(": ");
        activityLauncher.launch(intent);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}