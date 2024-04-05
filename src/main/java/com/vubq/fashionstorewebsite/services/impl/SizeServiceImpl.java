package com.vubq.fashionstorewebsite.services.impl;

import com.vubq.fashionstorewebsite.entities.Size;
import com.vubq.fashionstorewebsite.repositories.SizeRepository;
import com.vubq.fashionstorewebsite.services.SizeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SizeServiceImpl implements SizeService {

    @Autowired
    private SizeRepository sizeRepository;

    @Override
    public Optional<Size> findById(String id) {
        return this.sizeRepository.findById(id);
    }
}
