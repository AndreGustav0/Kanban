package com.atividade.kanban.repositories;

import com.atividade.kanban.entities.Kanban;
import org.springframework.data.jpa.repository.JpaRepository;

public interface KanbanRepositories extends JpaRepository<Kanban, Long> {
}
