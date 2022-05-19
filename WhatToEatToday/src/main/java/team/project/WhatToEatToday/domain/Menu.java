package team.project.WhatToEatToday.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Menu {

    @Id
    @GeneratedValue
    @Column(name = "menu_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "eating_house_id")
    private EatingHouse eatingHouse;

    private String name;
    private int price;

   
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category categorys;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "condition_menu_id")
    private ConditionMenu conditionMenu;


    
    public void setCategory(Category category) {
        this.categorys = category;
        category.getMenu().add(this);

    }

    public void setEatingHouse(EatingHouse eatingHouse) {
        this.eatingHouse = eatingHouse;
        eatingHouse.getMenus().add(this);
    }
    

    @Override
    public String toString() {
        return "Menu{" +
                "id=" + id +
                ", eatingHouse=" + eatingHouse +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", categorys=" + categorys +
                '}';
    }
}
