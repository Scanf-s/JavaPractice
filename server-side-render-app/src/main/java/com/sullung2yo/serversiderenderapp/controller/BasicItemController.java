package com.sullung2yo.serversiderenderapp.controller;

import com.sullung2yo.serversiderenderapp.domain.Product;
import com.sullung2yo.serversiderenderapp.domain.ProductUpdate;
import com.sullung2yo.serversiderenderapp.repository.ProductRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Controller
@RequestMapping("/basic/items")
@RequiredArgsConstructor // final이 붙은 멤버변수만 사용해서 생성자를 자동으로 만들어준다
public class BasicItemController {

    private final ProductRepository productRepository;

    @PostConstruct // 생성자 주입 전에, 미리 Product 생성
    public void init() {
        productRepository.save(new Product("itemA", 10000, 10));
        productRepository.save(new Product("itemB", 20000, 20));
    }

    @GetMapping
    public String items(Model model) {
        List<Product> products = new ArrayList<>(productRepository.findAll());
        model.addAttribute("items", products);
        return "basic/items";
    }

    @GetMapping("/{productId}")
    public String item(@PathVariable(name="productId") Long productId, Model model) {
        Product findProduct = productRepository.findById(productId);
        model.addAttribute("item", findProduct);
        return "basic/item";
    }

    @GetMapping("/add")
    public String addForm() {
        log.info("addForm-GET");
        return "basic/addForm";
    }

//    @PostMapping("/add") // Form Data로 itemName, price, quantity를 받아서 Product 생성
//    public String addItemV1(@RequestParam(name="itemName") String itemName,
//                            @RequestParam(name="price") Integer price,
//                            @RequestParam(name="quantity") Integer quantity,
//                            Model model) {
//        Product product = new Product(itemName, price, quantity);
//        productRepository.save(product);
//        model.addAttribute("item", product);
//        return "basic/item";
//    }

//    @PostMapping("/add")
//    public String addItemV2(@ModelAttribute("item") Product product, Model model) {
//        // ModelAttribute는 RequestParam 값을 setXXX 메서드를 자동으로 사용하여 매핑한다
//        log.info(product.toString());
//        productRepository.save(product);
//        return "basic/item";
//    }

    @PostMapping("/add")
    public String addItemV3(@ModelAttribute("item") Product product, RedirectAttributes redirectAttributes) {
        // Post -> Redirect -> GET 방식으로 수정해야 안정적인 서비스가 가능해진다.
        Product savedProduct = productRepository.save(product);
        Long productId = savedProduct.getId();
        redirectAttributes.addAttribute("productId", productId);
        return "redirect:/basic/items/{productId}";
    }

    @GetMapping("/{productId}/edit")
    public String editForm(@PathVariable(name="productId") Long productId, Model model) {
        Product findProduct = productRepository.findById(productId);
        model.addAttribute("item", findProduct);
        return "basic/editForm";
    }

    @PostMapping("/{productId}/edit")
    public String edit(
            @PathVariable(name="productId") Long productId,
            @ModelAttribute("item")ProductUpdate productUpdate
    ) {
        productRepository.updateProduct(productId, productUpdate);
        return "redirect:/basic/items/{productId}";
    }

}
