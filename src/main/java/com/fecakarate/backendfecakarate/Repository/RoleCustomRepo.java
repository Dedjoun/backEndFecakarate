package com.fecakarate.backendfecakarate.Repository;

import com.fecakarate.backendfecakarate.Models.Role;
import com.fecakarate.backendfecakarate.Models.Users;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.hibernate.Session;
import org.hibernate.query.NativeQuery;
import org.hibernate.transform.Transformers;
import org.hibernate.type.StandardBasicTypes;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class RoleCustomRepo {

    @PersistenceContext
    private EntityManager entityManager;

    public List<Role> getRole(Users users){
        StringBuffer sql = new StringBuffer()
                .append("SELECT r.name as name\n" +
                        "FROM users u\n" +
                        "JOIN user_role ur ON ur.user_id=ur.user_id\n" +
                        "JOIN roles r ON r.id = ur.role_id\n" +
        "Where 1=1");
        if (users.getEmail() != null){
            sql.append("and email=:email");
        }
        NativeQuery query = ((Session)entityManager.getDelegate()).createNativeQuery(sql.toString());
        if (users.getEmail()!=null){
            query.setParameter("email", users.getEmail());
        }
        query.addScalar("name", StandardBasicTypes.STRING);
        query.setResultTransformer(Transformers.aliasToBean(Role.class));
        return query.list();
    }



}
