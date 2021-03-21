package com.maximalus.controler;

import com.maximalus.dto.IngredientDto;
import com.maximalus.dto.converter.IngredientDtoConverter;
import com.maximalus.model.product.ingredient.Ingredient;
import com.maximalus.model.product.ingredient.IngredientGroup;
import com.maximalus.model.storage.IngredientStorage;
import com.maximalus.service.impl.IngredientGroupServiceImpl;
import com.maximalus.service.impl.IngredientServiceImpl;
import com.maximalus.service.impl.StorageServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@AllArgsConstructor
public class IngredientController {
    private final IngredientServiceImpl ingredientService;
    private final IngredientGroupServiceImpl ingredientGroupService;
    private final StorageServiceImpl storageService;

    @GetMapping(value = "/admin/createIngredient")
    public String createIngredient(Model model){
        model.addAttribute("ingredientDto", new IngredientDto());
        model.addAttribute("ingredientGroups", getListIngredientGroupNames());
        return "admin/manage/ingredient/createIngredient";
    }

    @PostMapping(value = "/admin/createIngredient")
    public String createIngredient(@Valid @ModelAttribute("ingredientDto") IngredientDto ingredientDto,
                                   BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "403";
        }

        Ingredient ingredient = IngredientDtoConverter.fromDto(ingredientDto);
        IngredientGroup ingredientGroup =
                ingredientGroupService.findByName(ingredientDto.getIngredientGroupName());
        ingredientGroup.addIngredient(ingredient);
        ingredientService.save(ingredient);
        return "admin/admin";
    }

    @GetMapping(value = "/admin/allIngredients")
    public String allIngredients(Model model){
        List<Ingredient> ingredients = storageService.findAll().stream()
                .map(IngredientStorage::getIngredient)
                .filter(ingredient -> !ingredient.isDeleted())
                .collect(Collectors.toList());
        model.addAttribute("ingredients", ingredients);
        return "admin/manage/ingredient/allIngredients";
    }

    @GetMapping(value = "/admin/editIngredient")
    public String editIngredient(Model model, @RequestParam String id){
        Long ingredientId = Long.parseLong(id);
        Ingredient ingredient = ingredientService.findById(ingredientId);
//        IngredientDto ingredientDto = IngredientDtoConverter.toDto(ingredient);
//        model.addAttribute("ingredientDto", ingredientDto);
        model.addAttribute("ingredientGroups", getListIngredientGroupNames());
        return "admin/manage/ingredient/editIngredient";
    }

    @PostMapping(value = "/admin/editIngredient")
    public String editIngredient(@Valid @ModelAttribute("ingredientDto") IngredientDto ingredientDto,
                                 BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "403";
        }

        Ingredient ingredient = ingredientService.findById(ingredientDto.getId());
        ingredient.setName(ingredientDto.getName());
//        ingredient.setUnitName(ingredientDto.getNameOfUnit());
        ingredient.setChangingDate(LocalDateTime.now());

        IngredientGroup ingredientGroup =
                ingredientGroupService.findByName(ingredientDto.getIngredientGroupName());
        ingredientGroup.addIngredient(ingredient);
        ingredientService.save(ingredient);
        return "admin/admin";
    }

    @GetMapping(value = "/admin/deleteIngredient")
    public String deleteIngredient(@RequestParam("id") String id){
        Long ingredientId = Long.parseLong(id);
        ingredientService.deleteById(ingredientId);
        return "admin/manage/user/allUsers";
    }

    private List<String> getListIngredientGroupNames(){
        List<String> ingredientGroupNames = ingredientGroupService.findAll().stream()
                .map(IngredientGroup::getName).collect(Collectors.toList());
        return ingredientGroupNames;
    }
}
