package com.vubq.fashionstorewebsite.controllers;

import com.vubq.fashionstorewebsite.dtos.CategoryDto;
import com.vubq.fashionstorewebsite.dtos.CreatedByDto;
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
import org.springframework.web.bind.annotation.*;

import java.util.Date;
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

    @GetMapping
    public List<Category> getAll() {
        return this.categoryService.getAll();
    }

    @GetMapping("{id}")
    public Response getById(@PathVariable String id) {
        return Response.build().ok().data(this.categoryService.findById(id));
    }

    @PostMapping()
    public Response create(@RequestBody CategoryDto categoryDto) {
        Category category = this.categoryService.save(Category.builder()
                .name(categoryDto.getName())
                .description(categoryDto.getDescription())
                .status(categoryDto.getStatus())
//                .createdBy(categoryDto.getCreatedBy().getId())
                .build());
        return Response.build().ok().data(category);
    }

//    @PutMapping()
//    public Response update(@RequestBody CategoryDTO categoryDTO) {
//        Category category = this.categoryService.save(Category.builder()
//                .id(categoryDTO.getId())
//                .name(categoryDTO.getName())
//                .description(categoryDTO.getDescription())
//                .createdAt(categoryDTO.getCreatedAt())
//                .status(categoryDTO.getStatus())
//                .build());
//        return Response.build().ok().data(category);
//    }

    @PostMapping("/get-all")
    public DataTableResponse getAll(DataTableRequest request, @RequestBody List<EStatus> statusList) {
        Page<Category> result = this.categoryService.getAll(request, statusList);
        return DataTableResponse.build()
                .ok()
                .totalRows(result.getTotalElements())
                .items(result.get().toList().stream().map(category -> {
                    CategoryDto categoryDto = CategoryDto.toDto(category);
                    User user = this.userService.findById(category.getCreatedBy()).orElse(null);
                    CreatedByDto createdByDto = CreatedByDto.builder()
                            .id(user != null ? user.getId() : null)
                            .name(user != null ? user.getFirstName() + " " + user.getLastName() : null)
                            .build();
                    categoryDto.setCreatedBy(createdByDto);
                    return categoryDto;
                }).collect(Collectors.toList()));
    }
}