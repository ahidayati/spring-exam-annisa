package com.hb.spring_exam_annisa.repositories;



import com.hb.spring_exam_annisa.models.Role;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends CrudRepository<Role, Long> {
    public Role findByRoleName(String roleName);
}
