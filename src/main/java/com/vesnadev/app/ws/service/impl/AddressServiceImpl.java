package com.vesnadev.app.ws.service.impl;

import com.vesnadev.app.ws.exceptions.UserServiceException;
import com.vesnadev.app.ws.io.entity.AddressEntity;
import com.vesnadev.app.ws.io.entity.UserEntity;
import com.vesnadev.app.ws.io.repositories.AddressRepository;
import com.vesnadev.app.ws.io.repositories.UserRepository;
import com.vesnadev.app.ws.service.AddressService;
import com.vesnadev.app.ws.shared.dto.AddressDto;
import com.vesnadev.app.ws.ui.model.response.ErrorMessages;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.List;

@Service
public class AddressServiceImpl implements AddressService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    AddressRepository addressRepository;

    ModelMapper modelMapper = new ModelMapper();

    @Override
    public List<AddressDto> getUserAddresses(String userId) {
        UserEntity userEntity = userRepository.findByUserId(userId);

        if (userEntity == null)
            throw new UserServiceException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());

        List<AddressEntity> addressEntity = addressRepository.findAllByUserDetails(userEntity);

        Type listType = new TypeToken<List<AddressDto>>(){}.getType();
        List<AddressDto> returnValue = modelMapper.map(addressEntity, listType);

        return returnValue;
    }

}
