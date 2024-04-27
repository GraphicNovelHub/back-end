package pe.programadoredeslavadoras.ficuniverse.creation.domain.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pe.programadoredeslavadoras.ficuniverse.creation.domain.model.entities.Chapter;

import java.util.List;
import java.util.Optional;

@Repository
public interface ChapterRepository extends JpaRepository<Chapter, Integer> {
    Optional<Chapter> findByTitle(String title);

    @Query(value = "SELECT * FROM chapters WHERE title = :title", nativeQuery = true)
    Optional<Chapter> sqlChapterByTitle(String title);


    List<Chapter> findByChapterOrderBetween(Integer orderInit, Integer orderEnd);
    @Query(value = "SELECT * FROM chapters WHERE chapter_order BETWEEN :orderInit AND :orderEnd", nativeQuery = true)
    List<Chapter> sqlChaptersBetweenChapterOrder(Integer orderInit, Integer orderEnd);

    List<Chapter> findChaptersByFanficId(Integer fanficId);

    @Query(value = "SELECT * from chapters WHERE fanfic_id = :fanficId", nativeQuery = true)
    List<Chapter> sqlChaptersByFanficId(Integer fanficId);
}
