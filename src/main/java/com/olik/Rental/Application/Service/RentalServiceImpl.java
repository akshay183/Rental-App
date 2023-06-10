package com.olik.Rental.Application.Service;

import com.olik.Rental.Application.Dao.BookedProduct;
import com.olik.Rental.Application.Dao.Product;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static com.olik.Rental.Application.Dao.BookedProduct.isDateRangeOverlap;

@Service
public class RentalServiceImpl implements RentalService {
    List<BookedProduct> ProductStatus;
    private final List<Product> availableProducts;

    public RentalServiceImpl() {
        ProductStatus = new ArrayList<>();
        availableProducts = new ArrayList<>();

        availableProducts.add(new Product(1, "Cycle", "cycle.jpg", 10.0));
        availableProducts.add(new Product(2, "Racket", "racket.jpg", 5.0));
        availableProducts.add(new Product(3, "Scooter", "scooter.jpg", 11.0));
        availableProducts.add(new Product(2, "Rocket", "rocket.jpg", 17.0));
    }

    @Override
    public Boolean createRentalBooking(Integer productId, Integer duration, String date){

        try{

            List<LocalDate> localDates = getDate(date, duration);
            LocalDate bookedFrom = localDates.get(0);
            LocalDate bookedTo = localDates.get(1);

            boolean hasOverlap = ProductStatus.stream()
                    .anyMatch(obj -> isDateRangeOverlap(obj.getBookedFrom(),
                            obj.getBookedTo(), bookedFrom, bookedTo));

            if(!hasOverlap){
                addProductStatus(productId, bookedFrom, bookedTo);
                return true;
            }
            return false;
        }
        catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public ResponseEntity<List<Product>> getAvailableProducts(String date, Integer duration) {

        List<LocalDate> localDates = getDate(date, duration);
        LocalDate bookedFrom = localDates.get(0);
        LocalDate bookedTo = localDates.get(1);


        return new ResponseEntity<>(availableProducts, HttpStatus.OK);
    }

    public void addProductStatus(Integer productId,LocalDate bookedFrom, LocalDate bookedTo){
        ProductStatus.add(new BookedProduct(productId, bookedFrom, bookedTo));
    }

    public List<LocalDate> getDate(String date, Integer duration){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        LocalDate bookedFrom = LocalDate.parse(date, formatter);
        LocalDate bookedTo = bookedFrom.plusDays(duration);

        List<LocalDate>localDates = new ArrayList<>();
        localDates.add(bookedFrom);
        localDates.add(bookedTo);

        return localDates;
    }
}