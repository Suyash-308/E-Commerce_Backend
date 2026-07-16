package com.suyash.ecommerce_backend.repository;

import com.suyash.ecommerce_backend.entity.Address;
import com.suyash.ecommerce_backend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AddressRepository extends JpaRepository<Address,Long> {
    List<Address> findByUser(User user);
}
