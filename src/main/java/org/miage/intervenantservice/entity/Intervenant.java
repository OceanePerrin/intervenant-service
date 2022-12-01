package org.miage.intervenantservice.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serial;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@AllArgsConstructor
@NoArgsConstructor          // obligatoire si JPA
@Data
public class Intervenant implements Serializable {
    
    @Serial
    private static final long serialVersionUID = 98765432345678L;
    @Id
    private String id;
    private String nom;
    private String prenom;
    private String codepostal;
    private String commune;
}
