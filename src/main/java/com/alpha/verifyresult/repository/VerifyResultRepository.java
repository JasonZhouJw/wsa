package com.alpha.verifyresult.repository;

import com.alpha.verifyresult.entities.VerifyResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by jzhou237 on 9/16/2016.
 */
@Repository
public interface VerifyResultRepository extends JpaRepository<VerifyResult, Long> {
}
