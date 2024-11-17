package com.atividade.kanban.controller;

import com.atividade.kanban.entities.Kanban;
import com.atividade.kanban.service.KanbanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/kanban")
public class KanbanController {

    @Autowired
    private KanbanService kanbanService;

    @GetMapping
    public List<Kanban> buscarTodos(){
        return kanbanService.buscarTodos();
    }

    @GetMapping("/{id}")
    public Kanban buscarPorId(@PathVariable Long id){
        return kanbanService.buscarPorId(id);
    }

    @PostMapping
    public Kanban criar(@RequestBody Kanban kanban){
        return kanbanService.criar(kanban);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id){
        kanbanService.deletar(id);
    }
}
