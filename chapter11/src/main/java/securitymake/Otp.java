package securitymake;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "otp")
@Getter
@Setter
public class Otp {

    @Id
    private String username;
    private String code;
}
