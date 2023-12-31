package com.fzoo.zoomanagementsystem.repository;

import com.fzoo.zoomanagementsystem.model.Account;
import com.fzoo.zoomanagementsystem.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Integer> {
    boolean existsByEmail(String email);

    void deleteAccountByEmail(String email);

    Account findAccountByEmail(String email);

    Optional<Account> findByEmail(String username);

    Account findAccountByRole(Role admin);
}
