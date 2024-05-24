package pe.programadoredeslavadoras.ficuniverse.store.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pe.programadoredeslavadoras.ficuniverse.store.domain.model.entitie.Fanfic;
import pe.programadoredeslavadoras.ficuniverse.store.domain.persistence.FanficRepository;
import pe.programadoredeslavadoras.ficuniverse.shared.exceptions.FetchIdNotFoundException;
import pe.programadoredeslavadoras.ficuniverse.shared.exceptions.FetchNotFoundException;

import javax.xml.validation.Validator;
import java.util.List;
import java.util.Optional;
@ExtendWith(MockitoExtension.class)
public class FanficServiceImplTest {
    @Mock
    private FanficRepository fanficRepository;
    @Mock
    private Validator validator;
    @InjectMocks
    private FanficServiceImpl fanficService;
    @Test
    public void fetchById_ExistingId_ReturnsFanfic() {
        Fanfic fanfic = new Fanfic();
        Integer id = 1;
        when(fanficRepository.findById(id)).thenReturn(Optional.of(fanfic));

        Fanfic fetchedFanfic = fanficService.fetchById(id);

        assertEquals(fanfic, fetchedFanfic);
    }
    @Test
    public void fetchById_NonExistingId_ThrowsException() {
        Integer id = 1;
        when(fanficRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(FetchIdNotFoundException.class, () -> fanficService.fetchById(id));
    }
    @Test
    public void deleteById_NonExistingId_ThrowsException() {
        Integer id = 1;
        when(fanficRepository.existsById(id)).thenReturn(false);

        assertThrows(FetchIdNotFoundException.class, () -> fanficService.deleteById(id));
    }

    @Test
    public void fetchAll_ReturnsListOfFanfic() {
        List<Fanfic> fanfics = List.of(new Fanfic());
        when(fanficRepository.findAll()).thenReturn(fanfics);

        List<Fanfic> fetchedFanfics = fanficService.fetchAll();

        assertEquals(fanfics, fetchedFanfics);
    }

    @Test
    public void findByTitle_ExistingTitle_ReturnsFanfic() {
        String title = "Some Title";
        Fanfic fanfic = new Fanfic();
        when(fanficRepository.sqlFanficByTitle(title)).thenReturn(Optional.of(fanfic));

        Fanfic foundFanfic = fanficService.findByTitle(title);

        assertEquals(fanfic, foundFanfic);
    }

    @Test
    public void findByTitle_NonExistingTitle_ThrowsException() {
        String title = "Nonexistent Title";
        when(fanficRepository.sqlFanficByTitle(title)).thenReturn(Optional.empty());

        assertThrows(FetchNotFoundException.class, () -> fanficService.findByTitle(title));
    }
}