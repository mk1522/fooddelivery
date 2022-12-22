package com.capgemini.service;

import java.util.List;

import com.capgemini.entities.Category;
import com.capgemini.entities.Item;
import com.capgemini.entities.Restaurant;

public interface IItemService {
	public Item addItem(Item item);

	public Item updateItem(Item item);

	public Item viewItem(Item item);

	public Item viewItem(String itemId);

	public Item removeItem(Item item);

	public List<Item> viewAllItems(Category cat);

	public List<Item> viewAllItems(Restaurant res);

	public List<Item> viewAllItemsByName(String name);
}