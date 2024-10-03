package ru.mirea.galiullinas.musicshop.presentation;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.List;

import ru.mirea.galiullinas.musicshop.R;
import ru.mirea.galiullinas.musicshop.data.repository.AlbumRepositoryImpl;
import ru.mirea.galiullinas.musicshop.data.repository.UserRepositoryImpl;
import ru.mirea.galiullinas.musicshop.databinding.ActivityMainBinding;
import ru.mirea.galiullinas.musicshop.domain.models.Album;
import ru.mirea.galiullinas.musicshop.domain.repository.AlbumRepository;
import ru.mirea.galiullinas.musicshop.domain.repository.UserRepository;
import ru.mirea.galiullinas.musicshop.domain.usecases.GetAllAlbumsUseCase;
import ru.mirea.galiullinas.musicshop.domain.usecases.SignInUseCase;
import ru.mirea.galiullinas.musicshop.domain.usecases.SignUpUseCase;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        EditText nameInput = binding.textInputName;
        EditText emailInput = binding.textInputEmail;
        EditText passwordInput = binding.textInputPassword;
        Button signInBtn = binding.buttonSignIn;
        Button signUpBtn = binding.buttonSignUp;
        TextView signInResult = binding.textViewSignInRes;
        TextView signUpResult = binding.textViewSignUpRes;
        Button getAllAlbumsBtn = binding.buttonGetAllAlbums;
        TextView allAlbumsResult = binding.textViewAllAlbums;

        signInBtn.setOnClickListener(view -> {
            UserRepository userRepository = new UserRepositoryImpl();
           boolean result = new SignInUseCase(userRepository).execute(emailInput.getText().toString(),
                   passwordInput.getText().toString());
            signInResult.setText(result? "Sign in successful" : "Sign in failed");
        });

        signUpBtn.setOnClickListener(view -> {
            UserRepository userRepository = new UserRepositoryImpl();
            boolean result = new SignUpUseCase(userRepository).execute(nameInput.getText().toString(),
                    emailInput.getText().toString(), passwordInput.getText().toString());
            signUpResult.setText(result? "Sign up successful" : "Sign up failed");
        });

        getAllAlbumsBtn.setOnClickListener(view -> {
            AlbumRepository albumRepository = new AlbumRepositoryImpl();
            List<Album> albums = new GetAllAlbumsUseCase(albumRepository).execute();
            allAlbumsResult.setText("");
            for (Album album : albums) {
                String albumString = album.toString();
                allAlbumsResult.append(albumString);
            }
        });
    }


}