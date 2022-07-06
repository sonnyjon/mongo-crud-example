package dev.sonnyjon.mongocrudexample.repository;

import com.mongodb.client.result.UpdateResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.mongodb.core.query.*;

import dev.sonnyjon.mongocrudexample.model.GroceryItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Component;

/**
 * Created by Sonny on 7/6/2022.
 */
@Slf4j
@Component
public class CustomItemRepositoryImpl implements CustomItemRepository
{
    @Autowired
    MongoTemplate mongoTemplate;

    @Override
    public void updateItemQuantity(String name, float newQuantity)
    {
        Query query = new Query(Criteria.where("name").is(name));
        Update update = new Update();
        update.set("quantity", newQuantity);

        UpdateResult result = mongoTemplate.updateFirst(query, update, GroceryItem.class);

        if (result == null) log.info("No documents updated");
        else log.info(result.getModifiedCount() + " document(s) updated..");
    }
}
