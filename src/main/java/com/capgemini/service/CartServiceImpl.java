package com.capgemini.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.entities.FoodCart;
import com.capgemini.entities.Item;
import com.capgemini.repo.ICartRepository;

@Service
public class CartServiceImpl implements ICartService {

	@Autowired
	ICartRepository cartRepo;

	@Override
	public FoodCart addItemToCart(FoodCart cart, Item item) {
		FoodCart repoCart = cartRepo.findById(cart.getCartId()).get();
		List<Item> tempItems = repoCart.getItemList();
		tempItems.add(item);
		repoCart.setItemList(tempItems);
		cartRepo.save(repoCart);
		return repoCart;
	}

	@Override
	public FoodCart increaseQuantity(FoodCart cart, Item item, int quantity) {
		FoodCart repoCart = cartRepo.findById(cart.getCartId()).get();
		List<Item> tempItems = repoCart.getItemList();
		for (Item it : tempItems) {
			if (it.getItemId().equals(item.getItemId())) {
				int quant = it.getQuantity();
				it.setQuantity(quant + quantity);
				break;
			}

		}
		repoCart.setItemList(tempItems);
		cartRepo.save(repoCart);
		return repoCart;
	}

	@Override
	public FoodCart reduceQuantity(FoodCart cart, Item item, int quantity) {
		FoodCart repoCart = cartRepo.findById(cart.getCartId()).get();
		List<Item> tempItems = repoCart.getItemList();
		for (Item it : tempItems) {
			if (it.getItemId().equals(item.getItemId())) {
				int quant = it.getQuantity();
				it.setQuantity(Math.max((quant - quantity), 0));
				break;
			}
		}
		repoCart.setItemList(tempItems);
		cartRepo.save(repoCart);
		return repoCart;
	}

	@Override
	public FoodCart removeItem(FoodCart cart, Item item) {
		FoodCart repoCart = cartRepo.findById(cart.getCartId()).get();
		List<Item> tempItems = repoCart.getItemList();
		tempItems.remove(item);
		repoCart.setItemList(tempItems);
		cartRepo.save(repoCart);
		return repoCart;
	}

	@Override
	public FoodCart clearCart(FoodCart cart) {
		FoodCart repoCart = cartRepo.findById(cart.getCartId()).get();
		List<Item> tempItems = repoCart.getItemList();
		tempItems.clear();
		repoCart.setItemList(tempItems);
		cartRepo.save(repoCart);
		return repoCart;
	}

}