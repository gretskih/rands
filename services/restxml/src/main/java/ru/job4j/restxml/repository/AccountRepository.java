package ru.job4j.restxml.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.job4j.restxml.model.Account;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Long> {
    Optional<Account> findByName(String username);
}
