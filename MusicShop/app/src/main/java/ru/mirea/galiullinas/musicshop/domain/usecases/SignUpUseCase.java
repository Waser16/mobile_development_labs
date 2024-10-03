package ru.mirea.galiullinas.musicshop.domain.usecases;

import ru.mirea.galiullinas.musicshop.domain.repository.UserRepository;

public class SignUpUseCase {
    private UserRepository userRepository;

    public SignUpUseCase(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public boolean execute(String name, String email, String password) {
        return userRepository.signUp(name, email, password);
    }
}
