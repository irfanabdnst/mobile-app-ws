package com.vesnadev.app.ws.io.repositories;

import com.vesnadev.app.ws.io.entity.UserEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends PagingAndSortingRepository<UserEntity, Long> {

    UserEntity findByEmail(String email);

    UserEntity findByUserId(String userId);

    @Query(value = "select * from users u where u.email like %:key% or u.first_name like %:key% or u.last_name like %:key%", nativeQuery = true)
    List<UserEntity> searchUser(@Param("key") String key);

}
