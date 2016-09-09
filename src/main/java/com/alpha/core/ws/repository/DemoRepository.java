package com.alpha.core.ws.repository;

import com.alpha.core.ws.entity.Demo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by jzhou237 on 9/8/2016.
 */
@Repository
public interface DemoRepository extends JpaRepository<Demo, Long> {
}
