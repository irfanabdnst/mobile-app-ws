package com.vesnadev.app.ws.io.repositories;

import com.vesnadev.app.ws.io.entity.AddressEntity;
import com.vesnadev.app.ws.io.entity.UserEntity;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AddressRepository extends PagingAndSortingRepository<AddressEntity, Long> {

    List<AddressEntity> findByUserDetails(UserEntity userDetails);

}
