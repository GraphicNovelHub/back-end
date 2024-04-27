package pe.programadoredeslavadoras.ficuniverse.security.domain.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.programadoredeslavadoras.ficuniverse.security.domain.model.User;

import java.util.List;

public interface UserRepository extends JpaRepository<User,Integer> {
    User findByEmailAndPassword(String user_email, String user_password);
    boolean existsById(Integer id);
    boolean existsByEmail(String user_email);


    User findByEmail(String email);

    List<User> findAll();
}
