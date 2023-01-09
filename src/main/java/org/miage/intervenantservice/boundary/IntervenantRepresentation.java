package org.miage.intervenantservice.boundary;

import java.net.URI;
import java.util.Optional;
import java.util.UUID;

import javax.transaction.Transactional;

import org.miage.intervenantservice.control.IntervenantAssembler;
import org.miage.intervenantservice.entity.Intervenant;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@Controller
@ResponseBody
@RequestMapping(value = "/intervenants", produces = MediaType.APPLICATION_JSON_VALUE)
public class IntervenantRepresentation {

    private final IntervenantResource ir;
    private final IntervenantAssembler ia;
    
    public IntervenantRepresentation(IntervenantResource ir, IntervenantAssembler ia) {
        this.ir = ir;
        this.ia = ia;
    }

    // GET all
    @GetMapping
    public ResponseEntity<?> getAllIntervenants() {
        return ResponseEntity.ok(ia.toCollectionModel(ir.findAll()));
    }

    // GET one
    @GetMapping(value = "/{intervenantId}")
    public ResponseEntity<?> getIntervenantById(@PathVariable("intervenantId") String id) {
        return Optional.of(ir.findById(id))
                .filter(Optional::isPresent)
                .map(i -> ResponseEntity.ok(ia.toModel(i.get())))
                .orElse(ResponseEntity.notFound().build());
    }

    // POST
    @PostMapping
    @Transactional
    public ResponseEntity<?> save(@RequestBody Intervenant intervenant) {
        Intervenant toSave = new Intervenant(UUID.randomUUID().toString(),
                intervenant.getNom(),
                intervenant.getPrenom(),
                intervenant.getCodepostal(),
                intervenant.getCommune());
        Intervenant saved = ir.save(toSave);
        URI location = linkTo(IntervenantRepresentation.class).slash(saved.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    // DELETE
    @DeleteMapping(value = "/{intervenantId}")
    @Transactional
    public ResponseEntity<?> delete(@PathVariable("intervenantId") String id) {
        Optional<Intervenant> intervenant = ir.findById(id);
        intervenant.ifPresent(ir::delete);
        return ResponseEntity.noContent().build();
    }

    // PUT
    @PutMapping(value = "/{intervenantId}")
    @Transactional
    public ResponseEntity<?> update(@PathVariable("intervenantId") String id,
            @RequestBody Intervenant newIntervenant) {
        if (!ir.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        Intervenant toSave = new Intervenant(UUID.randomUUID().toString(),
                newIntervenant.getNom(),
                newIntervenant.getPrenom(),
                newIntervenant.getCodepostal(),
                newIntervenant.getCommune());
        toSave.setId(id);
        ir.save(toSave);
        return ResponseEntity.ok().build();
    }

    //PATCH


}
