package com.webapp.clothes.mapper;

import com.webapp.clothes.dto.ImageDTO;
import com.webapp.clothes.entity.Image;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ImageMapper {
    @Autowired
    private ModelMapper modelMapper;

    public ImageDTO convertToDTO(Image image) {
        ImageDTO imageDTO = modelMapper.map(image, ImageDTO.class);
        return imageDTO;
    }
}
