package pe.programadoredeslavadoras.ficuniverse.fanfic.domain.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pe.programadoredeslavadoras.ficuniverse.creation.domain.model.entities.Chapter;
import pe.programadoredeslavadoras.ficuniverse.fanfic.domain.model.entitie.Fanfic;

import java.util.List;
import java.util.Optional;

public interface FanficRepository extends JpaRepository<Fanfic, Integer> {
    Optional<Fanfic> findByTitle(String title);

    @Query(value = "SELECT * FROM fanfics WHERE title = :title", nativeQuery = true)
    Optional<Fanfic> sqlFanficByTitle(String title);
}
