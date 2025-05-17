/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;
import java.util.ArrayList;
/**
 *
 * @author Admin
 */
public class Cart {
    private static ArrayList<Product> danhSach = new ArrayList<>();

    public static void addItem(Product p) {
        danhSach.add(p);
    }

    public static ArrayList<Product> getDanhSach() {
        return danhSach;
    }
    
    
}
