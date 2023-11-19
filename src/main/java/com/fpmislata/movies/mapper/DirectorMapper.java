package com.fpmislata.movies.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.fpmislata.movies.controller.model.director.DirectorCreateWeb;
import com.fpmislata.movies.controller.model.director.DirectorDetailWeb;
import com.fpmislata.movies.controller.model.director.DirectorListWeb;
import com.fpmislata.movies.controller.model.director.DirectorUpdateWeb;
import com.fpmislata.movies.domain.entity.Director;
import com.fpmislata.movies.dto.DirectorDTO;
import com.fpmislata.movies.persistence.model.DirectorEntity;

@Mapper(componentModel = "spring")
public interface DirectorMapper {

    DirectorMapper mapper = Mappers.getMapper(DirectorMapper.class);

    @Mapping(target = "id", ignore = true)
    DirectorDTO toDirectorDTO(DirectorCreateWeb directorCreateWeb);

    DirectorDTO toDirectorDTO(DirectorUpdateWeb directorUpdateWeb);

    DirectorDetailWeb toDirectorDetailWeb(DirectorDTO directorDTO);

    DirectorListWeb toDirectorListWeb(DirectorDTO directorDTO);

    DirectorEntity toDirectorEntity(DirectorDTO directorDTO);

    @Mapping(target = "id", expression = "java(resultSet.getInt(\"id\"))")
    @Mapping(target = "name", expression = "java(resultSet.getString(\"name\"))")
    @Mapping(target = "birthYear", expression = "java(resultSet.getInt(\"birthYear\"))")
    @Mapping(target = "deathYear", expression = "java((resultSet.getObject(\"deathYear\") != null)? resultSet.getInt(\"deathYear\") : null)")
    DirectorEntity toDirectorEntity(ResultSet resultSet) throws SQLException;

    DirectorDTO toDirectorDTO(DirectorEntity directorEntity);

    Director toDirector(DirectorDTO directorDTO);

    DirectorDTO toDirectorDTO(Director director);
}