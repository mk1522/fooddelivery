package com.capgemini.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.capgemini.entities.Item;

@Repository
public interface IItemRepository extends JpaRepository<Item, String> {
	@Query(value = "select * from item where cat_id = (select cat_id from category where cat_id=?1)", nativeQuery = true)
	public List<Item> viewAllItems(String catId, String catName);

	@Query(value = "select * from item where item_id in (select item_id from restaurant_item_table where restaurant_id=?1)", nativeQuery = true)
	public List<Item> viewAllItems(String resId);

	@Query(value = "select * from item where item_name=?1", nativeQuery = true)
	public List<Item> viewAllItemsByName(String name);
}