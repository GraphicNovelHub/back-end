package pe.programadoredeslavadoras.ficuniverse.profile.service;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.programadoredeslavadoras.ficuniverse.profile.domain.model.entity.Profile;
import pe.programadoredeslavadoras.ficuniverse.profile.domain.persistence.ProfileRepository;
import pe.programadoredeslavadoras.ficuniverse.profile.domain.service.ProfileService;
import pe.programadoredeslavadoras.ficuniverse.shared.exception.ResourceValidationException;
import pe.programadoredeslavadoras.ficuniverse.shared.exceptions.FetchIdNotFoundException;
import pe.programadoredeslavadoras.ficuniverse.shared.exceptions.FetchNotFoundException;

import java.util.List;
import java.util.Optional;
import java.util.Set;
@AllArgsConstructor
@Service
public class ProfileServiceImpl implements ProfileService {

    private final ProfileRepository profileRepository;
    private final Validator validator;

    @Transactional
    @Override
    public Profile createProfile(Profile profile) {
        Set<ConstraintViolation<Profile>> violation = validator.validate(profile);
        if(violation.isEmpty()){
            return profileRepository.save(profile);
        }
        throw new ResourceValidationException("Profile", violation);
    }

    @Transactional
    @Override
    public Profile getProfileById(Integer profile_id) {
        return profileRepository.findById(profile_id)
                .orElseThrow(() -> new FetchIdNotFoundException("Profile", profile_id));
    }

    @Transactional
    @Override
    public Profile update(Integer id, Profile profile) {
        if(profileRepository.existsById(id)){
            profile.setId(id);
            profile.setFanfics(getProfileById(id).getFanfics());
            return profileRepository.save(profile);
        }
        throw new ResourceValidationException("Profile", "Profile not found");
    }

    @Transactional
    @Override
    public boolean deleteProfile(Integer profile_id) {
        if(profileRepository.existsById(profile_id)){
            profileRepository.deleteById(profile_id);
            return !profileRepository.existsById(profile_id);
        }
        throw new FetchIdNotFoundException("Profile", profile_id);

    }


    @Override
    public List<Profile> getAllProfiles() {
        return profileRepository.findAll();
    }

    @Override
    public Profile findByUserName(String username) {
        Optional<Profile> optionalProfile = profileRepository.findByUserName(username);
        if(optionalProfile.isPresent()){
            return optionalProfile.get();
        }
        throw new FetchNotFoundException("Profile", "UserName", username);
    }
}
