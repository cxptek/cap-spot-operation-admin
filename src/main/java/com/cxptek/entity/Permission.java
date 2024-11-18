package com.cxptek.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
public class Permission implements Serializable {
    @Id
    private String name;

    @JsonIgnore
    @ToString.Exclude
    @ManyToMany(mappedBy = "permissions")
    private List<Role> roles = new ArrayList<>();

    public Permission() {}

    public Permission(String name) {
        this.name = name;
    }
}
