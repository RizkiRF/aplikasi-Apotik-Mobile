package id.web.rizki.apotikku;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.rengwuxian.materialedittext.MaterialEditText;
import id.web.rizki.apotikku.Model.User;

/**
 * Created by rizki on 18/12/17.
 */

public class Daftar extends AppCompatActivity{
    MaterialEditText edtNama,edtEmail,edtPhone,edtPassword;
    Button btnDaftar;

    public Daftar() {


    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daftar);
        edtNama = (MaterialEditText) findViewById(R.id.edt_nama);
        edtEmail = (MaterialEditText) findViewById(R.id.edt_email);
        edtPhone = (MaterialEditText) findViewById(R.id.edt_phone);
        edtPassword = (MaterialEditText) findViewById(R.id.edt_passwordD);

        btnDaftar = (Button) findViewById(R.id.btn_daftar);
        // Inisialisasi database
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference tbl_user = database.getReference("User");

        btnDaftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final ProgressDialog mDialog = new ProgressDialog(Daftar.this);
                mDialog.setMessage("Tunggu sebentar...");
                mDialog.show();

                tbl_user.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        // Cek Apakah data nya telah ada ?
                        if(dataSnapshot.child(edtPhone.getText().toString()).exists())
                        {
                            mDialog.dismiss();
                            Toast.makeText(Daftar.this, "No telah digunakan", Toast.LENGTH_SHORT).show();
                        }
                        else
                        {
                            mDialog.dismiss();
                            User usr = new User(edtNama.getText().toString(), edtPassword.getText().toString());
                            tbl_user.child(edtPhone.getText().toString()).setValue(usr);
                            Toast.makeText(Daftar.this, "Sukses Mendaftar", Toast.LENGTH_SHORT).show();
                            finish();
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });

            }
        });
    }
}
