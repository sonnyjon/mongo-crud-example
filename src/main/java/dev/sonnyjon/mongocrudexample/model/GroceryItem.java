package dev.sonnyjon.mongocrudexample.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by Sonny on 7/6/2022.
 */
@Getter
@Setter
@AllArgsConstructor
@Document("groceryitems")
public class GroceryItem
{
    @Id
    private String id;

    private String name;
    private int quantity;
    private String category;
}
