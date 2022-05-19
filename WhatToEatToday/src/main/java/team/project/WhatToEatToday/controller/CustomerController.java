package team.project.WhatToEatToday.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;
import team.project.WhatToEatToday.Service.CategoryService;
import team.project.WhatToEatToday.Service.ConditionCategoryService;
import team.project.WhatToEatToday.Service.ConditionService;
import team.project.WhatToEatToday.Service.CustomerService;
import team.project.WhatToEatToday.Service.EatingHouseService;
import team.project.WhatToEatToday.Service.MenuService;
import team.project.WhatToEatToday.domain.Category;
import team.project.WhatToEatToday.domain.Condition;
import team.project.WhatToEatToday.domain.ConditionCategory;
import team.project.WhatToEatToday.domain.EatingHouse;
import team.project.WhatToEatToday.domain.Menu;
import team.project.WhatToEatToday.domain.member.Customer;
import team.project.WhatToEatToday.domain.member.Manager;
import team.project.WhatToEatToday.domain.member.Member;
import team.project.WhatToEatToday.dto.JoinForm;
import team.project.WhatToEatToday.dto.MenuForm;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.net.http.HttpRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping("/customer")
@RequiredArgsConstructor
public class CustomerController {
	private final CustomerService customerService;
    private final MenuService menuService;
    private final EatingHouseService eatingHouseService;
    private final CategoryService categoryService;
    private final ConditionService conditionService;
    private final ConditionCategoryService conditionCategoryService;

	@GetMapping("/mypage/{customerId}")
    public String getMypage(@PathVariable String customerId, Model model) {
        model.addAttribute("customer", customerService.findOne(customerId));
        model.addAttribute("page", "myPage");
        return "layout";
    }

    @GetMapping("/mypage/edit/{customerId}")
    public String editMypage(@PathVariable String customerId, Model model, JoinForm joinForm) {
        model.addAttribute("customer", customerService.findOne(customerId));
        model.addAttribute("JoinForm", joinForm);
        model.addAttribute("page", "editCustomerInfo");
        return "layout";
    }

    @PostMapping("/mypage/edit/{customerId}")
    public String editMypage(HttpServletRequest request , @PathVariable String customerId, @Valid JoinForm joinForm) {
        HttpSession session = request.getSession();
        Customer customer = customerService.findOne(customerId);
        customer.setName(joinForm.getName());
        customer.setPassword(joinForm.getPassword());
        customer.setEmail(joinForm.getEmail());
        customer.setTel(joinForm.getTel());
        customer.setAddress(joinForm.getAddress());
        customer.setAddressDetail(joinForm.getAddressDetail());
        customerService.edit(customer);
        session.setAttribute("message", "회원정보 변경");
        return "redirect:/customer/mypage/" + customerId;
    }

    @GetMapping("/mypage/delete/{customerId}")
    public String editMypage(HttpServletRequest request , @PathVariable String customerId) {
        HttpSession session = request.getSession();
        Customer customer = customerService.findOne(customerId);
        customerService.delete(customer);
        session.setAttribute("message", "회원탈퇴");
        return "redirect:/logout";
    }
	

	@GetMapping("/recommend")
    public String recommendMenu(HttpServletRequest request, Model model) {
        HttpSession session= request.getSession();
        try {
            Member member = (Member) session.getAttribute("member");
            member.getId().isBlank();
            
            ConditionCategory concate1 = conditionCategoryService.findOne(1L);
            model.addAttribute("concate1", concate1);
            ConditionCategory concate2 = conditionCategoryService.findOne(2L);
            model.addAttribute("concate2", concate2);  
            ConditionCategory concate3 = conditionCategoryService.findOne(3L);
            model.addAttribute("concate3", concate3);  
            
            List<Condition> conditionList1 = conditionService.findCate1(1L);
            model.addAttribute("condition1", conditionList1);
            List<Condition> conditionList2 = conditionService.findCate1(2L);
            model.addAttribute("condition2", conditionList2);
            List<Condition> conditionList3 = conditionService.findCate1(3L);
            model.addAttribute("condition3", conditionList3);
            
            model.addAttribute("page", "menuRecommend");
            
            return "layout";
        }
        catch (Exception e){
            session.setAttribute("message","로그인 이후에 이용 가능합니다");
            return "redirect:/";
        }
    }

	@GetMapping("/recommendResult")
    public String recommendMenuResult(Model model) {
    	model.addAttribute("page", "menuRecommendResult");
        return "layout";

    }
	
	
	@GetMapping("/menuAll")
    public String viewMenuAll(Model model) {
    	model.addAttribute("page", "viewMenuAll");
        return "layout";
    }




    @GetMapping("/eating_house/All")
    public String viewAll(Model model){

        List<Menu> menu = menuService.findAll();

        List<EatingHouse> eatingHouses = new ArrayList<>();
        for(int i=0; i<menu.size(); i++){
            int x = 0;
            for(int j=0; j< eatingHouses.size(); j++){
                if(eatingHouses.get(j).getId().equals(menu.get(i).getEatingHouse().getId())){
                    x++;
                }
            }
            if(x ==0){
                eatingHouses.add(menu.get(i).getEatingHouse());
            }
        }

        model.addAttribute("page", "viewAll");
        model.addAttribute("eatingHouse", eatingHouses);

        return "layout";
    }


    @GetMapping("/eating_house/All/{eatingHouseId}")
    public String viewMenu(@PathVariable Long eatingHouseId,  Model model) {


        EatingHouse eatingHouses = eatingHouseService.findOne(eatingHouseId);
        System.out.println(eatingHouses.getMenus());


        model.addAttribute("eatingHouse", eatingHouses);
        model.addAttribute("page", "menuList");
        return "layout";
    }


    @GetMapping("/eating_house/Kfood")
    public String viewKfood(Model model) {

        List<Menu> menu = menuService.findCategoryId(2L);

        List<EatingHouse> eatingHouses = new ArrayList<>();
        for(int i=0; i<menu.size(); i++){
                int x = 0;
                for(int j=0; j< eatingHouses.size(); j++){
                    if(eatingHouses.get(j).getId().equals(menu.get(i).getEatingHouse().getId())){
                        x++;
                    }
                }
            if(x ==0 ){
                eatingHouses.add(menu.get(i).getEatingHouse());
            }
        }

        model.addAttribute("page", "viewKfood");
        model.addAttribute("eatingHouse", eatingHouses);

        return "layout";
    }

    @GetMapping("/eating_house/Jfood")
    public String viewJfood(Model model) {

        List<Menu> menu = menuService.findCategoryId(3L);

        List<EatingHouse> eatingHouses = new ArrayList<>();
        for(int i=0; i<menu.size(); i++){
            int x = 0;
            for(int j=0; j< eatingHouses.size(); j++){
                if(eatingHouses.get(j).getId().equals(menu.get(i).getEatingHouse().getId())){
                    x++;
                }
            }
            if(x ==0){
                eatingHouses.add(menu.get(i).getEatingHouse());
            }
        }

        model.addAttribute("page", "viewJfood");
        model.addAttribute("eatingHouse", eatingHouses);

        return "layout";
    }

    @GetMapping("/eating_house/Cfood")
    public String viewCfood(Model model) {

        List<Menu> menu = menuService.findCategoryId(4L);

        List<EatingHouse> eatingHouses = new ArrayList<>();
        for(int i=0; i<menu.size(); i++){
            int x = 0;
            for(int j=0; j< eatingHouses.size(); j++){
                if(eatingHouses.get(j).getId().equals(menu.get(i).getEatingHouse().getId())){
                    x++;
                }
            }
            if(x ==0){
                eatingHouses.add(menu.get(i).getEatingHouse());
            }
        }

        model.addAttribute("page", "viewCfood");
        model.addAttribute("eatingHouse", eatingHouses);

        return "layout";
    }

    @GetMapping("/eating_house/Wfood")
    public String viewWfood(Model model) {

        List<Menu> menu = menuService.findCategoryId(5L);

        List<EatingHouse> eatingHouses = new ArrayList<>();
        for(int i=0; i<menu.size(); i++){
            int x = 0;
            for(int j=0; j< eatingHouses.size(); j++){
                if(eatingHouses.get(j).getId().equals(menu.get(i).getEatingHouse().getId())){
                    x++;
                }
            }
            if(x ==0){
                eatingHouses.add(menu.get(i).getEatingHouse());
            }
        }

        model.addAttribute("page", "viewWfood");
        model.addAttribute("eatingHouse", eatingHouses);

        return "layout";
    }

    @GetMapping("/eating_house/Chicken")
    public String viewChicken(Model model) {

        List<Menu> menu = menuService.findCategoryId(6L);

        List<EatingHouse> eatingHouses = new ArrayList<>();
        for(int i=0; i<menu.size(); i++){
            int x = 0;
            for(int j=0; j< eatingHouses.size(); j++){
                if(eatingHouses.get(j).getId().equals(menu.get(i).getEatingHouse().getId())){
                    x++;
                }
            }
            if(x ==0){
                eatingHouses.add(menu.get(i).getEatingHouse());
            }
        }

        model.addAttribute("page", "viewChicken");
        model.addAttribute("eatingHouse", eatingHouses);

        return "layout";
    }

    @GetMapping("/eating_house/Bunsik")
    public String viewBunsik(Model model) {

        List<Menu> menu = menuService.findCategoryId(7L);

        List<EatingHouse> eatingHouses = new ArrayList<>();
        for(int i=0; i<menu.size(); i++){
            int x = 0;
            for(int j=0; j< eatingHouses.size(); j++){
                if(eatingHouses.get(j).getId().equals(menu.get(i).getEatingHouse().getId())){
                    x++;
                }
            }
            if(x ==0){
                eatingHouses.add(menu.get(i).getEatingHouse());
            }
        }

        model.addAttribute("page", "viewBunsik");
        model.addAttribute("eatingHouse", eatingHouses);

        return "layout";
    }

    @GetMapping("/eating_house/Dessert")
    public String viewDessert(Model model) {

        List<Menu> menu = menuService.findCategoryId(8L);

        List<EatingHouse> eatingHouses = new ArrayList<>();
        for(int i=0; i<menu.size(); i++){
            int x = 0;
            for(int j=0; j< eatingHouses.size(); j++){
                if(eatingHouses.get(j).getId().equals(menu.get(i).getEatingHouse().getId())){
                    x++;
                }
            }
            if(x ==0){
                eatingHouses.add(menu.get(i).getEatingHouse());
            }
        }

        model.addAttribute("page", "viewDessert");
        model.addAttribute("eatingHouse", eatingHouses);

        return "layout";
    }


}
