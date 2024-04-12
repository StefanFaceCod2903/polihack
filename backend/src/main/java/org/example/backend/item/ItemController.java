package org.example.backend.item;

import static org.example.backend.UrlMapping.ITEMS;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.example.backend.item.model.Item;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(ITEMS)
@RequiredArgsConstructor
public class ItemController {

  private final ItemService itemService;

  @GetMapping
  public List<Item> findAll() {
    return itemService.findAll();
  }
}
