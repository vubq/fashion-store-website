package com.vubq.fashionstorewebsite.controllers;

import com.vubq.fashionstorewebsite.dtos.CreatedByDto;
import com.vubq.fashionstorewebsite.dtos.SizeDto;
import com.vubq.fashionstorewebsite.dtos.UpdatedByDto;
import com.vubq.fashionstorewebsite.entities.Size;
import com.vubq.fashionstorewebsite.entities.User;
import com.vubq.fashionstorewebsite.enums.EStatus;
import com.vubq.fashionstorewebsite.payloads.DataTableRequest;
import com.vubq.fashionstorewebsite.payloads.DataTableResponse;
import com.vubq.fashionstorewebsite.payloads.Response;
import com.vubq.fashionstorewebsite.services.SizeService;
import com.vubq.fashionstorewebsite.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/webapi/size")
public class SizeController {

    @Autowired
    private SizeService sizeService;

    @Autowired
    private UserService userService;

    @GetMapping("/{id}")
    public Response getById(@PathVariable String id) {
        return Response.build().ok().data(SizeDto.toDto(this.sizeService.findById(id).get()));
    }

    @PostMapping()
    public Response createOrUpdate(@RequestBody SizeDto sizeDto) {
        Size size = this.sizeService.save(Size.toEntity(sizeDto));
        return Response.build().ok().data(SizeDto.toDto(size));
    }

    @PostMapping("/get-all")
    public DataTableResponse getAll(DataTableRequest request, @RequestBody List<EStatus> statusList) {
        Page<Size> result = this.sizeService.getAll(request, statusList);
        return DataTableResponse.build()
                .ok()
                .totalRows(result.getTotalElements())
                .items(result.get().toList().stream().map(size -> {
                    SizeDto sizeDto = SizeDto.toDto(size);
                    User userCreated = this.userService.findById(size.getCreatedBy()).orElse(null);
                    CreatedByDto createdByDto = CreatedByDto.builder()
                            .id(userCreated != null ? userCreated.getId() : null)
                            .name(userCreated != null ? userCreated.getFirstName() + " " + userCreated.getLastName() : null)
                            .build();
                    sizeDto.setCreatedBy(createdByDto);
                    if (!StringUtils.isEmpty(size.getUpdatedBy())) {
                        User userUpdated = this.userService.findById(size.getUpdatedBy()).orElse(null);
                        UpdatedByDto updatedByDto = UpdatedByDto.builder()
                                .id(userUpdated != null ? userUpdated.getId() : null)
                                .name(userUpdated != null ? userUpdated.getFirstName() + " " + userUpdated.getLastName() : null)
                                .build();
                        sizeDto.setUpdatedBy(updatedByDto);
                    }
                    return sizeDto;
                }).collect(Collectors.toList()));
    }

    @GetMapping("/get-all/{status}")
    public Response getAllByStatus(@PathVariable(value = "status") EStatus status) {
        return Response.build()
                .ok()
                .data(this.sizeService.getAllByStatus(status).stream().map(SizeDto::toDto).collect(Collectors.toList()));
    }
}
