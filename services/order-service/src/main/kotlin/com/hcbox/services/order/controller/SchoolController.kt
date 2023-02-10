package com.hcbox.services.order.controller

import com.hcbox.api.dto.PageQueryDto
import com.hcbox.api.dto.SchoolDto
import com.hcbox.services.order.controller.operation.SchoolOperation
import com.hcbox.services.order.service.SchoolService
import org.springframework.data.domain.Page
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Mono

@RestController
class SchoolController(
    private val schoolService: SchoolService
) : SchoolOperation {

    override fun create(schoolUpsertDto: SchoolDto.SchoolUpsertDto): Mono<SchoolDto.SchoolReadDto> {
        return schoolService.create(schoolUpsertDto);
    }

    override fun read(id: Long): Mono<SchoolDto.SchoolReadDto> {
        val school: Mono<SchoolDto.SchoolReadDto> = schoolService.findById(id);
        return school;
    }

    override fun update(
        id: Long,
        schoolUpsertDto: SchoolDto.SchoolUpsertDto
    ): Mono<SchoolDto.SchoolReadDto> {
        return schoolService.update(id, schoolUpsertDto)
    }

    override fun delete(id: Long): Mono<Void> {
        return schoolService.delete(id)
    }

    override fun retrieve(
        name: String?,
        pageQuery: PageQueryDto
    ): Mono<Page<SchoolDto.SchoolReadDto>> {
        return schoolService.retrieve(name, pageQuery)
    }

    override fun getList(): Mono<List<SchoolDto.SchoolReadDto>> {
        return schoolService.findAll();
    }
}
