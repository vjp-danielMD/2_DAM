package com.iesvjp.morenodominguez_daniel_ut9;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Accumulators;
import com.mongodb.client.model.Aggregates;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Sorts;
import com.mongodb.client.model.Updates;
import org.bson.Document;
import org.bson.conversions.Bson;

import java.util.Arrays;
import java.util.function.Consumer;

public class MorenoDominguez_Daniel_UT9 {

    public static void main(String[] args) {
        String uri = "mongodb://localhost:27017";
        try (MongoClient mongoClient = MongoClients.create(uri)) {
            MongoDatabase database = mongoClient.getDatabase("ad");
            MongoCollection<Document> collection = database.getCollection("articulos");

            System.out.println("--- Inicio de Operaciones UT9 MongoDB ---");

            opcionA(collection);
            opcionB(collection);
            opcionC(collection);
            opcionD(collection);
            opcionE(collection);
            opcionF(collection);
            opcionG(collection);

            System.out.println("\n--- Fin de Operaciones ---");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void opcionA(MongoCollection<Document> collection) {
        System.out.println("\n--- a. Resumen por categoría ---");
        Bson group = Aggregates.group("$categoria",
                Accumulators.sum("numArticulos", 1),
                Accumulators.sum("totalUV", "$uv"),
                Accumulators.sum("totalImporte", Document.parse("{\"$multiply\": [\"$pvp\", \"$uv\"]}"))
        );

        collection.aggregate(Arrays.asList(group)).forEach((Consumer<Document>) doc -> {
            int numArticulos = ((Number) doc.get("numArticulos")).intValue();
            int totalUV = ((Number) doc.get("totalUV")).intValue();
            System.out.println("Categoria: " + doc.getString("_id") +
                    " | N. Articulos: " + numArticulos +
                    " | Total UV: " + totalUV +
                    " | Total Importe: " + doc.get("totalImporte"));
        });
    }

    private static void opcionB(MongoCollection<Document> collection) {
        System.out.println("\n--- b. Categoria con mayores ventas ---");
        Bson group = Aggregates.group("$categoria",
                Accumulators.sum("totalImporte", Document.parse("{\"$multiply\": [\"$pvp\", \"$uv\"]}"))
        );
        Bson sort = Aggregates.sort(Sorts.descending("totalImporte"));
        Bson limit = Aggregates.limit(1);

        collection.aggregate(Arrays.asList(group, sort, limit)).forEach((Consumer<Document>) doc -> {
            System.out.println("Categoria con mayores ventas: " + doc.getString("_id") +
                    " | Importe: " + doc.get("totalImporte"));
        });
    }

    private static void opcionC(MongoCollection<Document> collection) {
        System.out.println("\n--- c. Resumen categoria Deportes ---");
        Bson match = Aggregates.match(Filters.eq("categoria", "Deportes"));
        Bson group = Aggregates.group("$categoria",
                Accumulators.sum("totalUV", "$uv"),
                Accumulators.sum("totalImporte", Document.parse("{\"$multiply\": [\"$pvp\", \"$uv\"]}")),
                Accumulators.avg("mediaUV", "$uv")
        );

        collection.aggregate(Arrays.asList(match, group)).forEach((Consumer<Document>) doc -> {
            int totalUV = ((Number) doc.get("totalUV")).intValue();
            System.out.println("Categoria: " + doc.getString("_id") +
                    " | Total UV: " + totalUV +
                    " | Total Importe: " + doc.get("totalImporte") +
                    " | Media UV: " + doc.get("mediaUV"));
        });
    }

    private static void opcionD(MongoCollection<Document> collection) {
        System.out.println("\n--- d. Articulo mas caro ---");
        Bson sort = Aggregates.sort(Sorts.descending("pvp"));
        Bson limit = Aggregates.limit(1);

        collection.aggregate(Arrays.asList(sort, limit)).forEach((Consumer<Document>) doc -> {
            System.out.println("Articulo mas caro: " + doc.getString("denominacion") +
                    " | PVP: " + doc.get("pvp"));
        });
    }

    private static void opcionE(MongoCollection<Document> collection) {
        System.out.println("\n--- e. Articulos con stock actual negativo (stock - uv) ---");
        Bson match = Aggregates.match(Filters.expr(Document.parse("{\"$lt\": [{\"$subtract\": [\"$stock\", \"$uv\"]}, 0]}")));

        collection.aggregate(Arrays.asList(match)).forEach((Consumer<Document>) doc -> {
            int stockOriginal = ((Number) doc.get("stock")).intValue();
            int uv = ((Number) doc.get("uv")).intValue();
            int stockActual = stockOriginal - uv;
            System.out.println("Articulo: " + doc.getString("denominacion") +
                    " | Stock original: " + stockOriginal +
                    " | UV: " + uv +
                    " | Stock actual: " + stockActual);
        });
    }

    private static void opcionF(MongoCollection<Document> collection) {
        System.out.println("\n--- f. Incrementa el stock a todos los articulos a 10 unidades ---");
        collection.updateMany(new Document(), Updates.inc("stock", 10));
        System.out.println("Stock de todos los articulos incrementado en 10 unidades.");
    }

    private static void opcionG(MongoCollection<Document> collection) {
        System.out.println("\n--- g. Inserta un nuevo artículo ---");
        Document nuevoArticulo = new Document("codigo", 9)
                .append("denominacion", "Teclado con lector DNI")
                .append("pvp", 30)
                .append("categoria", "Informatica")
                .append("uv", 1)
                .append("stock", 10);

        if (collection.find(Filters.eq("codigo", 9)).first() == null) {
            collection.insertOne(nuevoArticulo);
            System.out.println("Articulo insertado: " + nuevoArticulo.toJson());
        } else {
            System.out.println("El articulo con codigo 9 ya existe en la base de datos.");
        }
    }
}
