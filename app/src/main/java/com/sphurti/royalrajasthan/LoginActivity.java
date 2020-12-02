package com.sphurti.royalrajasthan;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.marozzi.roundbutton.RoundButton;

public class LoginActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    EditText userId;
    EditText password;
    TextView signUpText;
    LoginFragment fragment;
    RoundButton loginButton, signUpButton, googleLoginButton;
    private GoogleSignInClient mGoogleSignInClient;
    private static final int RC_SIGN_IN = 9001;
    FragmentTransaction transaction = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ActionBar actionBar=getSupportActionBar();
        actionBar.hide();
        fragment = new LoginFragment();
        signUpButton = findViewById(R.id.signUpButton);
        googleLoginButton = findViewById(R.id.googleLoginButton);


        googleLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("TAG", "Clicked");
                LoginWithGoogle();
            }
        });


        loginButton= findViewById(R.id.emailLogin);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(transaction != null){
                    try {
                        Bundle bundle = fragment.getText();
                        String emailText = bundle.getString("email");
                        String passwordText = bundle.getString("password");
                        if ((emailText.length() > 0) && (passwordText.length() > 0)) {
                            loginButton.startAnimation();
                            Login(emailText, passwordText);
                        } else {
                            Toast.makeText(LoginActivity.this, "Please enter valid Username and Password.", Toast.LENGTH_SHORT).show();
                        }
                    }
                    catch (Exception e){

                    }
                }
                else {
                    FragmentManager manager = getSupportFragmentManager();
                    transaction = manager.beginTransaction();
                    transaction.setCustomAnimations(R.anim.fade_in, R.anim.fade_out);
                    transaction.replace(R.id.frameLayout, fragment).addToBackStack(null);
                    transaction.commit();
                }
            }
        });
        mAuth = FirebaseAuth.getInstance();
        onStart();

        // Configure Google Sign In
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();
        mGoogleSignInClient = GoogleSignIn.getClient(LoginActivity.this, gso);

        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(transaction != null){
                    try{
                        Bundle bundle = fragment.getText();
                        String emailText = bundle.getString("email");
                        String passwordText = bundle.getString("password");
                        if((emailText.length()>0)&&(passwordText.length()>0)){
                            signUpButton.startAnimation();
                            SignUp(emailText, passwordText);
                            Login(emailText, passwordText);
                        }
                        else{
                            Toast.makeText(LoginActivity.this, "Please enter valid username and password.", Toast.LENGTH_SHORT).show();
                        }
                    }
                    catch (Exception e){
                        Log.e("Error in SignUp", e.getMessage());
                    }
                }
                else {
                    FragmentManager manager = getSupportFragmentManager();
                    transaction = manager.beginTransaction();
                    transaction.setCustomAnimations(R.anim.fade_in, R.anim.fade_out);
                    transaction.replace(R.id.frameLayout, fragment).addToBackStack(null);
                    transaction.commit();
                }
            }
        });
    }

    public void SignUp(String email, String pass)
    {
        mAuth.createUserWithEmailAndPassword(email, pass)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d("TAG", "createUserWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            startActivity(new Intent(LoginActivity.this,Home.class));
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w("TAG", "createUserWithEmail:failure", task.getException());
                            Toast.makeText(LoginActivity.this, "Username already exists.",
                                    Toast.LENGTH_SHORT).show();
                        }
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                signUpButton.revertAnimation();
                            }
                        },1000);
                    }
                });
    }

    public void LoginWithGoogle()
    {
        Log.d("TAG", "Invoked");
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
        googleLoginButton.startAnimation();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                // Google Sign In was successful, authenticate with Firebase
                GoogleSignInAccount account = task.getResult(ApiException.class);
                Log.d("TAG", "firebaseAuthWithGoogle:" + account.getId());
                firebaseAuthWithGoogle(account.getIdToken());
                googleLoginButton.setResultState(RoundButton.ResultState.SUCCESS);
                googleLoginButton.revertAnimation();
            } catch (ApiException e) {
                // Google Sign In failed, update UI appropriately
                googleLoginButton.setResultState(RoundButton.ResultState.FAILURE);
                Log.w("TAG", "Google sign in failed", e);
            }
        }
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                googleLoginButton.revertAnimation();
            }
        },2200);
    }

    private void firebaseAuthWithGoogle(String idToken) {
        AuthCredential credential = GoogleAuthProvider.getCredential(idToken, null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d("TAG", "signInWithCredential:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            startActivity(new Intent(LoginActivity.this,Home.class));

                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w("TAG", "signInWithCredential:failure", task.getException());

                        }
                    }
                });
    }

    public void Login(String email, String pass)
    {
        try {
            Task<AuthResult> authResultTask = mAuth.signInWithEmailAndPassword(email, pass)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                // Sign in success, update UI with the signed-in user's information
                                Log.d("TAG", "signInWithEmail:success");
                                FirebaseUser user = mAuth.getCurrentUser();
                                loginButton.setResultState(RoundButton.ResultState.SUCCESS);
                                startActivity(new Intent(LoginActivity.this,Home.class));
                            } else {
                                // If sign in fails, display a message to the user.
                                loginButton.setResultState(RoundButton.ResultState.FAILURE);
                                Log.w("TAG", "signInWithEmail:failure", task.getException());
                                Toast.makeText(LoginActivity.this, "Authentication failed.",
                                        Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        }
        catch(Exception e){
            Log.e("TAG",e.getMessage());
        }
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                loginButton.revertAnimation();
            }
        },1000);
    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        //updateUI(currentUser);
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}