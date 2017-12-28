package id.web.rizki.apotikku;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import id.web.rizki.apotikku.Holder.ObatViewHolder;
import id.web.rizki.apotikku.Interface.ItemClickListener;
import id.web.rizki.apotikku.Model.Obat;

public class ListObat extends AppCompatActivity {

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;

    FirebaseDatabase database;
    DatabaseReference listObat;

    String CategoryId="";
    FirebaseRecyclerAdapter<Obat,ObatViewHolder> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_obat);

        // Firebase
        database = FirebaseDatabase.getInstance();
        listObat = database.getReference("Obat");
        listObat.keepSynced(true);

        recyclerView = findViewById(R.id.recyclerObat);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);

        Bundle extras = getIntent().getExtras();
        CategoryId =(String) extras.getString("Go");
//        CategoryId = getIntent().getExtras().getString("Go");
            LoadListFood(CategoryId);


    }

    private void LoadListFood(String categoryId) {
        adapter = new FirebaseRecyclerAdapter<Obat, ObatViewHolder>(Obat.class,
                R.layout.obatitem,
                ObatViewHolder.class,
                listObat.orderByChild("MenuId").equalTo(categoryId)
                ) {
            @Override
            protected void populateViewHolder(ObatViewHolder viewHolder, Obat model, int position) {
                viewHolder.namaObat.setText(model.getName());
                Picasso.with(getBaseContext()).load(model.getImage())
                        .into(viewHolder.imgObat);
                viewHolder.deskripsi.setText(model.getDescription());
                viewHolder.harga.setText(model.getHarga());

                Obat local = model;
                viewHolder.setItemClickListener(new ItemClickListener() {
                    @Override
                    public void onClick(View view, int position, boolean isLongClick) {
                        Intent detailObat = new Intent(ListObat.this,DetailObat.class);
                        detailObat.putExtra("ObatId", adapter.getRef(position).getKey());
                        startActivity(detailObat);
                    }
                });

            }
        };
        //set adapter
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        Log.d("ADAPTER : ", ""+adapter.getItemCount());
    }
}
