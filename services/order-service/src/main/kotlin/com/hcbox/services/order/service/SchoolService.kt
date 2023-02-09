package com.hcbox.services.order.service

import com.hcbox.api.dto.PageQueryDto
import com.hcbox.api.dto.SchoolDto
import com.hcbox.services.order.mapper.SchoolMapper
import com.hcbox.services.order.repository.SchoolRepository
import org.slf4j.LoggerFactory
import org.springframework.dao.DuplicateKeyException
import org.springframework.data.domain.Page
import org.springframework.stereotype.Service
import org.webjars.NotFoundException
import reactor.core.publisher.Mono
import reactor.core.scheduler.Schedulers

@Service
class SchoolService(
    private val schoolRepository: SchoolRepository,
    private val mapper: SchoolMapper,
) {
    companion object {
        private val log = LoggerFactory.getLogger(SchoolService::class.java)
    }

    fun create(school: SchoolDto.SchoolUpsertDto): Mono<SchoolDto.SchoolReadDto> {
        return Mono.fromCallable { schoolRepository.save(mapper.toEntity(school)) }
            .map { entity -> mapper.toDto(entity) }
            .subscribeOn(Schedulers.boundedElastic()).log()
    }

    fun findById(id: Long): Mono<SchoolDto.SchoolReadDto> {
        return Mono.fromCallable { schoolRepository.findById(id) }
            .map { entity -> entity.get() }
            .switchIfEmpty(Mono.error(NotFoundException("Entity Not Found. id=$id")))
            .map { entity -> mapper.toDto(entity) }
            .subscribeOn(Schedulers.boundedElastic()).log()
    }

    fun delete(id: Long): Mono<Void> {
        return Mono.fromCallable { schoolRepository.findById(id) }
            .map { entity -> entity.get() }
            .switchIfEmpty(Mono.error(NotFoundException("Entity Not Found. id=$id")))
            .map { entity -> schoolRepository.delete(entity) }
            .subscribeOn(Schedulers.boundedElastic()).log().then()
    }

    fun update(
        id: Long,
        schoolUpsertDto: SchoolDto.SchoolUpsertDto
    ): Mono<SchoolDto.SchoolReadDto> {
        val dto =
            Mono.fromCallable { schoolRepository.findById(id) }
                .map { entity -> entity.get() }
                .switchIfEmpty(Mono.error(NotFoundException("School Entity Not Found. id=$id")))
                .map { entity ->
                    entity.name = schoolUpsertDto.name!!
                    entity.staffName = schoolUpsertDto.staffName
                    entity.phone = schoolUpsertDto.phone
                    schoolRepository.save(entity)
                }
                .onErrorMap { DuplicateKeyException("Duplicated school, $schoolUpsertDto") }
                .map { e -> mapper.toDto(e) }
                .subscribeOn(Schedulers.boundedElastic()).log()
        return dto
    }

    fun retrieve(
        name: String?,
        pageQuery: PageQueryDto
    ): Mono<Page<SchoolDto.SchoolReadDto>> {
        return Mono.fromCallable {
            schoolRepository.findAllByOptions(name, pageQuery.of())
        }
            .map { page -> page }
            .subscribeOn(Schedulers.boundedElastic()).log()
    }
}
