package com.cxptek.repository;


import com.cxptek.entity.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends BaseRepository<User, Long> {
    boolean existsUserById(Long id);
}
