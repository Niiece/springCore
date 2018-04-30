package trash;

import org.springframework.lang.NonNull;

public interface LoginService {
    void login(@NonNull String login, @NonNull String password);
}
