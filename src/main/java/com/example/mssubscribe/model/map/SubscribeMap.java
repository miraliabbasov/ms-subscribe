package com.example.mssubscribe.model.map;

import com.example.mssubscribe.dao.entity.SubscribeEntity;
import com.example.mssubscribe.model.dto.SubscribeDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.Optional;

@Mapper
public abstract class SubscribeMap {

     public static final SubscribeMap INSTANCE = Mappers.getMapper(SubscribeMap.class);

    public abstract SubscribeEntity dtoToEntity (SubscribeDto dto);

    public abstract SubscribeDto entityToDto (SubscribeEntity entity);

}
