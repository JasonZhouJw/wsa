package com.alpha.account.repository;

import com.alpha.account.entities.Permission;
import com.alpha.account.entities.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by jzhou237 on 2017-03-01.
 */
@RunWith(SpringJUnit4ClassRunner.class)
//@Transactional
@SpringBootTest
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PermissionRepository permissionRepository;

    @Test
    public void login() throws Exception {

    }

    @Test
    public void findByName() throws Exception {

    }

    @Test
    public void save() throws Exception {
        User user = new User();
        user.setName("admin");
        BCryptPasswordEncoder bc = new BCryptPasswordEncoder(4);
        user.setPassword(bc.encode("admin"));
        Permission permission = this.permissionRepository.findByName("Root");
        List<Permission> permissionList = new ArrayList<>();
        permissionList.add(permission);
        user.setPermissions(permissionList);
        userRepository.save(user);
    }

    @Test
    public void findAll() throws Exception {
        User user = new User();
//        user.setActive(true);
//        user.setName("admin");
        Page<User> result = userRepository.findAll(Example.of(user), new PageRequest(1, 10));
        assertEquals(1, result.getTotalElements());
    }

}