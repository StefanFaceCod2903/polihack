package org.example.backend;

import static org.junit.jupiter.api.Assertions.*;

import org.example.backend.item.ItemRepository;
import org.example.backend.item.ItemService;
import org.example.backend.item.model.Item;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ItemServiceTest {

  @Autowired private ItemService itemService;
  @Autowired private ItemRepository itemRepository;

  @BeforeEach
  void cleanup() {
    itemRepository.deleteAll();
  }

  @Test
  void findAll() {
    assertEquals(0, itemService.findAll().size());

    itemRepository.save(Item.builder().name("whatever").build());

    assertEquals(1, itemService.findAll().size());
  }
}
