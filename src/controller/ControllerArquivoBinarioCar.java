/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;
import java.util.ArrayList;
import model.Car;

/**
 *
 * @author Victor Hugo
 */
public class ControllerArquivoBinarioCar extends ControllerArquivoBinario {
    protected ArrayList<Car> cars = new ArrayList<>();
    protected StringBuilder sbCars = new StringBuilder();

    //getters
    public ArrayList<Car> getCars() {
        return (ArrayList<Car>) getObjects();
    }
    
    public StringBuilder getSbCars() {
        return sbCars;
    }
    
    //setters
    public void setCars(ArrayList<Car> cars) {
        this.setObjects((ArrayList<Car>) cars);
    }
   
    public void setSbCars(StringBuilder sbCars) {
        this.sbCars = sbCars;
    }
    
    //methods
    public void clear() {
        cars.clear();
        sbCars.setLength(0);
        getArquivo();
        if(ler() == true) {
            cars = (ArrayList<Car>) getObjects();
        }
    }
    
    public void registerCar(String name, int year, String brand, double price) {
        clear();
        if(cars.isEmpty()) {
            cars.add(new Car(1, name, year, brand, price));
        }
        else {
            cars.add(new Car(cars.get(cars.size() - 1).getIdCar() + 1, name, year, brand, price));
        }
        
        setObjects((ArrayList<Car>) cars);
        getArquivo();
        escrever(true);
    }
    
    public void readCar() {
        clear();
        
        for(Car car : cars) {
           sbCars
                    .append("ID: ")
                    .append(car.getIdCar())
                    .append("\n")
                    .append("Name: ")
                    .append(car.getName())
                    .append("\n")
                    .append("Year: ")
                    .append(car.getYear())
                    .append("\n")
                    .append("Brand: ")
                    .append(car.getBrand())
                    .append("\n")
                    .append("Price: ")
                    .append(car.getPrice())
                    .append("\n\n");
        }
    }
    
    public void deleteCar(String idCar) {
        clear();
        int i = 0;
        for(Car car : cars) {
            try {
                if(cars.get(i).getIdCar() == Integer.valueOf(idCar)) {
                    cars.remove(i);
                    break;
                }
            } catch(Exception e ) {
                System.out.println("Error: Car not removed.");         
            }
            i++;
        }
        
        setObjects((ArrayList<Car>) cars);
        getArquivo();
        escrever(false);   
    }
    
    public void updateCar(String idCar, String name, int year, String brand, double price) {
        clear();
        int i = 0;
        for(Car car : cars) {
            try {
                if(cars.get(i).getIdCar() == Integer.valueOf(idCar)) {
                    car.setName(name);
                    car.setYear(year);
                    car.setBrand(brand);
                    car.setPrice(price);
                    break;   
                }
            } catch(Exception e) {
                System.out.println("Error: Could not update car.");
            }
            i++;
        }
        
        setObjects((ArrayList<Car>) cars);
        getArquivo();
        escrever(false); 
    }
    
    public Car searchCar(String idCar) {
        clear();
        int i = 0;
        Car objCar = null;
        for(Car car : cars) {
            try {
                if(cars.get(i).getIdCar() == Integer.valueOf(idCar)) {
                    sbCars
                        .append("ID: ")
                        .append(car.getIdCar())
                        .append("\n")
                        .append("Name: ")
                        .append(car.getName())
                        .append("\n")
                        .append("Year: ")
                        .append(car.getYear())
                        .append("\n")
                        .append("Brand: ")
                        .append(car.getBrand())
                        .append("\n")
                        .append("Price: ")
                        .append(car.getPrice())
                        .append("\n\n");
                    
                    objCar = new Car(car.getIdCar(), car.getName(), car.getYear(), car.getBrand(), car.getPrice());
                    break;
                }
            } catch(Exception e ) {
                System.out.println("Error: Car not found.");         
            }
            i++;
        }
        return objCar;
    }
}
