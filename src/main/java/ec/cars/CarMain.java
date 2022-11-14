package ec.cars;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class CarMain {
    public static void main(String[] args) {
        CarManufacturer bmw = new CarManufacturer("BMW", "BMW-BRAND");
        bmw.createCar("Model2020", Color.Blue);
        bmw.createCar("model2021", Color.Green);
        CarManufacturer volvo = new CarManufacturer("VOLVO", "VOLVO-BRAND");
        volvo.createCar("Model2022", Color.White);

        System.out.println(bmw);
        System.out.println(volvo);

    }
}

@Data
class CarManufacturer {
    private String name;
    private String brand;
    private int countCreatedCars;
    private Set<Car> cars = new HashSet<>();

    public CarManufacturer(String name, String brand) {
        this.name = name;
        this.brand = brand;
        this.countCreatedCars = 0;

    }

    public Car createCar(String model, Color color) {
        this.countCreatedCars++;
         Car c = new Car(brand, model, RegNo.create(), color);
         cars.add(c);
         return c;
    }

    @Override
    public String toString() {
        return "CarManufacturer{" +
                "name='" + name + '\'' +
                ", brand='" + brand + '\'' +
                ", countCreatedCars=" + countCreatedCars +
                ", cars=" + cars +
                '}';
    }
}

@Data @AllArgsConstructor
class Car {
    private String brand;
    private String model;
    private RegNo regNo;
    private Color color;

    @Override
    public String toString() {
        return "Car{" +
                "brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                ", regNo=" + regNo +
                ", color=" + color +
                '}';
    }
}

enum Color {
    Yellow,
    Green,
    Black,
    White,
    Blue
}

@Data @AllArgsConstructor
class  RegNo {
    private static  int numberCounter = 100;
    private String text;
    private int number;

   public static RegNo create() {
        return new RegNo("BBB", numberCounter++);
    }
    @Override
    public String toString() {
        return "RegNo{" +
                "text='" + text + '\'' +
                ", number=" + number +
                '}';
    }
}
