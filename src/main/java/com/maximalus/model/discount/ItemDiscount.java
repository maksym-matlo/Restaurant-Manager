package com.maximalus.model.discount;

import com.maximalus.model.product.item.MenuItem;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "product_discounts")
public class ItemDiscount {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "discount_generator")
    @SequenceGenerator(allocationSize = 1, name = "discount_generator")
    private Long id;

    @Column(nullable = false)
    private String discountName;

    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @OneToOne
    private MenuItem menuItem;

    @Column(nullable = false)
    private int amountOfProduct;

    @Column(nullable = false)
    private int percentageDiscount;

    @Column(nullable = false)
    private LocalDateTime creationDate;

    @Column(nullable = false)
    private LocalDateTime changingDate;

    @Column(nullable = false)
    private boolean isDeleted;
}
