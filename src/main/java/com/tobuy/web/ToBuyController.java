package com.tobuy.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tobuy.domain.Product;
import com.tobuy.domain.ProductList;
import com.tobuy.service.ToBuyService;

@Controller
public class ToBuyController {

	@Autowired
	private ToBuyService toBuyService;
	
	@RequestMapping("/")
	public String home(Map<String, Object> model) {
		String userName = "ololo";
		List<ProductList> lists = toBuyService.getAllUndoneLists(userName);
		
		model.put("productLists", lists);
		model.put("name", "Ololo");
		return "home";
	}

	@ResponseBody
	@RequestMapping("/create-new-list")
	public Map<String, Object> createNewList() {
		String userName = "ololo";
		ProductList list = toBuyService.createNewListForUser(userName);
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("listId", list.getProductListId());
		result.put("date", list.getDate());
		return result;
	}
	
	@ResponseBody
	@RequestMapping("/add-product")
	public Map<String, Object> addProductToList(long productListId, String productName) {
		Product product = toBuyService.addProductToList(productListId, productName);
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("productId", product.getProductId());
		return result;
	}
	
	@ResponseBody
	@RequestMapping("/done")
	public Map<String, Object> markAsDone(long productListId) {
		toBuyService.markAsDone(productListId);
		Map<String, Object> result = new HashMap<String, Object>();
		return result;
	}

	
	
}
