package org.miage.intervenantservice.boundary;

import org.miage.intervenantservice.entity.Intervenant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IntervenantResource extends JpaRepository<Intervenant, String> {

    // JPA va fournir les SELECT, INSERT, UPDATE, DELETE
    //List<Intervenant> findByCodepostal(String codepostal);
    
}
