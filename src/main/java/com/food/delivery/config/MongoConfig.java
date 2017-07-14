package com.food.delivery.config;

import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

/**
 * Created by mz on 14/07/17.
 */
@Configuration
@EnableMongoRepositories(basePackages = "com.food.delivery")
public class MongoConfig extends AbstractMongoConfiguration {

    @Override
    protected String getDatabaseName() {
        return "food-delivery";
    }

    @Override
    public Mongo mongo() throws Exception {
        return new MongoClient();
    }

}
