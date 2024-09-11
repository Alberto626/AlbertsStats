package com.example.AlbertStats.Entities;

import java.util.List;
import java.util.Objects;

public class Hero {
    private int id;
    private String name;
    private String primaryAttribute;
    private List<String> roles;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrimaryAttribute() {
        return primaryAttribute;
    }

    public void setPrimaryAttribute(String primaryAttribute) {
        this.primaryAttribute = primaryAttribute;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Hero hero = (Hero) o;
        return id == hero.id && Objects.equals(name, hero.name) && Objects.equals(primaryAttribute, hero.primaryAttribute) && Objects.equals(roles, hero.roles);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, primaryAttribute, roles);
    }

    @Override
    public String toString() {
        return "" + id + " " + name + " " + primaryAttribute + " " + roles;
    }
}
