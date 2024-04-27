package pe.programadoredeslavadoras.ficuniverse.security.domain.service;

import pe.programadoredeslavadoras.ficuniverse.security.domain.model.User;

import java.util.List;

public interface UserService {
    public abstract User createUser(User user);
    public abstract User getUserById(Integer user_id);
    public abstract User updateUser(User user);
    public abstract void deleteUser(Integer user_id);

    public abstract List<User> getAllUsers();
}
