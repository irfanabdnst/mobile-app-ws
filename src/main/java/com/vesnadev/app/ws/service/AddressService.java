package com.vesnadev.app.ws.service;

import com.vesnadev.app.ws.shared.dto.AddressDto;

import java.util.List;

public interface AddressService {

    List<AddressDto> getUserAddresses(String userId);

}
