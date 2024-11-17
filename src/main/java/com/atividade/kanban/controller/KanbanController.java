package com.atividade.kanban.controller;

import com.atividade.kanban.entities.Kanban;
import com.atividade.kanban.service.KanbanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/tasks")
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

    @GetMapping("/a-fazer")
    public List<Kanban> buscarAFazer(){
        return kanbanService.buscarAFazer();
    }

    @GetMapping("/fazendo")
    public List<Kanban> buscarFazendo(){
        return kanbanService.buscarFazendo();
    }

    @GetMapping("/concluido")
    public List<Kanban> buscarConcluido(){
        return kanbanService.buscarConcluido();
    }

    @PutMapping("/{id}/move")
    public ResponseEntity<Kanban> atualizarStatus(@PathVariable Long id) {
        Optional<Kanban> tarefaAtualizada = kanbanService.atualizarStatus(id);
        return tarefaAtualizada
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Kanban> atualizarKanban(@PathVariable Long id, @RequestBody Kanban kanbanAtualizado) {
        Optional<Kanban> kanbanAtualizada = kanbanService.atualizarKanban(id, kanbanAtualizado);
        return kanbanAtualizada
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
