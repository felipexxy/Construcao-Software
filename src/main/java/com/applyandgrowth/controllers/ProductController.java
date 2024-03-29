package com.applyandgrowth.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.applyandgrowth.models.Product;
import com.applyandgrowth.models.User;
import com.applyandgrowth.repository.ProductRepository;
import com.applyandgrowth.repository.UserRepository;
import com.applyandgrowth.util.UploadUtil;

import jakarta.validation.Valid;

@Controller
public class ProductController {

	@Autowired
	private ProductRepository pr;
	@Autowired
	private UserRepository ur;

	@GetMapping("/myProducts")
	public ModelAndView productsAdvertiser() {
		ModelAndView mv = new ModelAndView("my_products_advertiser");
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		User user = ur.findByEmail(authentication.getName());
		Iterable<Product> products = pr.findByUser_id(user.getId());
		mv.addObject("products", products);
		return mv;
	}

	@GetMapping("/deleteProduct")
	public String deleteProduct(@RequestParam int id) {
		Product product = pr.findById(id);
		pr.delete(product);
		return "redirect:/myProducts";
	}

	@GetMapping("/createSale")
	public String sale() {
		return "create_sale";
	}

	@GetMapping("/editSale")
	public ModelAndView editSale(@RequestParam int id) {
		ModelAndView mv = new ModelAndView("edit_sale");
		Product products = pr.findById(id);
		mv.addObject("products", products);
		return mv;
	}

	@PostMapping("/editSale")
	public String editProduct(@Valid Product product, BindingResult br, RedirectAttributes attributes,
			@RequestParam("file") MultipartFile image) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		Product oldProduct = pr.findById(product.getId());

		if (br.hasErrors())
			return "redirect:/myProducts?error";
		else {
			
			if (UploadUtil.fazerUploadImagem(image)) 
				product.setImg(image.getOriginalFilename());
			else if(product.getImg()==null)
				product.setImg(oldProduct.getImg());
			
			User user = ur.findByEmail(authentication.getName());
			product.setOldDataAndTime(oldProduct.getDateAndTime());
			product.setUser(user);
			pr.save(product);
			return "redirect:/myProducts";
		}

	}

	@PostMapping("/createSale")
	public String createProduct(@Valid Product product, BindingResult br, RedirectAttributes attributes,
			@RequestParam("file") MultipartFile image) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		if (br.hasErrors())
			return "redirect:/createSale?error";
		else {
			if (UploadUtil.fazerUploadImagem(image)) {
				product.setImg(image.getOriginalFilename());
			}

			product.setDateAndTime();
			User user = ur.findByEmail(authentication.getName());
			user.setAdvProduct(product);
			product.setUser(user);
			pr.save(product);
			return "redirect:/createSale?success";
		}
	}
	
	@GetMapping("/productInfo")
	public ModelAndView descProduct(@RequestParam int id) {
		ModelAndView mv = new ModelAndView("products_info");
		Product products = pr.findById(id);
		mv.addObject("products", products);
		return mv;
	}


}
