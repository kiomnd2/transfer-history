package com.kakaopay.history.repository.history;

import com.kakaopay.history.domain.History;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HistoryRepository extends JpaRepository<History, Long>, HistoryRepositoryCustom{
}
