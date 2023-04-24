import models.Car;
import models.CarRenting;
import models.CarRepository;
import util.FileHelperNIO;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static final String CARS_FILENAME = "cars.txt";
    public static final String RENTINGS_FILENAME = "car_rent.txt";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        CarRepository carRepository = new CarRepository(CARS_FILENAME);
        while (true) {
            printMenu();
            String input = scanner.next();
            try {
                int userChoice = Integer.parseInt(input);
                if (userChoice == 0) {
                    break;
                }
                switch (userChoice) {
                    case 1:
                        addCar(scanner, carRepository);
                        break;
                    case 2:
                        listCars(carRepository.getCars());
                        break;
                    case 3:
                        carRepository.saveToFile(CARS_FILENAME);
                        break;
                    case 4:
                        rentACar(carRepository);
                        break;
                    case 5:
                        // List available cars
                        listCars(carRepository.getCarsByRentedStatus(false));
                        break;
                    case 6:
                        // List rented cars
                        listCars(carRepository.getCarsByRentedStatus(true));
                        break;
                    default:
                        System.out.println("Option doesn't exist.");
                }
            } catch (NumberFormatException e) {
                System.out.println("You entered a bad number");
            }
        }
        System.out.println("Exiting app.");
    }

    private static void rentACar(CarRepository carRepository) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Available cars:");
        List<Car> availableCars = carRepository.getCarsByRentedStatus(false);
        listCars(availableCars);
        if (availableCars.isEmpty()) {
            System.out.println("No available cars to rent.");
            return;
        }
        System.out.println("Select a car to rent (enter car number):");
        int carNumber = scanner.nextInt();
        if (carNumber < 1 || carNumber > availableCars.size()) {
            System.out.println("Invalid car number.");
            return;
        }
        Car car = availableCars.get(carNumber - 1);
        car.setRented(true);
        CarRenting carRenting = new CarRenting(LocalDate.now(), car);
        List<String> list = new ArrayList<>();
        list.add(carRenting.toFileFormat());
        FileHelperNIO.writeLinesToFile(list, RENTINGS_FILENAME);
    }

    private static void listCars(List<Car> cars) {
        for (int i = 0; i < cars.size(); i++) {
            // 1. Mercedes E220 2012 33.34e/day
            System.out.println(String.format
                    ("%d. %s", (i + 1), cars.get(i).toString()));
        }
    }

    private static void addCar(Scanner scanner, CarRepository carRepository) {
        System.out.println("Enter car maker:");
        String maker = scanner.next();

        System.out.println("Enter car model:");
        String model = scanner.next();
        int year;
        do {
            System.out.println("Enter year of production:");
            String yearString = scanner.next();
            try {
                year = Integer.parseInt(yearString);
                break;
            } catch (NumberFormatException e) {
                System.out.println("Bad year.");
            }
        } while (true);

        double pricePerDay;
        do {
            System.out.println("Enter the price of renting the car:");
            String priceString = scanner.next();
            try {
                pricePerDay = Double.parseDouble(priceString);
                break;
            } catch (NumberFormatException e) {
                System.out.println("Bad price.");
            }
        } while (true);
        Car car = new Car(maker, model, year, pricePerDay);
        carRepository.addCar(car);
    }

    private static void printMenu() {
        System.out.println("Select one of the options below:");
        System.out.println("1. Add car");
        System.out.println("2. List all cars");
        System.out.println("3. Save cars to file");
        System.out.println("4. Rent a car");
        System.out.println("5. List available cars");
        System.out.println("6. List rented cars");
        System.out.println("0. Exit");
    }
}