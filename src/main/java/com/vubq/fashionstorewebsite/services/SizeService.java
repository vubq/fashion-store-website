package com.vubq.fashionstorewebsite.services;

import com.vubq.fashionstorewebsite.entities.Size;

import java.util.List;
import java.util.Optional;

public interface SizeService {

    Optional<Size> findById(String id);
}
