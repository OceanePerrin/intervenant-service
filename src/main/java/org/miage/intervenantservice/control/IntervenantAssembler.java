package org.miage.intervenantservice.control;

import java.util.List;
import org.miage.intervenantservice.boundary.IntervenantRepresentation;
import org.miage.intervenantservice.entity.Intervenant;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;
import java.util.stream.StreamSupport;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@Component
public class IntervenantAssembler implements RepresentationModelAssembler<Intervenant, EntityModel<Intervenant>> {

    @Override
    public EntityModel<Intervenant> toModel(Intervenant intervenant) {
        return EntityModel.of(intervenant,
                linkTo(methodOn(IntervenantRepresentation.class)
                    .getIntervenantById(intervenant.getId())).withSelfRel(),
                linkTo(methodOn(IntervenantRepresentation.class)
                    .getAllIntervenants()).withRel("collection"));
    }

    @Override
    public CollectionModel<EntityModel<Intervenant>> toCollectionModel(Iterable<? extends Intervenant> entities) {
        List<EntityModel<Intervenant>> intervenanModel = StreamSupport
            .stream(entities.spliterator(), false)
            .map(this::toModel)
            .toList();

        return CollectionModel.of(intervenanModel, linkTo(methodOn(IntervenantRepresentation.class)
            .getAllIntervenants()).withSelfRel());
    }
}