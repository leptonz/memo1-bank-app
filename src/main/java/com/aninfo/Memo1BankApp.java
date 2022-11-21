package com.aninfo;

import com.aninfo.model.Account;
import com.aninfo.model.Transaction;
import com.aninfo.service.AccountService;
import com.aninfo.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Optional;
import java.util.List;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
@EnableJpaRepositories
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@SpringBootApplication
@EnableSwagger2


public class Memo1BankApp {

	@Autowired
	private AccountService accountService;

	@Autowired
	private TransactionService transactionService;

	public static void main(String[] args) {
		SpringApplication.run(Memo1BankApp.class, args);
	}

	@PostMapping("/accounts")
	@ResponseStatus(HttpStatus.CREATED)
	public Account createAccount(@RequestBody Account account) {
		return accountService.createAccount(account);
	}

	@GetMapping("/accounts")
	public Collection<Account> getAccounts() {
		return accountService.getAccounts();
	}

	@GetMapping("/accounts/{cbu}")
	public ResponseEntity<Account> getAccount(@PathVariable Long cbu) {
		Optional<Account> accountOptional = accountService.findById(cbu);
		return ResponseEntity.of(accountOptional);
	}

	@PutMapping("/accounts/{cbu}")
	public ResponseEntity<Account> updateAccount(@RequestBody Account account, @PathVariable Long cbu) {
		Optional<Account> accountOptional = accountService.findById(cbu);

		if (!accountOptional.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		account.setCbu(cbu);
		accountService.save(account);
		return ResponseEntity.ok().build();
	}

	@DeleteMapping("/accounts/{cbu}")
	public void deleteAccount(@PathVariable Long cbu) {
		accountService.deleteById(cbu);
	}

	@PostMapping("/transactions")
	public Transaction createTransaction(@RequestBody Transaction transaction) {
		if (transaction.getTipoDeTransaccion().equalsIgnoreCase("deposito")) {
			return (transactionService.hacerDeposito(transaction));
		}
		return (transactionService.hacerRetiro(transaction));
	}
	@GetMapping("/transactions/search/{cbu}")
	public List<Transaction> buscarTransaccionesPorCBU(@PathVariable Long cbu) {
		return transactionService.obtenerTransaccionesDeCuenta(cbu);
	}
	@GetMapping("/transactions/{numeroDeTransaccion}")
	public Transaction buscarTransaccionPorNumeroDeTransaccion(@PathVariable Long numeroDeTransaccion) {
		return transactionService.obtenerTransaccionPorSuNumero(numeroDeTransaccion);
	}
	@DeleteMapping("/transactions/{numeroDeTransaccion}")
	public void borrarTransaccionPorNumeroDeTransaccion(@PathVariable Long numeroDeTransaccion) {
		transactionService.borrarTransaccion(numeroDeTransaccion);
		return;
	}
		@Bean
		public Docket apiDocket () {
			return new Docket(DocumentationType.SWAGGER_2)
					.select()
					.apis(RequestHandlerSelectors.any())
					.paths(PathSelectors.any())
					.build();
		}
	}
