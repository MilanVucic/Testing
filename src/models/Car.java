package models;

public class Car {
    private String maker;
    private String model;
    private int year;
    private double pricePerDay;
    private boolean rented;

    public Car(String maker, String model, int year, double pricePerDay) {
        this(maker, model, year, pricePerDay, false);
    }

    public Car(String maker, String model, int year, double pricePerDay, boolean rented) {
        if (year > 2023) {
            throw new IllegalArgumentException("Year cannot be in the future.");
        }
        if (pricePerDay < 0) {
            throw new IllegalArgumentException("Rent cannot be negative.");
        }
        this.maker = maker;
        this.model = model;
        this.year = year;
        this.pricePerDay = pricePerDay;
        this.rented = rented;
    }

    public Car(String line, String SEPARATOR) {
        String[] parts = line.split(SEPARATOR);
        this.maker = parts[0];
        this.model = parts[1];
        this.year = Integer.parseInt(parts[2]);
        this.pricePerDay = Double.parseDouble(parts[3]);
        this.rented = Boolean.parseBoolean(parts[4]);
    }

    public void setRented(boolean rented) {
        this.rented = rented;
    }

    public boolean isRented() {
        return rented;
    }

    public String getMaker() {
        return maker;
    }

    public String getModel() {
        return model;
    }

    public int getYear() {
        return year;
    }

    public double getPricePerDay() {
        return pricePerDay;
    }

    public String toFileFormat(String separator) {
        return maker + separator + model + separator
                + year + separator + pricePerDay + separator + rented;
    }

    public String toString() {
        return String.format("%s %s %d %.2f€/day",
                maker, model, year, pricePerDay);
    }
}
