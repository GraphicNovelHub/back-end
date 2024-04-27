package pe.programadoredeslavadoras.ficuniverse.creation.domain.service;

import pe.programadoredeslavadoras.ficuniverse.creation.domain.model.entities.Chapter;

import java.util.List;
import java.util.Optional;

public interface ChapterService {
    Chapter createChapter(Integer fanficId,Chapter chapter);
    Chapter update(Chapter chapter);

    List<Chapter> fetchAll();

   Chapter fetchById(Integer id);

    Chapter fetchByTitle(String title);

    boolean deleteById(Integer id);
    List<Chapter> fetchChaptersByFanficId(Integer fanficId);

    List<Chapter> fetchByChapterOrderBetween(Integer orderInit, Integer orderEnd);



}