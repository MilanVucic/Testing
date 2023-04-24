package models;

import util.FileHelper;
import util.FileHelperNIO;

import java.util.ArrayList;
import java.util.List;

public class CarRepository {
    private List<Car> cars = new ArrayList<>();
    private static final String SEPARATOR = ",";

    public CarRepository(List<Car> cars) {
        this.cars = cars;
    }

    public CarRepository(String filename) {
        List<String> lines = FileHelperNIO.getFileLines(filename);
        for (String line : lines) {
            Car car = new Car(line, SEPARATOR);
            cars.add(car);
        }
    }

    public List<Car> getCars() {
        return cars;
    }

    public List<Car> getCarsByRentedStatus(boolean rented) {
        List<Car> filteredList = new ArrayList<>();
        for (Car car : cars) {
            if (car.isRented() == rented) {
                filteredList.add(car);
            }
        }
        return filteredList;
    }

    public void addCar(Car car) {
        cars.add(car);
    }

    public void saveToFile(String filename) {
        List<String> list = createListOfLines();
        FileHelperNIO.writeLinesToFile(list, filename);
    }

    private List<String> createListOfLines() {
        List<String> list = new ArrayList<>();
        for (Car car : cars) {
            list.add(car.toFileFormat(SEPARATOR));
        }
        return list;
    }
}
