package ru.job4j.restxml.service;

import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ru.job4j.restxml.exception.ServiceException;
import ru.job4j.restxml.model.Account;
import ru.job4j.restxml.repository.AccountRepository;

@Service
@AllArgsConstructor
public class AccountService {

    private final AccountRepository accountRepository;
    private final BCryptPasswordEncoder encoder;

    public Account save(Account account) throws ServiceException {
        try {
            account.setPassword(encoder.encode(account.getPassword()));
            return accountRepository.save(account);
        } catch (DataIntegrityViolationException e) {
            throw new ServiceException("Пользователь с именем: " + account.getName() + " уже существует", e);
        } catch (Exception e) {
            throw new ServiceException("Неизвестная ошибка", e);
        }
    }
}
