package com.alpha.account.repository;

import com.alpha.account.entities.Permission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by jzhou237 on 2017-03-01.
 */
@Repository
public interface PermissionRepository extends JpaRepository<Permission, Long> {

    Permission findByName(String name);

}
