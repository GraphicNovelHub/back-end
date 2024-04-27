package pe.programadoredeslavadoras.ficuniverse.profile.domain.service;

import pe.programadoredeslavadoras.ficuniverse.profile.domain.model.entity.Profile;

import java.util.List;

public interface ProfileService {
    Profile createProfile(Profile profile);
    Profile getProfileById(Integer profile_id);
    Profile update(Integer id, Profile profile);

    boolean deleteProfile(Integer profile_id);

    List<Profile> getAllProfiles();

    Profile findByUserName(String username);

}
