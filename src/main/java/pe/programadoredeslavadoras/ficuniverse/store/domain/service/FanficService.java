package pe.programadoredeslavadoras.ficuniverse.store.domain.service;

import pe.programadoredeslavadoras.ficuniverse.store.domain.model.entitie.Fanfic;

import java.util.List;

public interface FanficService {
    Fanfic save(Fanfic fanfic);
    Fanfic update(Fanfic fanfic);

    List<Fanfic> fetchAll();

    Fanfic fetchById(Integer id);

    Fanfic findByTitle(String title);

    boolean deleteById(Integer id);

}
