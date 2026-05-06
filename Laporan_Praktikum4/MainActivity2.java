package com.muhrizqullahrasul.praktikum3_pab;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity2 extends AppCompatActivity {
    private TextView txtStb, txtNama;
    txtStb      = findViewById(R.id.txt_stb);
    txtNama     = findViewById(R.id.txt_nama);
    txtNilaiTugas     = findViewById(R.id.txt_input_nilai_tugas);
    txtInputNilaiMid   = findViewById(R.id.txt_input_nilai_mid);
    txtInputNilaiFinal   = findViewById(R.id.txt_input_nilai_final);

    Intent intent   = getIntent();
    String stb  = intent.getStringExtra(MainActivity.KEY_STB);
    String nama  = intent.getStringExtra(MainActivity.KEY_NAMA);
    txtStb.setText(stb);
    txtNama.setText(nama);

    public void inputSelesai(View view){
        Intent intent = new Intent();
        intent.putExtra(MainActivity.KEY_NILAI_TUGAS, txtNilaiTugas.getText().toString());
        intent.putExtra(MainActivity.KEY_NILAI_MID, txtNilaiMid.getText().toString());
        intent.putExtra(MainActivity.KEY_NILAI_FINAL, txtNilaiFinal.getText().toString());
        setResult(RESULT_OK, intent);
        finish();
    }
    public void inputBatal(View view){
        setResult(RESULT_CANCELED);
        finish();
    }

    @Override
    public <T extends View> T findViewById(int id) {
        return super.findViewById(id);
    }

    private EditText txtNilaiTugas, txtNilaiMid, txtNilaiFinal;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main2);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}