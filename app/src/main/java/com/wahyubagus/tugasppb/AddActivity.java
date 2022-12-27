package com.wahyubagus.tugasppb;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

import java.io.IOException;

public class AddActivity extends AppCompatActivity {
    EditText eroll,ejudul,enama,eharga,edeskripsi;
    Button add,view;

    Button upfoto;
    ImageView imgView;
    Uri uri;
    ProgressDialog dialog;


    FirebaseStorage firebaseStorage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_activity);
        eroll = findViewById(R.id.roll);
        ejudul = findViewById(R.id.judul);
        enama= findViewById(R.id.namapengarang);
        eharga = findViewById(R.id.harga);
        edeskripsi = findViewById(R.id.deskripsi);




        add = findViewById(R.id.add);
        view = findViewById(R.id.view);

        dialog = new ProgressDialog(this);
        dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        dialog.setMessage("Please Wait...");
        dialog.setCancelable(false);
        dialog.setTitle("Product Uploading");
        dialog.setCanceledOnTouchOutside(false);

        view.setOnClickListener(view -> openAct2());

        add.setOnClickListener(view -> {


            if (eroll.getText().toString().isEmpty() && ejudul.getText().toString().isEmpty() && enama.getText().toString().isEmpty() && eharga.getText().toString().isEmpty() && edeskripsi.getText().toString().isEmpty()){

                Toast.makeText(this, "Please enter all fields", Toast.LENGTH_SHORT).show();
            }
            else {

                String roll = eroll.getText().toString();
                String judul = ejudul.getText().toString();
                String nama = enama.getText().toString();
                String harga = eharga.getText().toString();
                String deskripsi = edeskripsi.getText().toString();



                Dataholder obj = new Dataholder(judul,nama,harga,deskripsi);
                FirebaseDatabase db = FirebaseDatabase.getInstance();
                DatabaseReference node = db.getReference("list");

                node.child(roll).setValue(obj);
                eroll.setText("");
                ejudul.setText("");
                enama.setText("");
                eharga.setText("");
                edeskripsi.setText("");

                Toast.makeText(this, "Data inserted", Toast.LENGTH_SHORT).show();

                };
            });

    };




    private void openAct2() {

        Intent intent = new Intent(this, MainActivity2.class);
        startActivity(intent);
    }
}