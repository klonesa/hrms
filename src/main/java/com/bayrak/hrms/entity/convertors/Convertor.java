package com.bayrak.hrms.entity.convertors;

public interface Convertor<Entity,Dto> {

    Dto convertEntityToDto(Entity entity);

    Entity convertDtoToEntity(Dto dto);

}
