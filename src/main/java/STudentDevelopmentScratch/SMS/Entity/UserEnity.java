package STudentDevelopmentScratch.SMS.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.security.AuthProvider;
import java.util.Collection;
import java.util.List;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
public class UserEnity {

    private Long ID;

    private  String username;
    private  String password;

    private String providerID;

    @Enumerated(EnumType.STRING)
    private AuthProvider providertype;



}
