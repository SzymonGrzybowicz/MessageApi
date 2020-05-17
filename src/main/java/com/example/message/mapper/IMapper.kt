package com.example.message.mapper

interface IMapper<Domain, Dto> {

    fun mapToDomain(dto: Dto): Domain
    fun mapToDto(domain: Domain): Dto

    fun mapToDomainList(dtoList: List<Dto>): List<Domain> {
        return dtoList.map { dto: Dto -> mapToDomain(dto) }
    }

    fun mapToDtoList(domainList: List<Domain>): List<Dto> {
        return domainList.map { domain: Domain -> mapToDto(domain) }
    }
}