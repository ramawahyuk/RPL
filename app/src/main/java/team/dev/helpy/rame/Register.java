package team.dev.helpy.rame;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class Register extends AppCompatActivity implements View.OnClickListener {


    private EditText field_email_regis;
    private EditText field_pass_regis;
    private FirebaseAuth firebaseAuth;
    private EditText field_nama;
    private EditText field_alamat;
    private EditText field_jk;
    private ProgressDialog mProgress;
    private DatabaseReference mDatabase,datawil;
    private StorageReference mStorage;
    private Uri mImageUri = null;
    private EditText field_kontak;
    private EditText field_kdwilayah;
    private TextView textSignIn;
    RadioGroup radioGroup;
    RadioButton radioButton;
    Button registerButton;
    String user,pass,alamat,nomor,genderUser,kode_wilayah;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_register);
        mStorage = FirebaseStorage.getInstance().getReference();

        mDatabase = FirebaseDatabase.getInstance().getReference().child("pengguna");
        datawil= FirebaseDatabase.getInstance().getReference().child("wilayah");
        field_email_regis = (EditText) findViewById(R.id.field_email_regis);
        field_pass_regis = (EditText) findViewById(R.id.field_pass_regis);
        field_alamat = (EditText) findViewById(R.id.field_alamat);
        field_nama = (EditText) findViewById(R.id.field_name);
        field_jk=(EditText)findViewById(R.id.field_jk);
        field_kontak = (EditText) findViewById(R.id.field_kontak);
        textSignIn = (TextView) findViewById(R.id.textViewSignIn);
        textSignIn.setOnClickListener(this);
        registerButton = (Button) findViewById(R.id.btn_regis);
        mProgress = new ProgressDialog(this);
        firebaseAuth = FirebaseAuth.getInstance();


    }

    private  void star_regis(){

        //proses input data ke database firebase

        mProgress.setMessage("Mohon Tunggu...Sedang Proses Posting");
        mProgress.show();

        final String nama = field_nama.getText().toString();
        final String alamat =field_alamat.getText().toString();
        final String Jk=field_jk.getText().toString();
        final String kontak=field_kontak.getText().toString();


        if (!TextUtils.isEmpty(nama) && !TextUtils.isEmpty(alamat)&& !TextUtils.isEmpty(Jk)&& !TextUtils.isEmpty(kontak)){

            StorageReference filepath = mStorage.child("pengguna").child(field_alamat.getText().toString());

            DatabaseReference newPost = mDatabase.push();
            newPost.child("nama").setValue(nama);
            newPost.child("alamat").setValue(alamat);
            newPost.child("jk").setValue(Jk);
            newPost.child("kontak").setValue(kontak);
            mProgress.dismiss();
            startActivity(new Intent(Register.this,Login.class));
        }

    }

// method verifikasi register
    public void btn_regis_click(View view) {

        String email =field_email_regis.getText().toString();
        String pass=field_pass_regis.getText().toString();
        String nama = field_nama.getText().toString();
        String alamat =field_alamat.getText().toString();
        String Jk=field_jk.getText().toString();
        String kontak=field_kontak.getText().toString();

        if(TextUtils.isEmpty(nama)){
            Toast.makeText(this,"Masukan Nama Anda terlebih dahulu",Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(email)){
            Toast.makeText(this,"Masukan Email Anda terlebih dahulu",Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(pass)){
            Toast.makeText(this,"Masukan Password Anda terlebih dahulu",Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(alamat)){
            Toast.makeText(this,"Masukan Alamat Anda terlebih dahulu",Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(Jk)){
            Toast.makeText(this,"Tentukan Jenis kelamin Anda terlebih dahulu",Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(kontak)){
            Toast.makeText(this,"Masukan Nomor Kontak Anda terlebih dahulu",Toast.LENGTH_SHORT).show();
            return;
        }


        final ProgressDialog progressDialog = ProgressDialog.show(Register.this,"Tunggu...","Sedang Prosess.",true);
        (firebaseAuth.createUserWithEmailAndPassword(field_email_regis.getText().toString(),field_pass_regis.getText().toString())).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override //method apabila register berhasil
            public void onComplete(/*@NonNull*/ Task<AuthResult> task) {

                progressDialog.dismiss();

                if (task.isSuccessful()){
                    Toast.makeText(Register.this,"Register Sukses",Toast.LENGTH_LONG).show();
                    Intent i = new Intent(Register.this,Login.class);
                    star_regis();
                    startActivity(i);

                }

                else {
                    Log.e("ERROR",task.getException().toString());
                    Toast.makeText(Register.this,task.getException().getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    @Override
    public void onClick(View v) {

        if(v==textSignIn){
            startActivity(new Intent(this,Login.class));
        }

    }
}

