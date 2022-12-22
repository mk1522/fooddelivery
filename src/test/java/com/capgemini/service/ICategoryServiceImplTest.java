package com.capgemini.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doNothing;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.capgemini.entities.Category;
import com.capgemini.repo.ICategoryRepository;

@SpringBootTest
class ICategoryServiceImplTest {

	@MockBean
	ICategoryRepository repo;

	@Autowired
	ICategoryService service;

	Category cat1;
	Category cat2;
	Category cat3;
	Category cat4;
	Category cat5;

	@BeforeEach
	public void initialize() {
		cat1 = new Category("1", "Apple_Products");
		cat2 = new Category("2", "Books");
		cat3 = new Category("3", "Mobiles");
		cat4 = new Category("4", "cards");
		cat5 = new Category("5", "Action_Figurines");
	}

	@Test
	void addCategoryTest() throws Exception {
		Mockito.when(repo.save(cat1)).thenReturn(cat1);

		assertThat(service.addCategory(cat1)).isEqualTo(cat1);
	}

	@Test
	void updateCategoryTest() throws Exception {
		Mockito.when(repo.save(cat2)).thenReturn(cat2);
		Mockito.when(repo.save(cat3)).thenReturn(cat3);
		Mockito.when(repo.existsById(cat2.getCatId())).thenReturn(true);

		assertThat(service.updateCategory(cat2)).isEqualTo(cat2);
	}

	@Test
	void removeRestaurantTest() throws Exception {
		ArgumentCaptor<Category> valueCapture = ArgumentCaptor.forClass(Category.class);
		doNothing().when(repo).delete(valueCapture.capture());

		Mockito.when(repo.existsById(cat1.getCatId())).thenReturn(true);

		assertThat(service.removeCategory(cat1)).isEqualTo(cat1);
	}

	@Test
	void viewCategoryTest() throws Exception {
		Mockito.doReturn(Optional.of(cat1)).when(repo).findById(cat1.getCatId());
		assertThat(service.viewCategory(cat1)).isEqualTo(cat1);
	}

	@Test
	void viewAllCategory() throws Exception {
		List<Category> catL = new ArrayList<Category>();
		Mockito.when(repo.save(cat1)).thenReturn(cat1);
		Mockito.when(repo.save(cat2)).thenReturn(cat2);
		Mockito.when(repo.save(cat3)).thenReturn(cat3);
		Mockito.when(repo.save(cat4)).thenReturn(cat4);
		Mockito.when(repo.save(cat5)).thenReturn(cat5);
		Mockito.when(repo.findAll()).thenReturn(catL);
		assertThat(service.viewAllCategory()).isEqualTo(catL);
	}

}