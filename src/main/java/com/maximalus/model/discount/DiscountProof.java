package com.maximalus.model.discount;

import com.maximalus.model.Order;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "discount_proofs")
public class DiscountProof {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "discount_proof_generator")
    @SequenceGenerator(allocationSize = 1, name = "discount_proof_generator")
    private Long id;

    @Lob
    @Column(nullable = false)
    private String encodedImage;

    @Column(nullable = false)
    private LocalDateTime creationDate;

    @Column(nullable = false)
    private LocalDateTime changingDate;

    @EqualsAndHashCode.Exclude
    @OneToOne(mappedBy = "discountProof")
    private Order order;

    @Column(nullable = false)
    private boolean isDeleted;
}
