package com.accommate.demo.controller;

import com.accommate.demo.model.item.Book;
import com.accommate.demo.model.item.Item;
import com.accommate.demo.service.ItemService;
import com.accommate.demo.service.UpdateBookDto;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@Slf4j
@RequiredArgsConstructor
public class ItemController {

    private final ItemService itemService;

    @GetMapping("/items/new")
    public String createForm(Model model) {
        model.addAttribute("form", new BookForm());
        return "items/createItemForm";
    }

    @PostMapping("/items/new")
    public String create(@Valid BookForm form, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "members/createMemberForm";
        }

        Book book = new Book();
        book.setName(form.getName());
        book.setPrice(form.getPrice());
        book.setStockQuantity(form.getStockQuantity());
        book.setAuthor(form.getAuthor());
        book.setIsbn(form.getIsbn());
        itemService.saveItem(book);

        return "redirect:/";
    }

    @GetMapping("/items")
    public String list(Model model) {
        List<Item> items = itemService.findItems();
        model.addAttribute("items", items);
        return "items/itemList";
    }

    @GetMapping("/items/{itemId}/edit")
    public String updateItemForm(@PathVariable("itemId") Long itemId, Model model) {
        Item findItem = itemService.findById(itemId);

        if (findItem instanceof Book) {
            BookForm bookForm = new BookForm();
            bookForm.setId(findItem.getId());
            bookForm.setName(findItem.getName());
            bookForm.setPrice(findItem.getPrice());
            bookForm.setStockQuantity(findItem.getStockQuantity());
            bookForm.setAuthor(((Book) findItem).getAuthor());
            bookForm.setIsbn(((Book) findItem).getIsbn());

            model.addAttribute("form", bookForm);
        } else {
            log.error("only book implemented update method");
        }

        return "items/updateItemForm";
    }

    @PostMapping("/items/{itemId}/edit")
    public String update(@PathVariable("itemId") Long itemId, @ModelAttribute("form") BookForm form, BindingResult bindingResult) {
        UpdateBookDto updateBookDto = new UpdateBookDto(form);

        itemService.updateBook(itemId, updateBookDto);
        return "redirect:/items";
    }


}
