package com.olik.Rental.Application.Dao;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
public class BookedProduct {

    Integer id;
    LocalDate bookedFrom;
    LocalDate bookedTo;

    public BookedProduct(Integer id, LocalDate bookedFrom, LocalDate bookedTo){

        this.id = id;
        this.bookedTo = bookedFrom;
        this.bookedFrom = bookedFrom;
    }

    public static boolean isDateRangeOverlap(LocalDate fromDate, LocalDate toDate,
                                             LocalDate currentFromDate, LocalDate currentToDate) {
        return !(toDate.isBefore(currentFromDate) || fromDate.isAfter(currentToDate));
    }
}
