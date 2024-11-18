package com.atividade.kanban.service;

import com.atividade.kanban.entities.Kanban;
import com.atividade.kanban.entities.Prioridade;
import com.atividade.kanban.entities.Status;
import com.atividade.kanban.repositories.KanbanRepositories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class KanbanService {

    @Autowired
    private KanbanRepositories kanbanRepositories;

    public List<Kanban> buscarTodos(){
        List<Kanban> result = new ArrayList<>();
        List<Kanban> a_fazer =  kanbanRepositories.findByStatus(Status.A_FAZER);
        List<Kanban> fazendo =  kanbanRepositories.findByStatus(Status.FAZENDO);
        List<Kanban> consluido =  kanbanRepositories.findByStatus(Status.CONCLUIDO);
        a_fazer.forEach(tarefa -> result.add(tarefa));
        fazendo.forEach(tarefa -> result.add(tarefa));
        consluido.forEach(tarefa -> result.add(tarefa));
        return result;
    }

    public List<Kanban> ordenarPrioridade(){
        List<Kanban> result = new ArrayList<>();
        List<Kanban> alta = kanbanRepositories.findByPrioridade(Prioridade.ALTA);
        List<Kanban> media = kanbanRepositories.findByPrioridade(Prioridade.MEDIA);
        List<Kanban> baixa = kanbanRepositories.findByPrioridade(Prioridade.BAIXA);
        alta.forEach(tarefa -> result.add(tarefa));
        media.forEach(tarefa -> result.add(tarefa));
        baixa.forEach(tarefa -> result.add(tarefa));
        return result;
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

    public List<Kanban> buscarAFazer(){
        return kanbanRepositories.findByStatus(Status.A_FAZER);
    }

    public List<Kanban> buscarFazendo(){
        return kanbanRepositories.findByStatus(Status.FAZENDO);
    }

    public List<Kanban> buscarConcluido(){
        return kanbanRepositories.findByStatus(Status.CONCLUIDO);
    }

    public Optional<Kanban> atualizarStatus(Long id){
        Optional<Kanban> kanban = kanbanRepositories.findById(id);
        if(kanban.isPresent()){
            Kanban tarefa = kanban.get();
            Status statusAtual = tarefa.getStatus();

            if (statusAtual == Status.A_FAZER) {
                tarefa.setStatus(Status.FAZENDO);
            } else if (statusAtual == Status.FAZENDO) {
                tarefa.setStatus(Status.CONCLUIDO);
            }

            return Optional.of(kanbanRepositories.save(tarefa));
        }
        return Optional.empty();
    }

    public Optional<Kanban> atualizarKanban(Long id, Kanban kanbanAtualizado) {
        Optional<Kanban> kanban = kanbanRepositories.findById(id);
        if (kanban.isPresent()) {
            Kanban tarefa = kanban.get();

            if (kanbanAtualizado.getTitulo() != null) {
                tarefa.setTitulo(kanbanAtualizado.getTitulo());
            }
            if (kanbanAtualizado.getDescricao() != null) {
                tarefa.setDescricao(kanbanAtualizado.getDescricao());
            }
            if (kanbanAtualizado.getPrioridade() != null) {
                tarefa.setPrioridade(kanbanAtualizado.getPrioridade());
            }
            if (kanbanAtualizado.getDataLimite() != null) {
                tarefa.setDataLimite(kanbanAtualizado.getDataLimite());
            }

            return Optional.of(kanbanRepositories.save(tarefa));
        }
        return Optional.empty();
    }

    public List<Kanban> listarPorDataDecrescente() {
        return kanbanRepositories.findAllByOrderByDataLimiteDesc();
    }

    public List<Kanban> listarPorDataCrescente(){
        return kanbanRepositories.findAllByOrderByDataLimiteAsc();
    }

    public List<Kanban> listarPrioridadeAlta(){
        return kanbanRepositories.findByPrioridade(Prioridade.ALTA);
    }

    public List<Kanban> listarPrioridadeMedia(){
        return kanbanRepositories.findByPrioridade(Prioridade.MEDIA);
    }

    public List<Kanban> listPrioridadeBaixa(){
        return kanbanRepositories.findByPrioridade(Prioridade.BAIXA);
    }
}