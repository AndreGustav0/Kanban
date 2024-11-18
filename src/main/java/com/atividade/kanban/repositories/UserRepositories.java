package com.atividade.kanban.repositories;

import com.atividade.kanban.entities.User.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserRepositories extends JpaRepository<User, String> {
    UserDetails findByLogin(String login);
}
