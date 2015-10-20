package com.epam.pizzeria.service;

import org.springframework.transaction.annotation.Transactional;
import com.epam.pizzeria.model.Category;
import com.epam.pizzeria.model.Good;

import java.util.List;

@Transactional
public interface GoodService {
    public List<Good> getList(Integer id);
    public List<Category> getCategoryList();
    public Good find(Integer id);
    public Category findCategory(Integer id);
    public List<Good> getToppings();
}
