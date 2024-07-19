package it.testfinale.dao;

import org.springframework.data.repository.CrudRepository;

import it.testfinale.model.Role;

public interface RoleDao extends CrudRepository<Role, Integer> {

}
