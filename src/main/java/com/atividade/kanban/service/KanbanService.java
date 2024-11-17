package com.atividade.kanban.service;

import com.atividade.kanban.entities.Kanban;
import com.atividade.kanban.repositories.KanbanRepositories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KanbanService {

    @Autowired
    private KanbanRepositories kanbanRepositories;

    public List<Kanban> buscarTodos(){
        return kanbanRepositories.findAll();
    }

    public Kanban buscarPorId(Long id){
        return kanbanRepositories.findById(id).orElse(null);
    }

    public Kanban criar(Kanban kanban){
        return kanbanRepositories.save(kanban);
    }

    public void deletar(Long id){
        kanbanRepositories.deleteById(id);
    }
}