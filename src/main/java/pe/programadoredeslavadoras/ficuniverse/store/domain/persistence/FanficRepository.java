package pe.programadoredeslavadoras.ficuniverse.store.domain.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pe.programadoredeslavadoras.ficuniverse.store.domain.model.entitie.Fanfic;

import java.util.Optional;

public interface FanficRepository extends JpaRepository<Fanfic, Integer> {
    Optional<Fanfic> findByTitle(String title);

    @Query(value = "SELECT * FROM fanfics WHERE title = :title", nativeQuery = true)
    Optional<Fanfic> sqlFanficByTitle(String title);
}
