package com.example.message.mapper;

import java.util.List;
import java.util.stream.Collectors;

public interface IMapper<Domain, Dto> {

    Domain mapToDomain(Dto dto);
    Dto mapToDto(Domain domain);

    default List<Domain> mapToDomainList(List<Dto> dtoList){
        return dtoList.stream().map(this::mapToDomain).collect(Collectors.toList());
    }

    default List<Dto> mapToDtoList(List<Domain> domainList){
        return domainList.stream().map(this::mapToDto).collect(Collectors.toList());
    }
}
