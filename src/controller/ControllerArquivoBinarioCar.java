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
    
    public boolean deleteCar(String idCar) {
        clear();
        int posCar = searchCar(idCar);
        if(posCar != -1) {
            cars.remove(posCar);
            
            setObjects((ArrayList<Car>) cars);
            getArquivo();
            escrever(false);  
            return true;
        } else {
            return false;
        }   
    }
    
    public boolean updateCar(String idCar, String name, int year, String brand, double price) {
        clear();
        int posCar = searchCar(idCar);
        if (posCar != -1){
           cars.get(posCar).setName(name);
           cars.get(posCar).setYear(year);
           cars.get(posCar).setBrand(brand);
           cars.get(posCar).setPrice(price);

           setObjects((ArrayList<Car>) cars);
           getArquivo();
           escrever(false); 
           return true;
        } else {
           return false;
        }
    }
    
    public int searchCar(String idCar) {
        clear();
        int i = 0;
        int posCar = -1;
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
                    
                    posCar = i;
                    break;
                }
            } catch(Exception e ) {
                System.out.println("Error: Car not found.");         
            }
            i++;
        }
        return posCar;
    }
}
