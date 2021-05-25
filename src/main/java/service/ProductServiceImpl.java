package service;

import model.Product;

import java.util.ArrayList;
import java.util.List;

public class ProductServiceImpl implements ProductService{
    private static ArrayList<Product> productList ;
    static {
        productList =  new ArrayList<>();
        productList.add(new Product(0,"fsfds"));
        productList.add(new Product(1,"vcx"));
        productList.add(new Product(2,"bddd"));
    }

    public List<Product> findAll(){
        return productList;
    }

    @Override
    public void save(Product product) {
        productList.add(product);
        productList.trimToSize();
    }

    @Override
    public Product findbyId(int id) {
        return productList.get(id);
    }

    @Override
    public void update(int id, Product product) {
        productList.set(id,product);
    }

    @Override
    public void remove(int id) {
        productList.remove(id);
        for (Product product:productList) {
            product.setId(productList.indexOf(product));
        }
    }

    @Override
    public Product findbyName(String name) {
        for (Product product: productList) {
            if (product.getName().equals(name)){
                return product;
            }
        }
        return null;
    }
    public int getSize(){
        return productList.size();
    }


}
