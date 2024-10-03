package ru.mirea.galiullinas.musicshop.domain.usecases;

import android.text.Editable;

import ru.mirea.galiullinas.musicshop.domain.repository.UserRepository;

public class SignInUseCase {
    private UserRepository userRepository;

    public SignInUseCase(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public boolean execute(String email, String password) {
        return userRepository.signIn(email, password);
    }

}
