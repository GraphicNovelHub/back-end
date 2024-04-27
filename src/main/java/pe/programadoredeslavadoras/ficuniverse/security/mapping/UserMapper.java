package pe.programadoredeslavadoras.ficuniverse.security.mapping;

import org.springframework.beans.factory.annotation.Autowired;
import pe.programadoredeslavadoras.ficuniverse.security.domain.model.User;
import pe.programadoredeslavadoras.ficuniverse.security.resource.CreateUserResource;
import pe.programadoredeslavadoras.ficuniverse.security.resource.UserResource;
import pe.programadoredeslavadoras.ficuniverse.shared.mapping.EnhancedModelMapper;

import java.io.Serializable;

public class UserMapper implements Serializable {
    @Autowired
    EnhancedModelMapper mapper;

    public User toEntity(CreateUserResource resource){return this.mapper.map(resource, User.class);}
    public UserResource toResource(User user){return this.mapper.map(user, UserResource.class);}
}
