package ru.mirea.galiullinas.mireaproject;

import static android.view.View.VISIBLE;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Objects;

import ru.mirea.galiullinas.mireaproject.databinding.ActivityFirebaseAuthBinding;

public class FirebaseAuthActivity extends AppCompatActivity {
    private final String TAG = FirebaseAuthActivity.class.getSimpleName();
    private ActivityFirebaseAuthBinding binding;
    private FirebaseAuth mAuth;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
// Initialization views
        binding = ActivityFirebaseAuthBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        // [START initialize_auth] Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();
        // [END initialize_auth]

        binding.verifyEmailButton.setOnClickListener(v -> sendEmailVerification());
        binding.createAccButton.setOnClickListener(v -> createAccount(
                binding.emailField.getText().toString(),
                binding.passwordField.getText().toString()));
        binding.signInButton.setOnClickListener(v -> signIn(
                binding.emailField.getText().toString(),
                binding.passwordField.getText().toString()
        ));
        binding.signOutButton.setOnClickListener(v -> signOut());
    }
    // [START on_start_check_user]
    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        updateUI(currentUser);
    }
    // [END on_start_check_user]
    private void updateUI(FirebaseUser user) {
        if (user != null) {
            binding.statusTextView.setText(getString(R.string.emailpassword_status_fmt,
                    user.getEmail(), user.isEmailVerified()));
            binding.detailTextView.setText(getString(R.string.firebase_status_fmt, user.getUid()));
            binding.authTable.setVisibility(View.GONE);
            binding.signedTable.setVisibility(View.VISIBLE);
            binding.verifyEmailButton.setEnabled(!user.isEmailVerified());
            startMainActivity();
        } else {
            binding.statusTextView.setText(R.string.signed_out);
            binding.detailTextView.setText(null);
            binding.authTable.setVisibility(VISIBLE);
            binding.signedTable.setVisibility(View.GONE);
        }
    }

    private void createAccount(String email, String password) {
        Log.d(TAG, "createAccount:" + email);
//        if (!validateForm()) {
//            return;
//        }
// [START create_user_with_email]
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
// Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "createUserWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            updateUI(user);
                        } else {
// If sign in fails, display a message to the user.
                            Log.w(TAG, "createUserWithEmail:failure",
                                    task.getException());
                            Toast.makeText(FirebaseAuthActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            updateUI(null);
                        }
                    }
                });
// [END create_user_with_email]
    }
    private void signIn(String email, String password) {
        Log.d(TAG, "signIn:" + email);
        // [START sign_in_with_email]
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user'sinformation
                            Log.d(TAG, "signInWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            updateUI(user);
                            startMainActivity();
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithEmail:failure", task.getException());
                            Toast.makeText(FirebaseAuthActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            updateUI(null);
                        }
                        // [START_EXCLUDE]
                        if (!task.isSuccessful()) {
                            binding.statusTextView.setText(R.string.auth_failed);
                        }
// [END_EXCLUDE]
                    }
                });
        // [END sign_in_with_email]
    }

    private void signOut() {
        mAuth.signOut();
        updateUI(null);
    }

    private void sendEmailVerification() {
        // Disable button
        binding.verifyEmailButton.setEnabled(false);
        // Send verification email
        // [START send_email_verification]
        final FirebaseUser user = mAuth.getCurrentUser();
        Objects.requireNonNull(user).sendEmailVerification()
                .addOnCompleteListener(this, new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        // [START_EXCLUDE]
                        // Re-enable button
                        binding.verifyEmailButton.setEnabled(true);
                        if (task.isSuccessful()) {
                            Toast.makeText(FirebaseAuthActivity.this,
                                    "Verification email sent to " + user.getEmail(),
                                    Toast.LENGTH_SHORT).show();
                        } else {
                            Log.e(TAG, "sendEmailVerification", task.getException());
                            Toast.makeText(FirebaseAuthActivity.this,
                                    "Failed to send verification email.",
                                    Toast.LENGTH_SHORT).show();
                        }
// [END_EXCLUDE]
                    }
                });
        // [END send_email_verification]
    }
    private void startMainActivity() {
        Intent intent = new Intent(FirebaseAuthActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

}