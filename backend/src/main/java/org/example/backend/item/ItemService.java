package org.example.backend.item;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.example.backend.item.model.Item;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ItemService {

  private final ItemRepository itemRepository;

  public List<Item> findAll() {
    return itemRepository.findAll();
  }
}
