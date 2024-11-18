package com.cxptek.repository;

import com.cxptek.entity.Role;

public interface RoleRepository extends BaseRepository<Role, String> {
    Role findAllByName(String name);
}
