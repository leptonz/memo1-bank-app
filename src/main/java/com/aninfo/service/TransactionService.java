package com.aninfo.service;

import com.aninfo.exceptions.DepositNegativeSumException;
import com.aninfo.exceptions.InsufficientFundsException;
import com.aninfo.model.Account;
import com.aninfo.model.Transaction;
import com.aninfo.repository.AccountRepository;
import com.aninfo.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.Optional;

@Service
public class TransactionService {

    @Autowired
    public AccountService accountService;

    @Autowired
    private TransactionRepository transactionRepository;

    public Transaction hacerDeposito(Long cbu, Double monto) {

        Transaction nuevoDeposito = new Transaction();
        Account cuentaDeLaTransaccion = accountService.deposit(cbu, monto);

        nuevoDeposito.setTipoDeTransaccion("deposito");
        nuevoDeposito.setMonto(monto);
        nuevoDeposito.setCuenta(cuentaDeLaTransaccion);

        return transactionRepository.save(nuevoDeposito);
    }

    public Transaction hacerRetiro(long cbu, double monto){

        Transaction nuevoRetiro = new Transaction();
        Account cuentaDeLaTransaccion =  accountService.withdraw(cbu, monto);

        nuevoRetiro.setTipoDeTransaccion("retiro");
        nuevoRetiro.setMonto(monto);
        nuevoRetiro.setCuenta(cuentaDeLaTransaccion);

        return transactionRepository.save(nuevoRetiro);
    }



    public Collection<Transaction> getTransactions() {
        return transactionRepository.findAll();
    }

    public Optional<Transaction> findById(Long numeroDeTransaccion) {
        return transactionRepository.findById(numeroDeTransaccion);
    }

    public void deleteById(Long cbu) {
        transactionRepository.deleteById(cbu);
    }

}
