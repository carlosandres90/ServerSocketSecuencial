/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.servidorsecuencial;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author andrespillajo
 */
public class ServidorSecuencial {

    public static void main(String[] args) {
        int port = 3010;
        try (ServerSocket servidorSocket = new ServerSocket(port)) {
            System.out.println("Servidor escuchando en el puerto: " + port);
            
            while(true){
                //Esperar conexión de un cliente
                System.out.println("Esperando cliente...");
                Socket clienteSocket = servidorSocket.accept();
                System.out.println("Cliente conectado: " + clienteSocket.getInetAddress());
                
                //Manejar la comunicación con el cliente
                try (BufferedReader clienteInput = new BufferedReader(new InputStreamReader(clienteSocket.getInputStream()));
                        PrintWriter output = new PrintWriter(clienteSocket.getOutputStream(), true);
                        BufferedReader consoleInput = new BufferedReader(new InputStreamReader(System.in))) {
                    
                    output.println("Bienvenido al servidor!!");
                    String mensaje;
                    String userInput;
                    while ((mensaje = clienteInput.readLine()) != null) {
                        System.out.println("Mensaje del cliente: " + mensaje);
                        userInput = consoleInput.readLine();
                        output.println(userInput); // Envía una respuesta al cliente
                    }
                }catch (Exception e) {
                    System.out.println("Error en la comunicación con el cliente: " + e.getMessage());
                } finally{
                    clienteSocket.close();
                }
            }
            
        } catch (IOException e) {
            System.out.println("Error en el servidor: " + e.getMessage());
        }
    }
}
