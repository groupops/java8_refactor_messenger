package com.epam.pizzeria.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;
import com.epam.pizzeria.model.Category;
import com.epam.pizzeria.model.Good;
import com.epam.pizzeria.service.GoodService;

@Controller
@RequestMapping("/shop")
// @SessionAttributes(types = Basket.class)
@Transactional
public class ShopController {
	@Autowired
	GoodService service;

	public ShopController(GoodService service) {
		this.service = service;
	}

	public ShopController() {
	}

	@RequestMapping(method = RequestMethod.GET)
	public String categories(ModelMap model) {
		List<Category> cats = service.getCategoryList();
		model.addAttribute("cats", cats);
		model.addAttribute("title", "Categories");

		return "categories";
	}

	@RequestMapping(value = "/category/{id}", method = RequestMethod.GET)
	public String goods(@PathVariable(value = "id") Integer id, ModelMap model) {
		List<Good> goods = service.getList(id);
		Category cat = service.findCategory(id);
		model.addAttribute("goods", goods);
		model.addAttribute("title", cat.getName());

		return "goodlist";
	}

	// @RequestMapping(value = "/add/{catId}/{id}", method = RequestMethod.GET)
	// public String addToBasket(
	// @PathVariable(value = "catId")
	// Integer catId,
	// @PathVariable(value = "id")
	// Integer id,
	// ModelMap model) {
	//
	// Basket basket = (Basket) model.get("basket");
	// if (basket == null) basket = new Basket();
	//
	//
	// BasketItem i = basket.get(id);
	// if (i == null) {
	// i = new BasketItem();
	// i.setGood(srv.find(id));
	// } else {
	// i.incCount();
	// }
	//
	// if (i.getGood() != null)
	// basket.put(id, i);
	//
	// model.addAttribute("basket", basket);
	// return "redirect:/shop/category/" + catId.toString();
	// }
}