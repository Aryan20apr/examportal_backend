package com.aryan.examportal_backend.examcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.aryan.examportal_backend.payload.ApiResponse;
import com.aryan.examportal_backend.payload.CategoryDTO;
import com.aryan.examportal_backend.services.CategoryService;

@RestController
@RequestMapping("examportal/category")
public class CategoryController {
	
	@Autowired
	CategoryService categoryService;
	
	//Add Category
	
	@PostMapping("/add")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<ApiResponse<CategoryDTO>> addCategory(@RequestBody CategoryDTO category,@RequestParam Long userid)
	{
		CategoryDTO categoryDTO=categoryService.addCategory(category,userid);
		ApiResponse<CategoryDTO> apiResponse=new ApiResponse<>(categoryDTO,HttpStatus.CREATED,true);
		return new ResponseEntity<ApiResponse<CategoryDTO>>(apiResponse,HttpStatus.CREATED);
	}
	
	@GetMapping("/get")
	public ResponseEntity<ApiResponse<CategoryDTO>> getCategory(@RequestParam Long categoryId)
	{
		CategoryDTO categoryDTO=categoryService.getCategory(categoryId);
		ApiResponse<CategoryDTO> apiResponse=new ApiResponse<>(categoryDTO,HttpStatus.OK,true);
		return new ResponseEntity<ApiResponse<CategoryDTO>>(apiResponse,HttpStatus.OK);
	}
	
	@GetMapping("/allCategories")
	public ResponseEntity<ApiResponse<List<CategoryDTO>>> getAllCategories()
	{
		List<CategoryDTO> categorieSet=categoryService.getCategories();
		ApiResponse<List<CategoryDTO>> apiResponse=new ApiResponse<>(categorieSet,HttpStatus.OK,true);
		return ResponseEntity.ok(apiResponse);
	}
	@GetMapping("/allCategoriesbyuser")
	public ResponseEntity<ApiResponse<List<CategoryDTO>>> getAllCategoriesByUser(@RequestParam Long userid)
	{
		List<CategoryDTO> categorieSet=categoryService.getCategoriesByUser(userid);
		ApiResponse<List<CategoryDTO>> apiResponse=new ApiResponse<>(categorieSet,HttpStatus.OK,true);
		return ResponseEntity.ok(apiResponse);
	}
	@PutMapping("/update")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<ApiResponse<CategoryDTO>> updateCategory(@RequestBody CategoryDTO category)
	{
		CategoryDTO categoryDTO=categoryService.updateCategory(category);
		ApiResponse<CategoryDTO> apiResponse=new ApiResponse<>(categoryDTO,HttpStatus.CREATED,true);
		return new ResponseEntity<ApiResponse<CategoryDTO>>(apiResponse,HttpStatus.CREATED);
	}
	
	@DeleteMapping("/delete")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<?> deleteCategory(@RequestParam Long id)
	{
		categoryService.deleteCategory(id);
		ApiResponse<String> apiResponse=new ApiResponse<>("Category Deleted Successfully",HttpStatus.CREATED,true);
		return ResponseEntity.ok(apiResponse);
	}
	
	@GetMapping("/getcategoriesenrolledbyuser")
	
	public ResponseEntity<ApiResponse<List<CategoryDTO>>> getAllEnrolledCategoriesByUser(@RequestParam Long userid)
	{
		List<CategoryDTO> categorieSet=categoryService.getenrolledSubjects(userid);
		ApiResponse<List<CategoryDTO>> apiResponse=new ApiResponse<>(categorieSet,HttpStatus.OK,true);
		return ResponseEntity.ok(apiResponse);
	}
	
@GetMapping("/categories/notenrolled")
	
	public ResponseEntity<ApiResponse<List<CategoryDTO>>> getAllUnEnrolledCategoriesByUser(@RequestParam Long userid)
	{
		List<CategoryDTO> categorieSet=categoryService.getUnenrolledSubjects(userid);
		ApiResponse<List<CategoryDTO>> apiResponse=new ApiResponse<>(categorieSet,HttpStatus.OK,true);
		return ResponseEntity.ok(apiResponse);
	}
	

	
}
