package com.example.board.Service;

import com.example.board.Entity.Account;
import com.example.board.Repository.AccountRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AccountService {

    private AccountRepository repository;

    public AccountService(AccountRepository repository) {
        this.repository = repository;
    }

    public Optional<Account> logincheck(String userid){

        return repository.findByUserid(userid);

    }

    public void joinmember(String userid, String userpw){

        repository.joinmember(userid, userpw);

    }

}
