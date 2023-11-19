package com.fpmislata.movies.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.fpmislata.movies.controller.model.actor.ActorCreateWeb;
import com.fpmislata.movies.controller.model.actor.ActorDetailWeb;
import com.fpmislata.movies.controller.model.actor.ActorListWeb;
import com.fpmislata.movies.controller.model.actor.ActorUpdateWeb;
import com.fpmislata.movies.domain.entity.Actor;
import com.fpmislata.movies.dto.ActorDTO;
import com.fpmislata.movies.persistence.model.ActorEntity;

import java.sql.ResultSet;
import java.sql.SQLException;

@Mapper(componentModel = "spring")
public interface ActorMapper {

    ActorMapper mapper = Mappers.getMapper(ActorMapper.class);

    @Mapping(target = "id", expression = "java(resultSet.getInt(\"id\"))")
    @Mapping(target = "name", expression = "java(resultSet.getString(\"name\"))")
    @Mapping(target = "birthYear", expression = "java(resultSet.getInt(\"birthYear\"))")
    @Mapping(target = "deathYear", expression = "java((resultSet.getObject(\"deathYear\") != null)? resultSet.getInt(\"deathYear\") : null)")
    ActorEntity toActorEntity(ResultSet resultSet) throws SQLException;

    ActorDTO toActorDTO(ActorEntity actorEntity);
    
    ActorListWeb toActorListWeb(ActorDTO actorDTO);

    ActorDetailWeb toActorDetailWeb(ActorDTO actorDTO);

    Actor toActor(ActorDTO actorDTO);

    @Mapping(target = "id", ignore = true)
    ActorDTO toActorDTO(ActorCreateWeb actorCreateWeb);

    ActorDTO toActorDTO(ActorUpdateWeb actorUpdateWeb);

    ActorDTO toActorDTO(Actor actor);

    ActorEntity toActorEntity(ActorDTO actorDTO);
}