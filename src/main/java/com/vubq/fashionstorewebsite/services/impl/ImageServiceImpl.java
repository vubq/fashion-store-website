package com.vubq.fashionstorewebsite.services.impl;

import com.vubq.fashionstorewebsite.entities.Image;
import com.vubq.fashionstorewebsite.enums.EImageType;
import com.vubq.fashionstorewebsite.repositories.ImageRepository;
import com.vubq.fashionstorewebsite.services.ImageService;
import com.vubq.fashionstorewebsite.utils.BaseSpecification;
import com.vubq.fashionstorewebsite.utils.SearchCriteria;
import com.vubq.fashionstorewebsite.utils.SearchOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImageServiceImpl implements ImageService {

    @Autowired
    private ImageRepository imageRepository;

    @Override
    public List<Image> getAllByPrivateIdAndType(String privateId, EImageType type) {
        BaseSpecification<Image> specPrivateIdEquality = new BaseSpecification<>(
                SearchCriteria.builder()
                        .keys(new String[]{Image.Fields.privateId})
                        .operation(SearchOperation.EQUALITY)
                        .value(privateId)
                        .build());
        BaseSpecification<Image> specTypeEquality = new BaseSpecification<>(
                SearchCriteria.builder()
                        .keys(new String[]{Image.Fields.type})
                        .operation(SearchOperation.EQUALITY)
                        .value(type)
                        .build());
        return this.imageRepository.findAll(Specification.where(specPrivateIdEquality).and(specTypeEquality));
    }
}
