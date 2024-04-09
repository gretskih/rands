package ru.job4j.restxml.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.job4j.restxml.dto.AccountDTO;
import ru.job4j.restxml.exception.ControllerException;
import ru.job4j.restxml.exception.ServiceException;
import ru.job4j.restxml.model.Account;
import ru.job4j.restxml.service.AccountService;

@RestController
@RequestMapping("/account")
@AllArgsConstructor
public class AccountController {

	private final AccountService accountService;

	@PostMapping("/new")
	@PreAuthorize("hasAuthority('SCOPE_ADMIN')")
	public ResponseEntity<AccountDTO> newAccount(@RequestBody Account account) throws ControllerException {
		try {
			var accountOptional = accountService.save(account);
			return ResponseEntity
					.status(HttpStatus.CREATED)
					.body(new AccountDTO(accountOptional.getName(), accountOptional.getRoles()));
		} catch (ServiceException e) {
			throw new ControllerException(e.getMessage(), e);
		}
	}
}
