package com.example.laprak_4;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity2 extends AppCompatActivity {

    EditText edtKehadiran, edtTugas, edtUTS, edtUAS;

    public static final String KEY_KEHADIRAN = "kehadiran";
    public static final String KEY_TUGAS = "tugas";
    public static final String KEY_UTS = "uts";
    public static final String KEY_UAS = "uas";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        edtKehadiran = findViewById(R.id.edt_kehadiran);
        edtTugas = findViewById(R.id.edt_tugas);
        edtUTS = findViewById(R.id.edt_uts);
        edtUAS = findViewById(R.id.edt_uas);
    }

    public void simpan(View view) {

        if (edtKehadiran.getText().toString().isEmpty()) {
            edtKehadiran.setError("Isi dulu");
            return;
        }

        Intent i = new Intent();
        i.putExtra(KEY_KEHADIRAN, edtKehadiran.getText().toString());
        i.putExtra(KEY_TUGAS, edtTugas.getText().toString());
        i.putExtra(KEY_UTS, edtUTS.getText().toString());
        i.putExtra(KEY_UAS, edtUAS.getText().toString());

        setResult(RESULT_OK, i);
        finish();
    }

    public void batal(View view) {
        setResult(RESULT_CANCELED);
        finish();
    }
}