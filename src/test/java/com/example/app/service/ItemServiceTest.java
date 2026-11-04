package com.example.app.service;

import com.example.app.dto.ItemRequest;
import com.example.app.dto.ItemResponse;
import com.example.app.exception.ResourceNotFoundException;
import com.example.app.model.Item;
import com.example.app.repository.ItemRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ItemServiceTest {

    @Mock
    private ItemRepository itemRepository;

    @InjectMocks
    private ItemService itemService;

    @Test
    void findAll_returnsMappedResponses() {
        when(itemRepository.findAll()).thenReturn(List.of(
                Item.builder().id(1L).name("Widget").description("A widget").build()
        ));

        List<ItemResponse> result = itemService.findAll();

        assertThat(result).hasSize(1);
        assertThat(result.get(0).getName()).isEqualTo("Widget");
    }

    @Test
    void findById_notFound_throwsException() {
        when(itemRepository.findById(99L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> itemService.findById(99L))
                .isInstanceOf(ResourceNotFoundException.class);
    }

    @Test
    void create_savesAndReturnsResponse() {
        ItemRequest request = new ItemRequest();
        request.setName("Gadget");
        request.setDescription("A gadget");

        Item saved = Item.builder().id(1L).name("Gadget").description("A gadget").build();
        when(itemRepository.save(any(Item.class))).thenReturn(saved);

        ItemResponse response = itemService.create(request);

        assertThat(response.getId()).isEqualTo(1L);
        assertThat(response.getName()).isEqualTo("Gadget");
    }

    @Test
    void delete_notFound_throwsException() {
        when(itemRepository.existsById(5L)).thenReturn(false);

        assertThatThrownBy(() -> itemService.delete(5L))
                .isInstanceOf(ResourceNotFoundException.class);
    }
}
