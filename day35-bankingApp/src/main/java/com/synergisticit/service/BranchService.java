package com.synergisticit.service;

import java.util.List;

import com.synergisticit.domain.Branch;

public interface BranchService {
    Branch saveBranch(Branch branch);
    Branch getBranchById(Long branchId);
    List<Branch> getAllBranches();
    void deleteBranchById(Long branchId);
    boolean existById(Long branchId);
}
