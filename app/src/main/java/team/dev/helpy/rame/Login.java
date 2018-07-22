package team.dev.helpy.rame;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.GoogleAuthProvider;


public class Login extends AppCompatActivity implements View.OnClickListener {

    private EditText field_email_login;
    private EditText field_pass_login;
    private FirebaseAuth firebaseAuth;
    private Button btn_login;
    private TextView txtsignup;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private static final int RC_SIGN_IN=1;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login);

        mAuthListener=new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                if(firebaseAuth.getCurrentUser()!=null){
                    finish();
                    startActivity(new Intent(Login.this,menubeta.class));
                }
            }
        };
        firebaseAuth = FirebaseAuth.getInstance();
        txtsignup=(TextView)findViewById(R.id.textViewSignUp);
        field_email_login = (EditText)findViewById(R.id.field_email_login);
        field_pass_login = (EditText)findViewById(R.id.field_pass_login);
        btn_login=(Button)findViewById(R.id.btn_login);
        btn_login.setOnClickListener(this);
        txtsignup.setOnClickListener(this);
        progressDialog=new ProgressDialog(this);

    }


    // proses verifikasi login
        private void userLogin () {
            String email=field_email_login.getText().toString().trim();
            String pass=field_pass_login.getText().toString().trim();

            if(TextUtils.isEmpty(email)&& TextUtils.isEmpty(pass)){
                Toast.makeText(this, "Please enter your Email and password", Toast.LENGTH_SHORT).show();
            }

            if(TextUtils.isEmpty(email)){
                Toast.makeText(this, "Please Enter an Email", Toast.LENGTH_SHORT).show();
            }
            if(TextUtils.isEmpty(pass)){
                Toast.makeText(this,"Please enter your password",Toast.LENGTH_SHORT).show();
            }

            progressDialog.setMessage("Loading....");
            progressDialog.show();
            firebaseAuth.signInWithEmailAndPassword(email,pass)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {


                            progressDialog.dismiss();
                            if(task.isSuccessful()){
                                finish();
                                startActivity(new Intent(getApplicationContext(),menubeta.class));
                            }else{
                                Toast.makeText(Login.this,"User dan password yang Anda masukan salah",Toast.LENGTH_SHORT).show();

                            }

                        }
                    });
        }


        //method untuk tombol LOGIN dan tombol menuju ke class register
        @Override
        public void onClick (View v){
            if(v==btn_login){
                userLogin();
            }
            if(v==txtsignup){
                startActivity(new Intent(this,Register.class));
            }
        }



}
