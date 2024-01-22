package com.fpmislata.movies.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.fpmislata.movies.controller.model.director.DirectorCreateWeb;
import com.fpmislata.movies.controller.model.director.DirectorDetailWeb;
import com.fpmislata.movies.controller.model.director.DirectorListWeb;
import com.fpmislata.movies.controller.model.director.DirectorUpdateWeb;
import com.fpmislata.movies.domain.entity.Director;
import com.fpmislata.movies.persistence.model.DirectorEntity;

@Mapper(componentModel = "spring")
public interface DirectorMapper {

    DirectorMapper mapper = Mappers.getMapper(DirectorMapper.class);

    @Mapping(target = "id", source = "directorId")
    Director toDirector(Integer directorId);

    Director toDirector(DirectorCreateWeb directorCreateWeb);

    Director toDirector(DirectorUpdateWeb directorUpdateWeb);

    DirectorDetailWeb toDirectorDetailWeb(Director director);

    DirectorListWeb toDirectorListWeb(Director director);

    DirectorEntity toDirectorEntity(Director director);
    Director toDirector(DirectorEntity directorEntity);
/*
    @Mapping(target = "id", expression = "java(resultSet.getInt(\"id\"))")
    @Mapping(target = "name", expression = "java(resultSet.getString(\"name\"))")
    @Mapping(target = "birthYear", expression = "java(resultSet.getInt(\"birthYear\"))")
    @Mapping(target = "deathYear", expression = "java((resultSet.getObject(\"deathYear\") != null)? resultSet.getInt(\"deathYear\") : null)")
    DirectorEntity toDirectorEntity(ResultSet resultSet) throws SQLException;
 */
}