package com.peaksoft.service;


import com.peaksoft.dto.SubCategoryRequest;
import com.peaksoft.dto.SubCategoryResponse;
import com.peaksoft.model.entity.Subcategory;
import com.peaksoft.repository.SubCategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SubCategoryService {

    private final SubCategoryRepository subCategoryRepository;

    public SubCategoryResponse create(SubCategoryRequest request){
        Subcategory subCategory = mapToEntity(request);
        subCategoryRepository.save(subCategory);
        return subCategoryResponse(subCategory);
    }

    public SubCategoryResponse update(Long id, SubCategoryRequest request){
        Subcategory subCategory = subCategoryRepository.findById(id).get();
        mapToUpdate(request,subCategory);
        return subCategoryResponse(subCategoryRepository.save(subCategory));
    }

    public SubCategoryResponse deleteById(Long id){
        Subcategory subCategory = subCategoryRepository.findById(id).get();

        subCategoryRepository.deleteById(id);
        return subCategoryResponse(subCategory);
    }

    public SubCategoryResponse getById(Long id){
        Subcategory category = subCategoryRepository.findById(id).get();
        subCategoryRepository.findById(id);
        return subCategoryResponse(category);
    }

    public Subcategory mapToUpdate(SubCategoryRequest request, Subcategory subCategory){
        subCategory.setSubcategoryName(request.getSubcategoryName());

        return subCategory;
    }

    public Subcategory mapToEntity(SubCategoryRequest request) {
        Subcategory subCategory = new Subcategory();
        subCategory.setSubcategoryName(request.getSubcategoryName());

        return subCategory;
    }


    public SubCategoryResponse subCategoryResponse(Subcategory subCategory) {
        return SubCategoryResponse.builder()
                .id(subCategory.getId())
                .subcategoryName(subCategory.getSubcategoryName())
                .build();
    }

    public List<Subcategory> getAllSubCategory() {
        return subCategoryRepository.findAll();
    }
}
