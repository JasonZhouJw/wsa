package com.alpha.account.repository;

import com.alpha.account.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Created by jzhou237 on 2017-02-21.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query("select user from User user where user.name=:name or user.phone=:name and user.password=:password")
    User login(@Param("name") String name, @Param("password") String password);

    User findByName(String name);
}
