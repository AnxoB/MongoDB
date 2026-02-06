package com.example;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;
import org.bson.conversions.Bson;

import com.mongodb.client.model.Accumulators;
import com.mongodb.client.model.Aggregates;
import com.mongodb.client.model.Projections;
import com.mongodb.client.model.Sorts;

public class MatchController {
    public void insertarPartida(Document document){
        try (MongoProvider provider = new MongoProvider()) {
            provider.partidas().insertOne(document);
        } catch (Exception e) {
            System.out.println("No se ha podido insertar: " + e.getMessage());
        }
    }

    public void insertarMultiplesPartidas(List<Document> documents){
        try (MongoProvider provider = new MongoProvider()) {
            provider.partidas().insertMany(documents);
        } catch (Exception e) {
            System.out.println("No se han podido insertar múltiples partidas: " + e.getMessage());
        }
    }

    public void obtenerTotalPuntuacion(){
        try (MongoProvider provider = new MongoProvider()) {
            List<Document> list = new ArrayList<>();
            List<Bson> filter = List.of(
                Aggregates.group("$xogador",
                    Accumulators.sum("totalPuntuacion", "$puntuacion")
                )
            );
            provider.partidas().aggregate(filter).into(list);
            list.forEach(doc -> System.out.println(doc));
            
        } catch (Exception e) {
            System.out.println("Ha ocurrido un error al obtener la media: " + e.getMessage());
        }
    }

    public void mejorPartida(){
        try (MongoProvider provider = new MongoProvider()) {
            List<Document> list = new ArrayList<>();
            List<Bson> filter = List.of(
                Aggregates.group("$xogador", 
                    Accumulators.max("mejorPuntuacion", "$puntuacion")
                )
            );
            provider.partidas().aggregate(filter).into(list);
            list.forEach(doc -> System.out.println(doc));

        } catch (Exception e) {
            System.out.println("Ha ocurrido un error al obtener la media: " + e.getMessage());
        }
    }
    
    public void partidaMasCorta(){
        try (MongoProvider provider = new MongoProvider()) {
            List<Document> list = new ArrayList<>();
            List<Bson> filter = List.of(
                Aggregates.group("$xogo",
                    Accumulators.min("duracionMínima", "$duracion")
                )
            );
            provider.partidas().aggregate(filter).into(list);
            list.forEach(doc -> System.out.println(doc));
        } catch (Exception e) {
            System.out.println("Ha ocurrido un error al obtener la duracion mas corta: " + e.getMessage());
        }
    }

    public void rankingJugadores(){
        try (MongoProvider provider = new MongoProvider()) {
            List<Document> list = new ArrayList<>();
            List<Bson> filter = List.of(
                Aggregates.group("$xogador",
                    Accumulators.sum("totalPuntuacion", "$puntuacion")),
                    Aggregates.sort(Sorts.descending("totalPuntuacion"))
            );
            provider.partidas().aggregate(filter).into(list);
            list.forEach(doc -> System.out.println(doc));
            
        } catch (Exception e) {
            System.out.println("Ha ocurrido un error al obtener la media: " + e.getMessage());
        }
    }

    //Listar partidas sin aggregate
    public void listaPartidas(){
        try (MongoProvider provider = new MongoProvider()) {
            Document projection = new Document();
            projection.append("_id", 0);
            projection.append("xogador", 1);
            projection.append("xogo", 1);
            projection.append("puntuacion", 1);

            provider.partidas().find().projection(projection).forEach(doc -> System.out.println(doc));
        } catch (Exception e) {
            System.out.println("Ha ocurrido un error al obtener la media: " + e.getMessage());
        }
    }

    //Lista partida con aggregate
    public void partidas(){
        try (MongoProvider provider = new MongoProvider()) {
            List<Document> list = new ArrayList<>();
            List<Bson> filter = List.of(
                Aggregates.project(Projections.fields(
                    Projections.excludeId(),
                    Projections.include("xogador", "xogo", "puntuacion")
                ))
            );
            provider.partidas().aggregate(filter).into(list);
            list.forEach(doc -> System.out.println(doc));
        } catch (Exception e) {
            System.out.println("Ha ocurrido un error al obtener la media: " + e.getMessage());
        }
    }

    public void obtenerPuntuacionMedia(){
        try (MongoProvider provider = new MongoProvider()) {
            List<Document> list = new ArrayList<>();
            List<Bson> filter = List.of(
                Aggregates.group("$xogo",
                    Accumulators.avg("puntuacionMedia", "$puntuacion")),
                    Aggregates.sort(Sorts.descending("puntuacionMedia"))
            );
            provider.partidas().aggregate(filter).into(list);
            list.forEach(doc -> System.out.println(doc));
            
        } catch (Exception e) {
            System.out.println("Ha ocurrido un error al obtener la media: " + e.getMessage());
        }
    }


}
