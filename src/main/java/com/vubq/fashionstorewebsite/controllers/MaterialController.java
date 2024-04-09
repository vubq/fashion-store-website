package com.vubq.fashionstorewebsite.controllers;

import com.vubq.fashionstorewebsite.dtos.CreatedByDto;
import com.vubq.fashionstorewebsite.dtos.MaterialDto;
import com.vubq.fashionstorewebsite.dtos.UpdatedByDto;
import com.vubq.fashionstorewebsite.entities.Material;
import com.vubq.fashionstorewebsite.entities.User;
import com.vubq.fashionstorewebsite.enums.EStatus;
import com.vubq.fashionstorewebsite.payloads.DataTableRequest;
import com.vubq.fashionstorewebsite.payloads.DataTableResponse;
import com.vubq.fashionstorewebsite.payloads.Response;
import com.vubq.fashionstorewebsite.services.MaterialService;
import com.vubq.fashionstorewebsite.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/webapi/material")
public class MaterialController {

    @Autowired
    private MaterialService materialService;

    @Autowired
    private UserService userService;

    @GetMapping("/{id}")
    public Response getById(@PathVariable String id) {
        return Response.build().ok().data(MaterialDto.toDto(this.materialService.findById(id).get()));
    }

    @PostMapping()
    public Response createOrUpdate(@RequestBody MaterialDto materialDto) {
        Material material = this.materialService.save(Material.toEntity(materialDto));
        return Response.build().ok().data(MaterialDto.toDto(material));
    }

    @PostMapping("/get-all")
    public DataTableResponse getAll(DataTableRequest request, @RequestBody List<EStatus> statusList) {
        Page<Material> result = this.materialService.getAll(request, statusList);
        return DataTableResponse.build()
                .ok()
                .totalRows(result.getTotalElements())
                .items(result.get().toList().stream().map(material -> {
                    MaterialDto materialDto = MaterialDto.toDto(material);
                    User userCreated = this.userService.findById(material.getCreatedBy()).orElse(null);
                    CreatedByDto createdByDto = CreatedByDto.builder()
                            .id(userCreated != null ? userCreated.getId() : null)
                            .name(userCreated != null ? userCreated.getFirstName() + " " + userCreated.getLastName() : null)
                            .build();
                    materialDto.setCreatedBy(createdByDto);
                    if (!StringUtils.isEmpty(material.getUpdatedBy())) {
                        User userUpdated = this.userService.findById(material.getUpdatedBy()).orElse(null);
                        UpdatedByDto updatedByDto = UpdatedByDto.builder()
                                .id(userUpdated != null ? userUpdated.getId() : null)
                                .name(userUpdated != null ? userUpdated.getFirstName() + " " + userUpdated.getLastName() : null)
                                .build();
                        materialDto.setUpdatedBy(updatedByDto);
                    }
                    return materialDto;
                }).collect(Collectors.toList()));
    }

    @GetMapping("/get-all/{status}")
    public Response getAllByStatus(@PathVariable(value = "status") EStatus status) {
        return Response.build()
                .ok()
                .data(this.materialService.getAllByStatus(status).stream().map(MaterialDto::toDto).collect(Collectors.toList()));
    }
}
