package com.kakaopay.history.repository.branch;

import com.kakaopay.history.dto.BranchDto;

import java.util.List;

public interface BranchRepositoryCustom {

        List<BranchDto> findBranchByBrCode(String brCode);
}
