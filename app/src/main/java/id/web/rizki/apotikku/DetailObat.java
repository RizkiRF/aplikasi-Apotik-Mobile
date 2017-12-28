package id.web.rizki.apotikku;

import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.cepheuen.elegantnumberbutton.view.ElegantNumberButton;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import id.web.rizki.apotikku.Database.Database;
import id.web.rizki.apotikku.Model.Obat;
import id.web.rizki.apotikku.Model.Order;

public class DetailObat extends AppCompatActivity {

    TextView nama_obat,harga_obat,deskripsi_obat,indikasi_obat,komposisi_obat,dosis_obat,efeksamping,kemasan;
    ImageView img_obat;
    CollapsingToolbarLayout collapsingToolbarLayout;
    FloatingActionButton btn_cart;
    ElegantNumberButton num_cont;

    String obatId="";

    FirebaseDatabase database;
    DatabaseReference obats;
    Obat CurrentObat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_detail_obat);

        // firebase
        database = FirebaseDatabase.getInstance();
        obats = database.getReference("Obat");

        // Inisilasi View
        harga_obat = (TextView) findViewById(R.id.harga_obt);
        num_cont = (ElegantNumberButton) findViewById(R.id.counter_btn);
        btn_cart = (FloatingActionButton) findViewById(R.id.btn_chart);
        nama_obat = (TextView) findViewById(R.id.obat_nama);
        deskripsi_obat = (TextView) findViewById(R.id.deskripsi_obat);
        indikasi_obat = (TextView) findViewById(R.id.des_indikasi);
        komposisi_obat = (TextView) findViewById(R.id.des_komposisi);
        dosis_obat = (TextView) findViewById(R.id.des_dosis);
        efeksamping = (TextView) findViewById(R.id.des_efeksamping);
        kemasan = (TextView) findViewById(R.id.des_kemasan);
        img_obat = (ImageView) findViewById(R.id.obat_image);

        collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collaps);
        collapsingToolbarLayout.setExpandedTitleTextAppearance(R.style.ExpandedAppbar);
        collapsingToolbarLayout.setCollapsedTitleTextAppearance(R.style.CollapsedAppbar);

        //Ambil dari dari parse intent yang di lempar
            obatId = getIntent().getStringExtra("ObatId");

            getDetailObat(obatId);

        final String discon = "0";
        btn_cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Database(DetailObat.this).tambahkanCart(new Order(
                        obatId,
                        CurrentObat.getName(),
                        num_cont.getNumber(),
                        CurrentObat.getHarga(),
                        discon
                ));
                Toast.makeText(DetailObat.this,"Data Di Tambahkan ke Keranjang",Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void getDetailObat(final String obatId) {
        obats.child(obatId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                CurrentObat = dataSnapshot.getValue(Obat.class);

                //Atur Gambar awal
                Picasso.with(getBaseContext()).load(CurrentObat.getImage())
                        .into(img_obat);

                collapsingToolbarLayout.setTitle(CurrentObat.getName());
                harga_obat.setText(CurrentObat.getHarga());
                nama_obat.setText(CurrentObat.getName());
                deskripsi_obat.setText(CurrentObat.getDescription());
                komposisi_obat.setText(CurrentObat.getKomposisi());
                indikasi_obat.setText(CurrentObat.getIndikasi());
                dosis_obat.setText(CurrentObat.getDosis());
                efeksamping.setText(CurrentObat.getEfek());
                kemasan.setText(CurrentObat.getKemasan());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
