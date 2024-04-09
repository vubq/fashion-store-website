package com.vubq.fashionstorewebsite.controllers;

import com.vubq.fashionstorewebsite.dtos.ColorDto;
import com.vubq.fashionstorewebsite.dtos.CreatedByDto;
import com.vubq.fashionstorewebsite.dtos.UpdatedByDto;
import com.vubq.fashionstorewebsite.entities.Color;
import com.vubq.fashionstorewebsite.entities.User;
import com.vubq.fashionstorewebsite.enums.EStatus;
import com.vubq.fashionstorewebsite.payloads.DataTableRequest;
import com.vubq.fashionstorewebsite.payloads.DataTableResponse;
import com.vubq.fashionstorewebsite.payloads.Response;
import com.vubq.fashionstorewebsite.services.ColorService;
import com.vubq.fashionstorewebsite.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/webapi/color")
public class ColorController {

    @Autowired
    private ColorService colorService;

    @Autowired
    private UserService userService;

    @GetMapping("/{id}")
    public Response getById(@PathVariable String id) {
        return Response.build().ok().data(ColorDto.toDto(this.colorService.findById(id).get()));
    }

    @PostMapping()
    public Response createOrUpdate(@RequestBody ColorDto colorDto) {
        Color color = this.colorService.save(Color.toEntity(colorDto));
        return Response.build().ok().data(ColorDto.toDto(color));
    }

    @PostMapping("/get-all")
    public DataTableResponse getAll(DataTableRequest request, @RequestBody List<EStatus> statusList) {
        Page<Color> result = this.colorService.getAll(request, statusList);
        return DataTableResponse.build()
                .ok()
                .totalRows(result.getTotalElements())
                .items(result.get().toList().stream().map(color -> {
                    ColorDto colorDto = ColorDto.toDto(color);
                    User userCreated = this.userService.findById(color.getCreatedBy()).orElse(null);
                    CreatedByDto createdByDto = CreatedByDto.builder()
                            .id(userCreated != null ? userCreated.getId() : null)
                            .name(userCreated != null ? userCreated.getFirstName() + " " + userCreated.getLastName() : null)
                            .build();
                    colorDto.setCreatedBy(createdByDto);
                    if (!StringUtils.isEmpty(color.getUpdatedBy())) {
                        User userUpdated = this.userService.findById(color.getUpdatedBy()).orElse(null);
                        UpdatedByDto updatedByDto = UpdatedByDto.builder()
                                .id(userUpdated != null ? userUpdated.getId() : null)
                                .name(userUpdated != null ? userUpdated.getFirstName() + " " + userUpdated.getLastName() : null)
                                .build();
                        colorDto.setUpdatedBy(updatedByDto);
                    }
                    return colorDto;
                }).collect(Collectors.toList()));
    }

    @GetMapping("/get-all/{status}")
    public Response getAllByStatus(@PathVariable(value = "status") EStatus status) {
        return Response.build()
                .ok()
                .data(this.colorService.getAllByStatus(status).stream().map(ColorDto::toDto).collect(Collectors.toList()));
    }
}
