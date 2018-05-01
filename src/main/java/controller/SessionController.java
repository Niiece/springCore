package controller;

import model.User;
import org.springframework.lang.NonNull;

public interface SessionController {
    void login(@NonNull String userName, @NonNull String password);

    void startSession(@NonNull User user);

}
