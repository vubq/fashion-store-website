package com.vubq.fashionstorewebsite.controllers;

import com.vubq.fashionstorewebsite.entities.Category;
import com.vubq.fashionstorewebsite.payloads.DataTableRequest;
import com.vubq.fashionstorewebsite.payloads.DataTableResponse;
import com.vubq.fashionstorewebsite.payloads.Response;
import com.vubq.fashionstorewebsite.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/webapi/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public List<Category> getAll() {
        return this.categoryService.getAll();
    }

    @GetMapping("{id}")
    public Response getById(@PathVariable String id) {
        return Response.build().ok().data(this.categoryService.findById(id));
    }

//    @PostMapping()
//    public Response create(@RequestBody CategoryDTO categoryDTO) {
//        Category category = this.categoryService.save(Category.builder()
//                .name(categoryDTO.getName())
//                .description(categoryDTO.getDescription())
//                .createdAt(new Date())
//                .status(categoryDTO.getStatus())
//                .build());
//        return Response.build().ok().data(category);
//    }

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

    @GetMapping("/get-all")
    public DataTableResponse getAll(DataTableRequest request, @RequestParam(value = "status") String status) {
        Page<Category> result = this.categoryService.getAll(request, status);
        return DataTableResponse.build()
                .ok()
                .totalRows(result.getTotalElements())
                .items(result.get().toList());
    }
}