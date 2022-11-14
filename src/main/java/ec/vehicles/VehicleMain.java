package ec.vehicles;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.LinkedList;
import java.util.List;

public class VehicleMain {
    public static void main(String[] args) {
        Brand brand = new Brand("Volvo");
        Bike bike1 = new Bike(brand);
        Car car1 = new Car(brand);


        List<Vehicle> vehicleList = new LinkedList<>();
        vehicleList.add(car1);
        vehicleList.add(bike1);
        vehicleList.forEach(System.out::println);

        vehicleList.forEach(vehicle -> System.out.println(vehicle.getClass().getSimpleName()+" has "+vehicle.countWheels()+" wheel "));
    }
}

@Data
abstract class Vehicle {
    private Brand brand;
    private int speed;

    public Vehicle(Brand brand) {
        this.brand = brand;
        this.speed = 0;
    }

    public abstract int countWheels();
}

@Data @AllArgsConstructor
class Brand {
    String name;

    @Override
    public String toString() {
        return "Brand{" +
                "name='" + name + '\'' +
                '}';
    }
}


class Bike extends Vehicle {
    private Wheel front;
    private Wheel back;

    public Bike(Brand brand) {
        super(brand);
        this.front = new Wheel(16);
        this.back = new Wheel(16);
    }

    @Override
    public int countWheels() {
        return 2;
    }

}

class Car extends Vehicle {
    private Wheel frontLeft;
    private Wheel frontRight;
    private Wheel backLeft;
    private Wheel backRight;

    public Car(Brand brand) {
        super(brand);
        this.frontLeft = new Wheel(14);
        this.frontRight = new Wheel(14);
        this.backLeft = new Wheel(14);
        this.backRight = new Wheel(14);
    }
    @Override
    public int countWheels() {
        return 4;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Car{");
        sb.append("brand=").append(getBrand());
        sb.append(", speed=").append(getSpeed());
        sb.append(", frontLeftWheel=").append(frontLeft);
        sb.append(", frontRightWheel=").append(frontRight);
        sb.append(", backLeftWheel=").append(backLeft);
        sb.append(", backRightWheel=").append(backRight);
        sb.append('}');
        return sb.toString();
    }
}

@Data @AllArgsConstructor
class Wheel {
    private int inch;

    @Override
    public String toString() {
        return "Wheel{" +
                "inch=" + inch +
                '}';
    }
}
