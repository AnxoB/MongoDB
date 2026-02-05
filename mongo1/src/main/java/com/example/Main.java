package com.example;

import java.util.List;
import java.util.Scanner;

import org.bson.Document;

import com.mongodb.client.model.Filters;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");


        try (MongoProvider provider = new MongoProvider()){
            Scanner sc = new Scanner(System.in);
            Document document = new Document();

            System.out.println("Introduce el nombre: ");
            String nombre = sc.nextLine();
            System.out.println("Introduce la edad: ");
            Integer edad = sc.nextInt();
            sc.nextLine();
            System.out.println("Introduce la ciclo: ");
            String ciclo = sc.nextLine();

            document.append("nombre", nombre).append("edad", edad).append("ciclo", ciclo);
            

            provider.alumnado().insertOne(document);

            provider.alumnado().deleteOne(Filters.eq("nombre", "Andres"));

        } catch (Exception e) {
            System.out.println("Error" + e.getMessage());
        }
    }


}