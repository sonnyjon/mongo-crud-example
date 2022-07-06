package dev.sonnyjon.mongocrudexample;

import dev.sonnyjon.mongocrudexample.model.GroceryItem;
import dev.sonnyjon.mongocrudexample.repository.ItemRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import java.util.List;

@Slf4j
@SpringBootApplication
@EnableMongoRepositories
public class MongoCrudExampleApplication implements CommandLineRunner
{
    @Autowired
    ItemRepository itemRepository;

    public static void main(String[] args)
    {
        SpringApplication.run(MongoCrudExampleApplication.class, args);
    }

    public void run(String... args)
    {

        log.info("-------------CREATE GROCERY ITEMS-------------------------------");
        createGroceryItems();

        log.info("----------------SHOW ALL GROCERY ITEMS---------------------------");
        showAllGroceryItems();

        log.info("--------------GET ITEM BY NAME-----------------------------------");
        getGroceryItemByName("Whole Wheat Biscuit");

        log.info("-----------GET ITEMS BY CATEGORY---------------------------------");
        getItemsByCategory("millets");

        log.info("-----------UPDATE CATEGORY NAME OF SNACKS CATEGORY----------------");
        updateCategoryName("snacks", "munchies");

        log.info("----------------SHOW ALL GROCERY ITEMS---------------------------");
        showAllGroceryItems();

        log.info("----------DELETE A GROCERY ITEM----------------------------------");
        deleteGroceryItem("Kodo Millet");

        log.info("----------------SHOW ALL GROCERY ITEMS---------------------------");
        showAllGroceryItems();

        log.info("------------FINAL COUNT OF GROCERY ITEMS-------------------------");
        findCountOfGroceryItems();

        log.info("-------------------------THANK YOU-------------------------------");

    }

    // CREATE

    private void createGroceryItems()
    {
        log.info("Data creation started...");
        itemRepository.save(new GroceryItem("Whole Wheat Biscuit", "Whole Wheat Biscuit", 5, "snacks"));
        itemRepository.save(new GroceryItem("Kodo Millet", "XYZ Kodo Millet healthy", 2, "millets"));
        itemRepository.save(new GroceryItem("Dried Red Chilli", "Dried Whole Red Chilli", 2, "spices"));
        itemRepository.save(new GroceryItem("Pearl Millet", "Healthy Pearl Millet", 1, "millets"));
        itemRepository.save(new GroceryItem("Cheese Crackers", "Bonny Cheese Crackers Plain", 6, "snacks"));
        log.info("Data creation complete...");
    }

    // READ

    public void showAllGroceryItems()
    {
        itemRepository.findAll().forEach(this::getItemDetails);
    }

    public void getGroceryItemByName(String name)
    {
        log.info("Getting item by name: " + name);
        GroceryItem item = itemRepository.findItemByName(name);
        getItemDetails(item);
    }

    public void getItemsByCategory(String category)
    {
        log.info("Getting items for the category " + category);

        List<GroceryItem> list = itemRepository.findAll(category);
        list.forEach(item -> log.info("Name: " + item.getName() + ", Quantity: " + item.getQuantity()));
    }

    public void findCountOfGroceryItems()
    {
        long count = itemRepository.count();
        log.info("Number of documents in the collection: " + count);
    }

    // UPDATE

    public void updateCategoryName(String category, String newCategory)
    {
        // Find all the items with the category snacks
        List<GroceryItem> list = itemRepository.findAll(category);

        list.forEach(item -> {
            // Update the category in each document
            item.setCategory(newCategory);
        });

        // Save all the items in database
        List<GroceryItem> itemsUpdated = itemRepository.saveAll(list);

        if (itemsUpdated != null) log.info("Successfully updated " + itemsUpdated.size() + " items.");
    }

    // DELETE

    public void deleteGroceryItem(String id)
    {
        itemRepository.deleteById(id);
        log.info("Item with id " + id + " deleted...");
    }

    // Helper Methods

    private void getItemDetails(GroceryItem item)
    {
        log.info(
                "Item Name: " + item.getName() +
                        ", Quantity: " + item.getQuantity() +
                        ", Item Category: " + item.getCategory()
        );
    }
}
