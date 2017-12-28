package id.web.rizki.apotikku;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.sql.CommonDataSource;

import id.web.rizki.apotikku.Common.Common;
import id.web.rizki.apotikku.Database.Database;
import id.web.rizki.apotikku.Holder.CartAdapter;
import id.web.rizki.apotikku.Model.Order;
import id.web.rizki.apotikku.Model.Submit;
import info.hoang8f.widget.FButton;

public class Cart extends AppCompatActivity {

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;

    FirebaseDatabase database;
    DatabaseReference requests;

    TextView textHarga;
    FButton btn_place;

    List<Order> cart = new ArrayList<>();
    CartAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        // Firebase
        database = FirebaseDatabase.getInstance();
        requests = database.getReference("Request");

        // Cast
        recyclerView = (RecyclerView) findViewById(R.id.listcart);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        textHarga = (TextView) findViewById(R.id.total);

        btn_place = (FButton) findViewById(R.id.btn_placeholder);

        LoadListObat();

        btn_place.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showAllertDialog();
            }
        });
    }

    private void showAllertDialog() {
        AlertDialog.Builder alertdialog = new AlertDialog.Builder(Cart.this);
        alertdialog.setTitle("Selesaikan Order");
        alertdialog.setMessage("Alamat : ");

        final EditText edtAdres = new EditText(Cart.this);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT
        );
        edtAdres.setLayoutParams(lp);
        alertdialog.setView(edtAdres);
        alertdialog.setIcon(R.drawable.ic_add_shopping_cart_black_24dp);

        alertdialog.setPositiveButton("YA", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Submit sub = new Submit(
                        Common.currentUser.getPhone(),
                        Common.currentUser.getName(),
                        edtAdres.getText().toString(),
                        textHarga.getText().toString(),
                        cart
                );
                requests.child(String.valueOf(System.currentTimeMillis()))
                        .setValue(sub);
                new Database(getBaseContext()).hapusCart();
                Toast.makeText(Cart.this, "Terimakasih Telah Order", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
        alertdialog.setNegativeButton("TIDAK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        alertdialog.show();

    }

    private void LoadListObat() {
        cart = new Database(this).getCarts();
        adapter = new CartAdapter(cart,this);
        recyclerView.setAdapter(adapter);

        // Akumulasi total harga
        int total = 0;
        for (Order order:cart)
        {
            total+= (Integer.parseInt(order.getPrice())*(Integer.parseInt(order.getQuantity())));
        }
        Locale locale = new Locale("en","US");
        NumberFormat fmt = NumberFormat.getCurrencyInstance(locale);
        textHarga.setText(fmt.format(total));
    }
}
