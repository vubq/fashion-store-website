package com.vubq.fashionstorewebsite.services;

import com.vubq.fashionstorewebsite.entities.Image;
import com.vubq.fashionstorewebsite.enums.EImageType;

import java.util.List;

public interface ImageService {

    List<Image> getAllByPrivateIdAndType(String privateId, EImageType type);
}
