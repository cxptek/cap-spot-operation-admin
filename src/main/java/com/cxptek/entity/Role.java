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

import static jakarta.persistence.FetchType.EAGER;


@Getter
@Setter
@Entity
public class Role implements Serializable {
    @Id
    private String name;

    @JsonIgnore
    @ToString.Exclude
    @ManyToMany(fetch = EAGER)
    private List<Permission> permissions = new ArrayList<>();

    public Role() {}

    public Role(String name) {
        this.name = name;
    }
}
