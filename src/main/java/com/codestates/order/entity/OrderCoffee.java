package com.codestates.order.entity;

import com.codestates.coffee.entity.Coffee;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class OrderCoffee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderCoffeeId;

    @Column(nullable = false)
    private int quantity;

    @ManyToOne
    @JoinColumn(name = "ORDER_ID")
    private Order order;

    @ManyToOne
    @JoinColumn(name = "COFFEE_ID")
    private Coffee coffee;

    public void setOrder(Order order) {
        this.order = order;
        if (!this.order.getOrderCoffees().contains(this)) { // 이 this는 OrderCoffee 즉, 클래스 자기 자신 지칭
            this.order.getOrderCoffees().add(this);
        }
    }

    public void setCoffee(Coffee coffee) {
        this.coffee = coffee;
        if (!this.coffee.getOrderCoffees().contains(this)) {
            this.coffee.getOrderCoffees().add(this);
        }
    }
}
