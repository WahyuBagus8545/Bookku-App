package com.wahyubagus.tugasppb;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.core.Context;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.ViewHolder;
import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

public class myadapter extends FirebaseRecyclerAdapter<Dataholder, myadapter.myviewholder> {



    public myadapter(@NonNull  FirebaseRecyclerOptions<Dataholder> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull  final myviewholder holder, final int position, @NonNull  final Dataholder model) {



        holder.judul.setText(model.getJudul());
        holder.deskripsi.setText(model.getDeskripsi());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), Detail_Activity.class);

                    intent.putExtra("singleJudul",model.getJudul());
                    intent.putExtra("singleNama",model.getNama());
                    intent.putExtra("singleHarga",model.getHarga());
                    intent.putExtra("singleDeskripsi",model.getDeskripsi());
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    view.getContext().startActivity(intent);

            }
        });

        holder.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final DialogPlus dialogPlus = DialogPlus.newDialog(holder.judul.getContext())
                        .setContentHolder(new ViewHolder(R.layout.dialog_content))
                        .setExpanded(true, 1100)
                        .create();

                View myview = dialogPlus.getHolderView();
                EditText judultxt = myview.findViewById(R.id.judulTxt);
                EditText namatxt = myview.findViewById(R.id.namaTxt);
                EditText hargatxt = myview.findViewById(R.id.hargaTxt);
                EditText deskripsitxt = myview.findViewById(R.id.deskripsiTxt);
                Button button = myview.findViewById(R.id.updateBtn);

                judultxt.setText(model.getJudul());
                namatxt.setText(model.getNama());
                hargatxt.setText(model.getHarga());
                deskripsitxt.setText(model.getDeskripsi());
                dialogPlus.show();
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Map<String, Object> map = new HashMap<>();
                        map.put("judul", judultxt.getText().toString());
                        map.put("nama", namatxt.getText().toString());
                        map.put("harga", hargatxt.getText().toString());
                        map.put("deskripsi", deskripsitxt.getText().toString());

                        FirebaseDatabase.getInstance().getReference().child("list")
                                .child(getRef(position).getKey())
                                .updateChildren(map)
                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void unused) {
                                        dialogPlus.dismiss();
                                    }
                                }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull @NotNull Exception e) {
                                        dialogPlus.dismiss();
                            }
                        });

                    }
                });


            }
        });

        holder.checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Is the view now checked?
                boolean checked = ((CheckBox) view).isChecked();

            }
        });
        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(holder.judul.getContext());
                builder.setTitle("Delete Panel");
                builder.setMessage("Delete .... ?");
                        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                FirebaseDatabase.getInstance().getReference().child("list")
                                        .child(getRef(position).getKey()).removeValue();

                            }
                        });
                        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {

                                    }
                        });
                        builder.show();
            }
        });
    }


    @NotNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull  ViewGroup parent, int viewType) {
       View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row, parent,false);
       return new myviewholder(view);
    }


    class myviewholder extends RecyclerView.ViewHolder{


        TextView judul;
        TextView deskripsi;
        CheckBox checkBox;

        ImageView edit, delete;

        public myviewholder(@NonNull  View itemView) {
            super(itemView);
            judul = itemView.findViewById(R.id.judul);
            deskripsi = itemView.findViewById(R.id.deskripsi);
            checkBox = itemView.findViewById(R.id.checkbox);





            edit = itemView.findViewById(R.id.editBtn);
            delete  = itemView.findViewById(R.id.deleteBtn);

        }



    }
}
