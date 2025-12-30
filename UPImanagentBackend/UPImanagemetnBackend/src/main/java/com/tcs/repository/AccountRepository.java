package com.tcs.repository;

import com.tcs.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Long> {
	Optional<Account> findByAccountNumber(String accountNumber);
    List<Account> findByUserId(Long userId);
    Optional<Account> findByUserIdAndType(Long userId, String type);

}
