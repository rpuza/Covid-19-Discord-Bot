package com.mongodb.coronavirus;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.bson.conversions.Bson;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static com.mongodb.client.model.Filters.and;
import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Sorts.descending;
import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;


public class MongoDB {

    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    PrintStream ps = new PrintStream(baos);

    public MongoDB(String state, String county){
        try (MongoClient client = MongoClients.create(getMongoClient())) {
            MongoDatabase db = client.getDatabase("covid19");
            MongoCollection<UsOnly> usCollection = db.getCollection("us_only", UsOnly.class);

            Bson countryFilter = eq("country", "US");
            Bson stateFilter = eq("state", state);
            Bson countyFilter = eq("county", county);

            PrintStream old = System.out;
            System.setOut(ps);

            usCollection.find(and(countryFilter,stateFilter, countyFilter)).sort(descending("date")).limit(1).forEach(System.out::println);

            System.out.flush();
            System.setOut(old);
        }
    }

    public String getData(){
        return baos.toString();
    }
//We use the database updated by John Hopkins University
//https://developer.mongodb.com/article/johns-hopkins-university-covid-19-data-atlas
    private static MongoClientSettings getMongoClient() {
        String connectionString = "mongodb+srv://readonly:readonly@covid-19.hip2i.mongodb.net/covid19";
        CodecRegistry pojoCodecRegistry = fromProviders(PojoCodecProvider.builder().automatic(true).build());
        CodecRegistry codecRegistry = fromRegistries(MongoClientSettings.getDefaultCodecRegistry(), pojoCodecRegistry);
        return MongoClientSettings.builder()
                .applyConnectionString(new ConnectionString(connectionString))
                .codecRegistry(codecRegistry)
                .build();
    }
}
