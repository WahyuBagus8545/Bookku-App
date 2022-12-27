package com.wahyubagus.tugasppb;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.view.View;
import android.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity2 extends AppCompatActivity {
    RecyclerView recview;
    FloatingActionButton tambah;
    myadapter myadapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recview = findViewById(R.id.recview);
        recview.setLayoutManager(new LinearLayoutManager(this));
        tambah = findViewById(R.id.btntambah);
        tambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity2.this, AddActivity.class));
            }
        });

        FirebaseRecyclerOptions<Dataholder> options = new FirebaseRecyclerOptions.Builder<Dataholder>()
                .setQuery(FirebaseDatabase.getInstance().getReference().child("list"),Dataholder.class)
                .build();

        myadapter = new myadapter(options);
        recview.setAdapter(myadapter);
    }

    @Override
    protected void onStart() {
        super.onStart();
        myadapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        myadapter.stopListening();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search_menu, menu);
        MenuItem item = menu.findItem(R.id.search);
        SearchView searchView = (SearchView) item.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                processSearch(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                processSearch(newText);
                return false;
            }
        });

        return super.onCreateOptionsMenu(menu);

    }

    private void processSearch(String query) {


        FirebaseRecyclerOptions<Dataholder> options = new FirebaseRecyclerOptions.Builder<Dataholder>()
                .setQuery(FirebaseDatabase.getInstance().getReference().child("list").orderByChild("judul").startAt(query).endAt(query+"\uf8ff"),Dataholder.class)
                .build();

        myadapter = new myadapter(options);
        myadapter.startListening();
        recview.setAdapter(myadapter);


    }
}