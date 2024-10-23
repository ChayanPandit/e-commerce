package com.example.e_commerce.service.product;

import com.example.e_commerce.dto.ProductDto;
import com.example.e_commerce.model.Product;
import com.example.e_commerce.request.AddProductRequest;
import com.example.e_commerce.request.ProductUpdateRequest;

import java.util.List;

public interface IProductService {

  Product addProduct(AddProductRequest request);

  Product getProductById(Long id);
  Product updateProduct(ProductUpdateRequest request, Long id);
  void deleteProductById(Long id);

  List<Product> getAllProducts();
  List<Product> getProductsByCategory(String category);
  List<Product> getProductsByBrand(String brand);
  List<Product> getProductsByCategoryAndBrand(String category, String brand);
  List<Product> getProductsByName(String name);
  List<Product> getProductsByBrandAndName(String brand, String name);

  Long countProductsByBrandAndName(String brand, String name);

  ProductDto convertToDto(Product product);

  List<ProductDto> getConvertedProducts(List<Product> products);
}
