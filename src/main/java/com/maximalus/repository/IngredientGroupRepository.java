package com.maximalus.repository;

import com.maximalus.model.product.ingredient.IngredientGroup;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IngredientGroupRepository extends CrudRepository<IngredientGroup, Long> {
    IngredientGroup findByName(String name);
}
