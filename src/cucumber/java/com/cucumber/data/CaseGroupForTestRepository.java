package com.cucumber.data;

import com.alpha.testcase.repository.CaseGroupRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Created by jzhou237 on 2017-04-03.
 */
@Repository
public interface CaseGroupForTestRepository extends CaseGroupRepository {

    @Modifying
    @Query("delete from CaseGroup caseGroup where caseGroup.name=:name")
    void deleteByName(@Param("name") String savedName);
}
