package com.example.ToDoApp.controllers;

import com.example.ToDoApp.model.ToDoItem;
import com.example.ToDoApp.repositories.ToDoItemRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Controller
public class ToDoController implements CommandLineRunner {

    private final ToDoItemRepository toDoItemRepository;

    public ToDoController(ToDoItemRepository toDoItemRepository) {
        this.toDoItemRepository = toDoItemRepository;
    }

    @GetMapping
    public String index(Model model){
        List<ToDoItem> allToDos = toDoItemRepository.findAll();
        model.addAttribute("allToDos", allToDos);
        model.addAttribute("newToDo", new ToDoItem());

        return "index";
    }

    @PostMapping("/add")
    public String add(ToDoItem toDoItem){
        toDoItemRepository.save(toDoItem);

        return "redirect:/";
    }

    @PostMapping("/delete/{id}")
    public String deleteToDoItem(@PathVariable("id") Long id){
        toDoItemRepository.deleteById(id);
        return "redirect:/";
    }

    @PostMapping("/removeAll")
    public String removeAllItem(){
        toDoItemRepository.deleteAll();
        return "redirect:/";
    }

    @PostMapping("/search")
    public String searchItem(@RequestParam("searchTerm") String searchTerm, Model model){
        List<ToDoItem> allItems = toDoItemRepository.findAll();
        List<ToDoItem> searchResults = new ArrayList<>();

        for (ToDoItem item: allItems){
            if (item.getTitle().toLowerCase().contains(searchTerm.toLowerCase())){
                searchResults.add(item);
            }
        }

        model.addAttribute("allToDos", searchResults);
        model.addAttribute("newToDo", new ToDoItem());
        model.addAttribute("searchTerm", searchTerm);

        return "index";
    }

    @Override
    public void run(String... args) throws Exception {
        toDoItemRepository.save(new ToDoItem("Item 1"));
        toDoItemRepository.save(new ToDoItem("Item 2"));
    }
}
