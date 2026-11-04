package com.example.app.service;

import com.example.app.dto.ItemRequest;
import com.example.app.dto.ItemResponse;
import com.example.app.exception.ResourceNotFoundException;
import com.example.app.model.Item;
import com.example.app.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;

    public List<ItemResponse> findAll() {
        return itemRepository.findAll().stream()
                .map(this::toResponse)
                .toList();
    }

    public ItemResponse findById(Long id) {
        return itemRepository.findById(id)
                .map(this::toResponse)
                .orElseThrow(() -> new ResourceNotFoundException("Item not found with id: " + id));
    }

    public ItemResponse create(ItemRequest request) {
        Item item = Item.builder()
                .name(request.getName())
                .description(request.getDescription())
                .build();
        return toResponse(itemRepository.save(item));
    }

    public ItemResponse update(Long id, ItemRequest request) {
        Item item = itemRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Item not found with id: " + id));
        item.setName(request.getName());
        item.setDescription(request.getDescription());
        return toResponse(itemRepository.save(item));
    }

    public void delete(Long id) {
        if (!itemRepository.existsById(id)) {
            throw new ResourceNotFoundException("Item not found with id: " + id);
        }
        itemRepository.deleteById(id);
    }

    private ItemResponse toResponse(Item item) {
        return ItemResponse.builder()
                .id(item.getId())
                .name(item.getName())
                .description(item.getDescription())
                .build();
    }
}
