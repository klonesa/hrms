package com.bayrak.hrms.dto.convertor;

public interface Convertor<Entity,Dto> {

    Dto EntityToDto(Entity entity);

    Entity DtoToEntity(Dto dto);

}
