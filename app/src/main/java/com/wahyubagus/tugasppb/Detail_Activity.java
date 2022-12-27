package com.wahyubagus.tugasppb;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class Detail_Activity extends AppCompatActivity {

    TextView singleJudul;
    TextView singleNama;
    TextView singleHarga;
    TextView singleDeskripsi;

    Button btnmundur;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        singleJudul = findViewById(R.id.singleJudul);
        singleNama = findViewById(R.id.singleNama);
        singleHarga = findViewById(R.id.singleHarga);
        singleDeskripsi = findViewById(R.id.singleDeskripsi);



        btnmundur=findViewById(R.id.btnmundur);

        btnmundur.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Detail_Activity.this, MainActivity2.class));
            }
        });


        singleJudul.setText(getIntent().getStringExtra("singleJudul"));
        singleNama.setText(getIntent().getStringExtra("singleNama"));
        singleHarga.setText(getIntent().getStringExtra("singleHarga"));
        singleDeskripsi.setText(getIntent().getStringExtra("singleDeskripsi"));


    }
}