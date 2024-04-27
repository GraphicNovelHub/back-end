package pe.programadoredeslavadoras.ficuniverse.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.programadoredeslavadoras.ficuniverse.security.domain.model.User;
import pe.programadoredeslavadoras.ficuniverse.security.domain.persistence.UserRepository;
import pe.programadoredeslavadoras.ficuniverse.security.domain.service.UserService;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Override
    public User createUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User getUserById(Integer user_id) {
        return userRepository.findById(user_id).orElse(null);
    }

    @Override
    public User updateUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public void deleteUser(Integer user_id) {
        userRepository.deleteById(user_id);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}
