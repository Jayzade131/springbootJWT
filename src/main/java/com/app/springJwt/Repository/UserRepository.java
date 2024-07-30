package com.app.springJwt.Repository;


import com.app.springJwt.model.Role;
import com.app.springJwt.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {


    Optional<User> findByUserName(String userName);


    User findByRole(Role role);

}
