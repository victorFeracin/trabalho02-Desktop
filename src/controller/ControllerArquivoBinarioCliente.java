/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.util.ArrayList;
import model.Cliente;

/**
 *
 * @author Victor Hugo
 */
public class ControllerArquivoBinarioCliente extends ControllerArquivoBinario{
    
    protected ArrayList<Cliente> clientes = new ArrayList<>();
    protected StringBuilder sbClientes = new StringBuilder();

    //getters
    public ArrayList<Cliente> getClientes() {
        return (ArrayList<Cliente>) getObjects();
    }
    
    public StringBuilder getSbClientes() {
        return sbClientes;
    }
    
    //setters
    public void setClientes(ArrayList<Cliente> clientes) {
        this.setObjects((ArrayList<Cliente>) clientes);
    }
   
    public void setSbClientes(StringBuilder sbClientes) {
        this.sbClientes = sbClientes;
    }
    
    //methods
    public void clear() {
        clientes.clear();
        sbClientes.setLength(0);
        getArquivo();
        if(ler() == true) {
            clientes = (ArrayList<Cliente>) getObjects();
        }
    }
    
    public void registerCliente(String name, String email, String phone) {
        clear();
        if(clientes.isEmpty()) {
            clientes.add(new Cliente(1, name, email, phone));
        }
        else {
            clientes.add(new Cliente(clientes.get(clientes.size() - 1).getIdCliente() + 1, name, email, phone));
        }
        
        setObjects((ArrayList<Cliente>) clientes);
        getArquivo();
        escrever(true);
    }
    
    public void readCliente() {
        clear();
        
        for(Cliente cliente : clientes) {
            sbClientes
                    .append("ID: ")
                    .append(cliente.getIdCliente())
                    .append("\n")
                    .append("Name: ")
                    .append(cliente.getName())
                    .append("\n")
                    .append("Email: ")
                    .append(cliente.getEmail())
                    .append("\n")
                    .append("Phone: ")
                    .append(cliente.getPhone())
                    .append("\n\n");
        }
    }
    
    public void deleteCliente(String idCliente) {
        clear();
        int i = 0;
        for(Cliente cliente : clientes) {
            try {
                if(clientes.get(i).getIdCliente() == Integer.valueOf(idCliente)) {
                    clientes.remove(i);
                    break;
                }
            } catch(Exception e ) {
                System.out.println("Error: Customer not removed.");         
            }
            i++;
        }
        
        setObjects((ArrayList<Cliente>) clientes);
        getArquivo();
        escrever(false);   
    }
    
    public void updateCliente(String idCliente, String name, String email, String phone) {
        clear();
        int i = 0;
        for(Cliente cliente : clientes) {
            try {
                if(clientes.get(i).getIdCliente() == Integer.valueOf(idCliente)) {
                    cliente.setName(name);
                    cliente.setEmail(email);
                    cliente.setPhone(phone);
                    break;   
                }
            } catch(Exception e) {
                System.out.println("Error: Could not update customer.");
            }
            i++;
        }
        
        setObjects((ArrayList<Cliente>) clientes);
        getArquivo();
        escrever(false); 
    }
    
    public Cliente searchCliente(String idCliente) {
        clear();
        int i = 0;
        Cliente objCliente = null;
        for(Cliente cliente : clientes) {
            try {
                if(clientes.get(i).getIdCliente() == Integer.valueOf(idCliente)) {
                    sbClientes
                        .append("ID: ")
                        .append(cliente.getIdCliente())
                        .append("\n")
                        .append("Name: ")
                        .append(cliente.getName())
                        .append("\n")
                        .append("Email: ")
                        .append(cliente.getEmail())
                        .append("\n")
                        .append("Phone: ")
                        .append(cliente.getPhone())
                        .append("\n\n");
                    
                    objCliente = new Cliente(cliente.getIdCliente(), cliente.getName(), cliente.getEmail(), cliente.getPhone());
                    break;
                }
            } catch(Exception e ) {
                System.out.println("Error: Customer not found.");         
            }
            i++;
        }
        return objCliente;
    }
}
