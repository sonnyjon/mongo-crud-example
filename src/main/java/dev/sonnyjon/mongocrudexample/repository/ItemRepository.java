package dev.sonnyjon.mongocrudexample.repository;

import dev.sonnyjon.mongocrudexample.model.GroceryItem;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

/**
 * Created by Sonny on 7/6/2022.
 */
public interface ItemRepository extends MongoRepository<GroceryItem, String>
{
    @Query("{name:'?0'}")
    GroceryItem findItemByName(String name);

    @Query(value="{category:'?0'}", fields="{'name' : 1, 'quantity' : 1}")
    List<GroceryItem> findAll(String category);

    long count();
}
