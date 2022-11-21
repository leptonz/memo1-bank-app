package com.aninfo.service;

import com.aninfo.exceptions.NoSeEncuentraLaTransaccionException;
import com.aninfo.model.Account;
import com.aninfo.model.Transaction;
import com.aninfo.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TransactionService {

    @Autowired
    public AccountService accountService;

    @Autowired
    private TransactionRepository transactionRepository;

    public Transaction hacerDeposito(Transaction transaction) {

        Account cuentaDeLaTransaccion = accountService.deposit(transaction.getCbuCuenta(), transaction.getMonto());

        return transactionRepository.save(transaction);
    }

    public Transaction hacerRetiro(Transaction transaction){


        Account cuentaDeLaTransaccion = accountService.withdraw(transaction.getCbuCuenta(), transaction.getMonto());

        return transactionRepository.save(transaction);
    }

    public List<Transaction> obtenerTransaccionesDeCuenta(Long cbu){

        return(transactionRepository.findAllByCbuCuenta(cbu));
    }

    public Transaction obtenerTransaccionPorSuNumero(Long numeroDeTransaccion){
        Optional<Transaction> transaccionBuscada = transactionRepository.findById(numeroDeTransaccion);
        return transaccionBuscada.orElse(null);
    }

    public void borrarTransaccion(Long numeroDeTransaccion){
        Transaction transaccionABorrar;
        String tipoDeTransaccion;

        if(!transactionRepository.existsById(numeroDeTransaccion)){
            throw new NoSeEncuentraLaTransaccionException("La transaccion que quiere borrar no existe");
        }

        transaccionABorrar = (transactionRepository.findById(numeroDeTransaccion)).get();
        tipoDeTransaccion = transaccionABorrar.getTipoDeTransaccion();
//

        if(tipoDeTransaccion.equals("deposito")){
            accountService.withdraw(transaccionABorrar.getCbuCuenta(), transaccionABorrar.getMonto());
        }

        if(tipoDeTransaccion.equals("retiro")){
            accountService.deposit(transaccionABorrar.getCbuCuenta(), transaccionABorrar.getMonto());
        }

        transactionRepository.deleteById(numeroDeTransaccion);
        return;
    }



}
