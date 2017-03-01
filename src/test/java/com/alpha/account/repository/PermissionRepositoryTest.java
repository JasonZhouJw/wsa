package com.alpha.account.repository;

import com.alpha.account.entities.Permission;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by jzhou237 on 2017-03-01.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class PermissionRepositoryTest {

    @Autowired
    PermissionRepository permissionRepository;

    @Test
    public void save() throws Exception {
        Permission permission = new Permission();
        permission.setUrl("\\");
        permission.setName("Root");
        this.permissionRepository.save(permission);
    }
}