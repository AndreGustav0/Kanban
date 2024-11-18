package com.atividade.kanban.repositories;

import com.atividade.kanban.entities.Kanban;
import com.atividade.kanban.entities.Prioridade;
import com.atividade.kanban.entities.Status;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface KanbanRepositories extends JpaRepository<Kanban, Long> {

    List<Kanban> findByStatus(Status status);

    List<Kanban> findByPrioridade(Prioridade prioridade);

    List<Kanban> findAllByOrderByDataLimiteAsc();

    List<Kanban> findAllByOrderByDataLimiteDesc();

    List<Kanban> findByDataLimite(LocalDate dataFinal);
}
