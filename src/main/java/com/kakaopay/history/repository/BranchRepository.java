package com.kakaopay.history.repository;

import com.kakaopay.history.domain.Branch;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BranchRepository extends JpaRepository<Branch, Long> {

    Optional<Branch> findByBrCode(String s);
}
