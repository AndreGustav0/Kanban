package com.atividade.kanban.entities.User;

public record RegisterDTO(String login, String password, UserRole role) {
}
