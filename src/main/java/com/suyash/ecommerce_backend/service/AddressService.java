package com.suyash.ecommerce_backend.service;

import com.suyash.ecommerce_backend.entity.Address;

import java.util.List;

public interface AddressService {

    Address addAddress(Address address);

    List<Address> getAllAddresses();

    Address getAddressById(Long id);

    Address updateAddress(Long id, Address address);

    void deleteAddress(Long id);

}
