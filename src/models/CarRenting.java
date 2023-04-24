package models;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class CarRenting {
    public static final String SEPARATOR = ",";
    private LocalDate startDate;
    private LocalDate endDate;
    private Car car;
    private double pricePerDay;

    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public CarRenting(LocalDate startDate, LocalDate endDate, Car car) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.car = car;
    }

    public CarRenting(LocalDate startDate, Car car) {
        this.startDate = startDate;
        this.car = car;
    }

    public String toFileFormat() {
        String startDate = this.startDate.format(formatter);
        String endDate = this.endDate != null ? this.endDate.format(formatter) : "";
        return startDate + SEPARATOR + endDate + SEPARATOR + car.getModel();
    }
}
