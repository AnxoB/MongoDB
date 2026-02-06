package com.example;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

public class Main {
    public static void main(String[] args) {
        MatchController matchController = new MatchController();

        /* 

        List<Document> partidas = new ArrayList<>();

        partidas.add(new Document()
                .append("xogador", "Mario")
                .append("xogo", "Space Invaders")
                .append("puntuacion", 1200)
                .append("duracion", 15)
                .append("nivel", 3));

        partidas.add(new Document()
                .append("xogador", "Anxo")
                .append("xogo", "Pong")
                .append("puntuacion", 800)
                .append("duracion", 10)
                .append("nivel", 2));

        partidas.add(new Document()
                .append("xogador", "Manuel")
                .append("xogo", "Tetris")
                .append("puntuacion", 1000)
                .append("duracion", 20)
                .append("nivel", 5));
                
        partidas.add(new Document()
                .append("xogador", "Andres")
                .append("xogo", "Donkey Kong")
                .append("puntuacion", 500)
                .append("duracion", 10)
                .append("nivel", 4));

        partidas.add(new Document()
                .append("xogador", "Anxo")
                .append("xogo", "Space Invaders")
                .append("puntuacion", 1300)
                .append("duracion", 20)
                .append("nivel", 4));

        matchController.insertarMultiplesPartidas(partidas);

        */

        matchController.obtenerTotalPuntuacion();
        matchController.mejorPartida();
        matchController.partidaMasCorta();
        matchController.rankingJugadores();
        matchController.listaPartidas();
        matchController.obtenerPuntuacionMedia();
        matchController.partidas();
    }
}