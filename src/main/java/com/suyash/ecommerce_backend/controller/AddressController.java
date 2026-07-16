package com.suyash.ecommerce_backend.controller;

import com.suyash.ecommerce_backend.entity.Address;
import com.suyash.ecommerce_backend.entity.User;
import com.suyash.ecommerce_backend.service.AddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/address")
@RequiredArgsConstructor
public class AddressController {
    private final AddressService addressService;


    @PostMapping
    public Address addAddress(@RequestBody Address address) {
        return addressService.addAddress(address);
    }

    @GetMapping
    public List<Address> getAllAddresses() {

        return addressService.getAllAddresses();
    }

    @GetMapping("/{id}")
    public Address getAddressById(@PathVariable Long id) {

        return addressService.getAddressById(id);
    }

    @PutMapping("/{id}")
    public Address updateAddress(@PathVariable Long id,
                                 @RequestBody Address address) {

        return addressService.updateAddress(id, address);
    }

    @DeleteMapping("/{id}")
    public String deleteAddress(@PathVariable Long id) {

        addressService.deleteAddress(id);
        return "Address Deleted Successfully";
    }
}
