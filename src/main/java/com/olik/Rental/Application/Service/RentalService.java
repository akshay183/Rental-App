package com.olik.Rental.Application.Service;

import com.olik.Rental.Application.Dao.Product;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface RentalService {
    Boolean createRentalBooking(Integer productId, Integer duration, String date);

    ResponseEntity<List<Product>> getAvailableProducts(String date, Integer duration);
}
