package com.capgemini.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.entities.Category;
import com.capgemini.entities.Item;
import com.capgemini.entities.Restaurant;
import com.capgemini.repo.IItemRepository;

@Service
public class ItemServiceImpl implements IItemService {

	@Autowired
	IItemRepository itemRepo;

	@Override
	public Item addItem(Item item) {
		return itemRepo.save(item);
	}

	@Override
	public Item updateItem(Item item) {
		Item itemUpdate = null;
		if (itemRepo.existsById(item.getItemId()) && itemRepo.findById(item.getItemId()).get().isActive())
			itemUpdate = itemRepo.save(item);
		return itemUpdate;
	}

	@Override
	public Item viewItem(Item item) {
		Item showItem = itemRepo.findById(item.getItemId()).get();
		if (showItem.isActive())
			return showItem;
		return null;
	}

	@Override
	public Item viewItem(String itemId) {
		Item showItem = itemRepo.findById(itemId).get();
		if (showItem.isActive())
			return showItem;
		return null;
	}

	@Override
	public Item removeItem(Item item) {
		Item delItem = item;
		if (itemRepo.existsById(item.getItemId())) {
			item.setActive(false);
			itemRepo.save(item);
			return delItem;
		}
		return null;
	}

	@Override
	public List<Item> viewAllItems(Category cat) {
		List<Item> list = itemRepo.viewAllItems(cat.getCatId(), cat.getCategoryName());
		List<Item> newList = new ArrayList<>();
		for (Item item : list) {
			if (item.isActive())
				newList.add(item);
		}
		return newList;
	}

	@Override
	public List<Item> viewAllItems(Restaurant res) {
		List<Item> list = itemRepo.viewAllItems(res.getRestaurantId());
		List<Item> newList = new ArrayList<>();
		for (Item item : list) {
			if (item.isActive())
				newList.add(item);
		}
		return newList;
	}

	@Override
	public List<Item> viewAllItemsByName(String name) {
		List<Item> list = itemRepo.viewAllItems(name);
		List<Item> newList = new ArrayList<>();
		for (Item item : list) {
			if (item.isActive())
				newList.add(item);
		}
		return newList;
	}

}
