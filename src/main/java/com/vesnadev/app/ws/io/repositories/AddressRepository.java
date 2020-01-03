package com.vesnadev.app.ws.io.repositories;

import com.vesnadev.app.ws.io.entity.AddressEntity;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends PagingAndSortingRepository<AddressEntity, Long> {

    AddressEntity findByAddressId(String id);

}
