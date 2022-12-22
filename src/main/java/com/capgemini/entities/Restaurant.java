package com.capgemini.entities;

import java.util.List;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Restaurant {
	@Id
	private String restaurantId;
	private String restaurantName;
	private boolean isActive = true;

	@Embedded
	private Address address;
	// many to many
	@ManyToMany
	@JoinTable(name = "RESTAURANT_ITEM_TABLE", joinColumns = {
			@JoinColumn(name = "restaurant_id", referencedColumnName = "restaurantId") }, inverseJoinColumns = {
					@JoinColumn(name = "item_id", referencedColumnName = "itemId") })
	private List<Item> itemList;
	private String managerName;
	private String contactNumber;

	public Restaurant() {
		super();
	}

	public Restaurant(String restaurantId, String restaurantName, Address address, List<Item> itemList,
			String managerName, String contactNumber) {
		super();
		this.restaurantId = restaurantId;
		this.restaurantName = restaurantName;
		this.address = address;
		this.itemList = itemList;
		this.managerName = managerName;
		this.contactNumber = contactNumber;
	}

	public void addItem(Item item) {
		itemList.add(item);
	}

}
