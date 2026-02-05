package com.example;

import java.util.List;

import org.bson.Document;

public class MatchController {
    public void insertarPartida(Document document){
        try (MongoProvider provider = new MongoProvider()) {
            provider.videojuegos().insertOne(document);
        } catch (Exception e) {
            System.out.println("No se ha podido insertar: " + e.getMessage());
        }
    }

    public void insertarMultiplesPartidas(List<Document> documents){
        try (MongoProvider provider = new MongoProvider()) {
            provider.videojuegos().insertMany(documents);
        } catch (Exception e) {
            System.out.println("No se han podido insertar múltiples partidas: " + e.getMessage());
        }
    }


}
