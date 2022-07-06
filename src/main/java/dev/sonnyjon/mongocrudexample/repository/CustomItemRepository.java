package dev.sonnyjon.mongocrudexample.repository;

/**
 * Created by Sonny on 7/6/2022.
 */
public interface CustomItemRepository
{
    void updateItemQuantity(String name, float newQuantity);
}
