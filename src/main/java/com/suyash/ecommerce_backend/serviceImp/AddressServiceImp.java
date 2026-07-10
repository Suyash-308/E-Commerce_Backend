package com.suyash.ecommerce_backend.serviceImp;

import com.suyash.ecommerce_backend.entity.Address;
import com.suyash.ecommerce_backend.entity.User;
import com.suyash.ecommerce_backend.repository.AddressRepository;
import com.suyash.ecommerce_backend.repository.UserRepository;
import com.suyash.ecommerce_backend.service.AddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AddressServiceImp implements AddressService {


    private final AddressRepository addressRepository;
    private final UserRepository userRepository;


    @Override
    public Address addAddress(Long userId, Address address) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User Not Found"));

        address.setUser(user);

        return addressRepository.save(address);
    }

    @Override
    public List<Address> getAllAddresses() {
        return addressRepository.findAll();
    }

    @Override
    public Address getAddressById(Long id) {
        return addressRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Address Not Found"));
    }

    @Override
    public Address updateAddress(Long id, Address address) {

        Address existing = addressRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Address Not Found"));

        existing.setFullName(address.getFullName());
        existing.setPhone(address.getPhone());
        existing.setAddressLine(address.getAddressLine());
        existing.setCity(address.getCity());
        existing.setState(address.getState());
        existing.setCountry(address.getCountry());
        existing.setPincode(address.getPincode());

        return addressRepository.save(existing);
    }

    @Override
    public void deleteAddress(Long id) {
        addressRepository.deleteById(id);
    }
}
