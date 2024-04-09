package com.vubq.fashionstorewebsite.controllers;

import com.vubq.fashionstorewebsite.dtos.CategoryDto;
import com.vubq.fashionstorewebsite.dtos.CreatedByDto;
import com.vubq.fashionstorewebsite.dtos.UpdatedByDto;
import com.vubq.fashionstorewebsite.entities.Category;
import com.vubq.fashionstorewebsite.entities.User;
import com.vubq.fashionstorewebsite.enums.EStatus;
import com.vubq.fashionstorewebsite.payloads.DataTableRequest;
import com.vubq.fashionstorewebsite.payloads.DataTableResponse;
import com.vubq.fashionstorewebsite.payloads.Response;
import com.vubq.fashionstorewebsite.services.CategoryService;
import com.vubq.fashionstorewebsite.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/webapi/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private UserService userService;

    @GetMapping("/{id}")
    public Response getById(@PathVariable String id) {
        return Response.build().ok().data(CategoryDto.toDto(this.categoryService.findById(id).get()));
    }

    @PostMapping()
    public Response createOrUpdate(@RequestBody CategoryDto categoryDto) {
        Category category = this.categoryService.save(Category.toEntity(categoryDto));
        return Response.build().ok().data(CategoryDto.toDto(category));
    }

    @PostMapping("/get-all")
    public DataTableResponse getAll(DataTableRequest request, @RequestBody List<EStatus> statusList) {
        Page<Category> result = this.categoryService.getAll(request, statusList);
        return DataTableResponse.build()
                .ok()
                .totalRows(result.getTotalElements())
                .items(result.get().toList().stream().map(category -> {
                    CategoryDto categoryDto = CategoryDto.toDto(category);
                    User userCreated = this.userService.findById(category.getCreatedBy()).orElse(null);
                    CreatedByDto createdByDto = CreatedByDto.builder()
                            .id(userCreated != null ? userCreated.getId() : null)
                            .name(userCreated != null ? userCreated.getFirstName() + " " + userCreated.getLastName() : null)
                            .build();
                    categoryDto.setCreatedBy(createdByDto);
                    if (!StringUtils.isEmpty(category.getUpdatedBy())) {
                        User userUpdated = this.userService.findById(category.getUpdatedBy()).orElse(null);
                        UpdatedByDto updatedByDto = UpdatedByDto.builder()
                                .id(userUpdated != null ? userUpdated.getId() : null)
                                .name(userUpdated != null ? userUpdated.getFirstName() + " " + userUpdated.getLastName() : null)
                                .build();
                        categoryDto.setUpdatedBy(updatedByDto);
                    }
                    return categoryDto;
                }).collect(Collectors.toList()));
    }

    @GetMapping("/get-all/{status}")
    public Response getAllByStatus(@PathVariable(value = "status") EStatus status) {
        return Response.build()
                .ok()
                .data(this.categoryService.getAllByStatus(status).stream().map(CategoryDto::toDto).collect(Collectors.toList()));
    }
}