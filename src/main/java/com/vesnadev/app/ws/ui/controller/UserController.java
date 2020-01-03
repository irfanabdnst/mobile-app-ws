package com.vesnadev.app.ws.ui.controller;

import com.vesnadev.app.ws.exceptions.UserServiceException;
import com.vesnadev.app.ws.service.AddressService;
import com.vesnadev.app.ws.service.UserService;
import com.vesnadev.app.ws.shared.dto.AddressDto;
import com.vesnadev.app.ws.shared.dto.UserDto;
import com.vesnadev.app.ws.ui.model.request.UserDetailsRequestModel;
import com.vesnadev.app.ws.ui.model.response.*;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("users")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    AddressService addressService;

    ModelMapper modelMapper = new ModelMapper();

    @GetMapping(path = "/all")
    public List<UserRest> getUsers() {
        List<UserDto> userDtos = userService.getUsers();
        Type listType = new TypeToken<List<UserRest>>() {} .getType();

        return modelMapper.map(userDtos, listType);
    }

    @GetMapping(
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public List<UserRest> getUsers(
            @RequestParam(value = "page", defaultValue = "1") int page,
            @RequestParam(value = "limit", defaultValue = "25") int limit) {
        List<UserDto> userDtos = userService.getUsers(page, limit);
        Type listType = new TypeToken<List<UserRest>>(){}.getType();

        return modelMapper.map(userDtos, listType);
    }

    @GetMapping(
            path = "/{id}",
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public UserRest getUser(@PathVariable String id) {
        UserDto userDto = userService.getUserByUserId(id);

        return modelMapper.map(userDto, UserRest.class);
    }

    @PostMapping(
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public UserRest createUser(@RequestBody UserDetailsRequestModel userDetails) throws UserServiceException {
        if (userDetails.getFirstName().isEmpty() || userDetails.getLastName().isEmpty() || userDetails.getEmail().isEmpty() || userDetails.getPassword().isEmpty())
            throw new UserServiceException(ErrorMessages.MISSING_REQUIRED_FIELD.getErrorMessage());

        UserDto userDto = modelMapper.map(userDetails, UserDto.class);
        UserDto createdUser = userService.createUser(userDto);

        return modelMapper.map(createdUser, UserRest.class);
    }

    @PutMapping(
            path = "/{id}",
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public UserRest updateUser(@PathVariable String id, @RequestBody UserDetailsRequestModel userDetails) throws UserServiceException {
        if (userDetails.getFirstName().isEmpty() || userDetails.getLastName().isEmpty())
            throw new UserServiceException(ErrorMessages.MISSING_REQUIRED_FIELD.getErrorMessage());

        UserDto userDto = modelMapper.map(userDetails, UserDto.class);
        UserDto updatedUser = userService.updateUser(id, userDto);

        return modelMapper.map(updatedUser, UserRest.class);
    }

    @DeleteMapping(
            path = "/{id}",
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public OperationStatusModel deleteUser(@PathVariable String id) {
        OperationStatusModel returnValue = new OperationStatusModel();

        userService.deleteUser(id);

        returnValue.setOperationName(RequestOperationName.DELETE.name());
        returnValue.setOperationResult(RequestOperationStatus.SUCCESS.name());

        return returnValue;
    }

    @GetMapping(
            path = "/{id}/addresses",
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public List<AddressRest> getUserAddresses(@PathVariable String id) {
        List<AddressDto> addressDtos = addressService.getUserAddresses(id);

        Type listType = new TypeToken<List<AddressRest>>(){}.getType();
        List<AddressRest> returnValue = modelMapper.map(addressDtos, listType);

        return returnValue;
    }

}
