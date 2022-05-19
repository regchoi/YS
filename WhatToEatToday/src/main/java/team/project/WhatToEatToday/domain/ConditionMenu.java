package team.project.WhatToEatToday.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class ConditionMenu {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "condition_id")
    private Condition condition;

    @OneToMany(mappedBy = "conditionMenu")
    private List<Menu> menu = new ArrayList<>();

    public void setCondition(Condition condition) {
        this.condition = condition;
        condition.getConditionMenu().add(this);
    }

}
