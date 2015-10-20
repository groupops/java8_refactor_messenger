package com.epam.pizzeria.service;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import com.epam.pizzeria.model.Category;
import com.epam.pizzeria.model.Good;

@Repository(value = "goodDAO")
@Transactional
//@Configuration
public class GoodDAO implements GoodService {

    @Autowired
    @Qualifier(value = "sessionFactory")
    SessionFactory sf;
    public List<Good> getList(Integer id) {
        Query q = sf.getCurrentSession().createQuery("from Good g where g.category.id = :catId");
        q.setInteger("catId", id);
        return q.list();
    }

    public List<Category> getCategoryList() {
        Query q = sf.getCurrentSession().createQuery("from Category");
        return q.list();
    }

    public Good find(Integer id) {
        return (Good) sf.getCurrentSession().get(Good.class, id);
    }

    public List<Good> getToppings() {
        String query = "select from Goods where category='2'";
        return sf.getCurrentSession().createQuery(query).list();
    }

    public Category findCategory(Integer id) {
        return (Category) sf.getCurrentSession().get(Category.class, id);
    }


}
