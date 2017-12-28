package id.web.rizki.apotikku;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.rengwuxian.materialedittext.MaterialEditText;

import id.web.rizki.apotikku.Common.Common;
import id.web.rizki.apotikku.Model.User;

public class LoginActivity extends AppCompatActivity {
    EditText edtUser,edtPass;
    Button btnlogin;
    TextView txtDaftar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_main);

        edtUser = (MaterialEditText) findViewById(R.id.edt_user);
        edtPass = (MaterialEditText) findViewById(R.id.edt_password);
        btnlogin = (Button) findViewById(R.id.btn_sign);
        txtDaftar = (TextView) findViewById(R.id.txt_daftar);

        // Inisialisasi database
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference tbl_user = database.getReference("User");

        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final ProgressDialog mDialog = new ProgressDialog(LoginActivity.this);
                mDialog.setMessage("Tunggu sebentar...");
                mDialog.show();

                tbl_user.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        // Jika data user tidak ditemukan
                        if(dataSnapshot.child(edtUser.getText().toString()).exists()) {
                            // Mengambil informasi user dari database
                            User user = dataSnapshot.child(edtUser.getText().toString()).getValue(User.class);
                            mDialog.dismiss();
                            if (user.getPassword().equals(edtPass.getText().toString())) {

                                Toast.makeText(LoginActivity.this, "Sukses Login", Toast.LENGTH_SHORT).show();
                                Intent masuk = new Intent(LoginActivity.this,Navigasi.class);
                                Common.currentUser = user;
                                startActivity(masuk);
                            } else {
                                Toast.makeText(LoginActivity.this, "Gagal Login", Toast.LENGTH_SHORT).show();
                            }
                        }
                        else
                        {
                            Toast.makeText(LoginActivity.this, "User tidak terdaftar", Toast.LENGTH_SHORT).show();
                        }

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
            }
        });

        txtDaftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent textlogin = new Intent(LoginActivity.this,Daftar.class);
                startActivity(textlogin);

            }
        });

    }
}
