/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.util.ArrayList;
import model.Brand;

/**
 *
 * @author Victor Hugo
 */
public class ControllerArquivoBinarioBrand extends ControllerArquivoBinario {
    protected ArrayList<Brand> brands = new ArrayList<>();
    protected StringBuilder sbBrands = new StringBuilder();

    //getters
    public ArrayList<Brand> getBrands() {
        return (ArrayList<Brand>) getObjects();
    }
    
    public StringBuilder getSbBrands() {
        return sbBrands;
    }
    
    //setters
    public void setBrands(ArrayList<Brand> brands) {
        this.setObjects((ArrayList<Brand>) brands);
    }
   
    public void setSbBrands(StringBuilder sbBrands) {
        this.sbBrands = sbBrands;
    }
    
    //methods
    public void clear() {
        brands.clear();
        sbBrands.setLength(0);
        getArquivo();
        if(ler() == true) {
            brands = (ArrayList<Brand>) getObjects();
        }
    }
    
    public void registerBrand(String name, int yearCreated, String website) {
        clear();
        if(brands.isEmpty()) {
            brands.add(new Brand(1, name, yearCreated, website));
        }
        else {
            brands.add(new Brand(brands.get(brands.size() - 1).getIdBrand() + 1, name, yearCreated, website));
        }
        
        setObjects((ArrayList<Brand>) brands);
        getArquivo();
        escrever(true);
    }
    
    public void readBrand() {
        clear();
        
        for(Brand brand : brands) {
            sbBrands
                    .append("ID: ")
                    .append(brand.getIdBrand())
                    .append("\n")
                    .append("Name: ")
                    .append(brand.getName())
                    .append("\n")
                    .append("Year of Creation: ")
                    .append(brand.getYearCreated())
                    .append("\n")
                    .append("Website: ")
                    .append(brand.getWebsite())
                    .append("\n\n");
        }
    }
    
    public void deleteBrand(String idBrand) {
        clear();
        int i = 0;
        for(Brand brand : brands) {
            try {
                if(brands.get(i).getIdBrand() == Integer.valueOf(idBrand)) {
                    brands.remove(i);
                    break;
                }
            } catch(Exception e ) {
                System.out.println("Error: Brand not removed.");         
            }
            i++;
        }
        
        setObjects((ArrayList<Brand>) brands);
        getArquivo();
        escrever(false);   
    }
    
    public void updateBrand(String idBrand, String name, int yearCreated, String website) {
        clear();
        int i = 0;
        for(Brand brand : brands) {
            try {
                if(brands.get(i).getIdBrand() == Integer.valueOf(idBrand)) {
                    brand.setName(name);
                    brand.setYearCreated(yearCreated);
                    brand.setWebsite(website);
                    break;   
                }
            } catch(Exception e) {
                System.out.println("Error: Could not update brand.");
            }
            i++;
        }
        
        setObjects((ArrayList<Brand>) brands);
        getArquivo();
        escrever(false); 
    }
    
    public void searchBrand(String idBrand) {
        clear();
        int i = 0;
        for(Brand brand : brands) {
            try {
                if(brands.get(i).getIdBrand() == Integer.valueOf(idBrand)) {
                    sbBrands
                        .append("ID: ")
                        .append(brand.getIdBrand())
                        .append("\n")
                        .append("Name: ")
                        .append(brand.getName())
                        .append("\n")
                        .append("Year of Creation: ")
                        .append(brand.getYearCreated())
                        .append("\n")
                        .append("Website: ")
                        .append(brand.getWebsite())
                        .append("\n\n");
                    break;
                }
            } catch(Exception e ) {
                System.out.println("Error: Brand not found.");         
            }
            i++;
        }
    }
}
