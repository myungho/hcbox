package com.hcbox.services.product.controller.operation

import com.hcbox.api.dto.PageQueryDto
import com.hcbox.api.dto.SchoolDto
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.enums.ParameterIn
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.data.domain.Page
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Mono
import javax.validation.Valid

@RequestMapping("/schools")
@Tag(name = "Schools", description = "Schools")
interface SchoolOperation {
    @PostMapping
    @Operation(summary = "생성")
    @ApiResponses(
        value = [ApiResponse(
            responseCode = "201",
            description = "CREATED",
            content = [Content(schema = Schema(implementation = SchoolDto.SchoolReadDto::class))]
        )]
    )
    fun create(
        @RequestBody schoolUpsertDto: @Valid SchoolDto.SchoolUpsertDto
    ): Mono<SchoolDto.SchoolReadDto>

    @GetMapping("/{id}")
    @Operation(summary = "조회")
    @ApiResponses(value = [ApiResponse(responseCode = "200", description = "Ok")])
    fun read(
        @Parameter(
            description = "id",
            `in` = ParameterIn.PATH,
            required = true
        ) @PathVariable id: Long,
    ): Mono<SchoolDto.SchoolReadDto>

    @PutMapping("/{id}")
    @Operation(summary = "업데이트")
    @ApiResponses(value = [ApiResponse(responseCode = "200", description = "Ok")])
    fun update(
        @Parameter(
            description = "id",
            `in` = ParameterIn.PATH,
            required = true
        ) @PathVariable id: Long,
        @RequestBody schoolUpsertDto: @Valid SchoolDto.SchoolUpsertDto
    ): Mono<SchoolDto.SchoolReadDto>

    @DeleteMapping("/{id}")
    @Operation(summary = "삭제")
    @ApiResponses(value = [ApiResponse(responseCode = "204", description = "No Content")])
    fun delete(
        @Parameter(
            description = "id",
            `in` = ParameterIn.PATH,
            required = true
        ) @PathVariable id: Long,
    ): Mono<Void>

    @GetMapping("/retrieve")
    @Operation(summary = "페이징 조회")
    @ApiResponses(value = [ApiResponse(responseCode = "200", description = "Ok")])
    fun retrieve(
        @Parameter(description = "이름") @RequestParam(
            value = "name",
            required = false
        ) name: String?,
        @Parameter(
            schema = Schema(implementation = PageQueryDto::class),
            `in` = ParameterIn.QUERY
        ) pageQuery: PageQueryDto
    ): Mono<Page<SchoolDto.SchoolReadDto>>
}
