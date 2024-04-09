package com.vubq.fashionstorewebsite.controllers;

import com.vubq.fashionstorewebsite.dtos.BrandDto;
import com.vubq.fashionstorewebsite.dtos.CreatedByDto;
import com.vubq.fashionstorewebsite.dtos.UpdatedByDto;
import com.vubq.fashionstorewebsite.entities.Brand;
import com.vubq.fashionstorewebsite.entities.User;
import com.vubq.fashionstorewebsite.enums.EStatus;
import com.vubq.fashionstorewebsite.payloads.DataTableRequest;
import com.vubq.fashionstorewebsite.payloads.DataTableResponse;
import com.vubq.fashionstorewebsite.payloads.Response;
import com.vubq.fashionstorewebsite.services.BrandService;
import com.vubq.fashionstorewebsite.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/webapi/brand")
public class BrandController {

    @Autowired
    private BrandService brandService;

    @Autowired
    private UserService userService;

    @GetMapping("/{id}")
    public Response getById(@PathVariable String id) {
        return Response.build().ok().data(BrandDto.toDto(this.brandService.findById(id).get()));
    }

    @PostMapping()
    public Response createOrUpdate(@RequestBody BrandDto brandDto) {
        Brand brand = this.brandService.save(Brand.toEntity(brandDto));
        return Response.build().ok().data(BrandDto.toDto(brand));
    }

    @PostMapping("/get-all")
    public DataTableResponse getAll(DataTableRequest request, @RequestBody List<EStatus> statusList) {
        Page<Brand> result = this.brandService.getAll(request, statusList);
        return DataTableResponse.build()
                .ok()
                .totalRows(result.getTotalElements())
                .items(result.get().toList().stream().map(brand -> {
                    BrandDto brandDto = BrandDto.toDto(brand);
                    User userCreated = this.userService.findById(brand.getCreatedBy()).orElse(null);
                    CreatedByDto createdByDto = CreatedByDto.builder()
                            .id(userCreated != null ? userCreated.getId() : null)
                            .name(userCreated != null ? userCreated.getFirstName() + " " + userCreated.getLastName() : null)
                            .build();
                    brandDto.setCreatedBy(createdByDto);
                    if (!StringUtils.isEmpty(brand.getUpdatedBy())) {
                        User userUpdated = this.userService.findById(brand.getUpdatedBy()).orElse(null);
                        UpdatedByDto updatedByDto = UpdatedByDto.builder()
                                .id(userUpdated != null ? userUpdated.getId() : null)
                                .name(userUpdated != null ? userUpdated.getFirstName() + " " + userUpdated.getLastName() : null)
                                .build();
                        brandDto.setUpdatedBy(updatedByDto);
                    }
                    return brandDto;
                }).collect(Collectors.toList()));
    }

    @GetMapping("/get-all/{status}")
    public Response getAllByStatus(@PathVariable(value = "status") EStatus status) {
        return Response.build()
                .ok()
                .data(this.brandService.getAllByStatus(status).stream().map(BrandDto::toDto).collect(Collectors.toList()));
    }
}
