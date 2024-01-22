package com.fpmislata.movies.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

//import com.fpmislata.movies.controller.model.actor.ActorListWeb;
import com.fpmislata.movies.domain.entity.Actor;
import com.fpmislata.movies.persistence.model.ActorEntity;

//import java.sql.ResultSet;
//import java.sql.SQLException;

@Mapper(componentModel = "spring")
public interface ActorMapper {

    ActorMapper mapper = Mappers.getMapper(ActorMapper.class);
/*
    @Mapping(target = "id", expression = "java(resultSet.getInt(\"id\"))")
    @Mapping(target = "name", expression = "java(resultSet.getString(\"name\"))")
    @Mapping(target = "birthYear", expression = "java(resultSet.getInt(\"birthYear\"))")
    @Mapping(target = "deathYear", expression = "java((resultSet.getObject(\"deathYear\") != null)? resultSet.getInt(\"deathYear\") : null)")
    ActorEntity toActorEntity(ResultSet resultSet) throws SQLException;

    ActorListWeb toActorListWeb(Actor actor);

    ActorDetailWeb toActorDetailWeb(Actor actor);

    @Mapping(target = "id", ignore = true)
    Actor toActor(ActorCreateWeb actorCreateWeb);

    Actor toActor(ActorUpdateWeb actorUpdateWeb);
 */
    ActorEntity toActorEntity(Actor actor);

    Actor toActor(ActorEntity actorEntity);

    @Mapping(target = "id", source = "actorId")
    @Mapping(target = "name", ignore = true)
    @Mapping(target = "birthYear", ignore = true)
    @Mapping(target = "deathYear", ignore = true)
    Actor toActor(Integer actorId);
}