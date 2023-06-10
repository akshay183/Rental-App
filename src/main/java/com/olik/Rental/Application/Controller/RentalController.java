package com.olik.Rental.Application.Controller;

import com.olik.Rental.Application.Dao.Product;
import com.olik.Rental.Application.Service.RentalService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/products")
@Log4j2
public class RentalController {

    @Autowired
    RentalService rentalService;


    @GetMapping
    public ResponseEntity<List<Product>> getAvailableProducts(@RequestParam("duration") Integer duration,
                                                              @RequestParam("date")String date) {
        // remains is what i need to give only that products which are available
        return rentalService.getAvailableProducts(date, duration);
    }

    @PostMapping("/booking")
    public ResponseEntity<String> createRentalBooking(@RequestParam("productId") Integer productId,
                                    @RequestParam("duration") Integer duration,
                                    @RequestParam("date")String date) {
        try{

            String result;
            if(rentalService.createRentalBooking(productId, duration, date)){
                result = "Created Successfully";
            }
            else{
                result = "Already Booked";
            }
            log.info("Booking created for Product ID: " + productId + ", Duration: " + duration);
            return new ResponseEntity<>(result,HttpStatus.OK);
        }
        catch (Exception e){

            log.info(e.getMessage());
            return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
