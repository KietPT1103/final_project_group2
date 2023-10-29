/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.ArrayList;
import java.util.List;

/**
 * Đối tượng giỏ hàng, chứa một danh sách của các sản phẩm ta ném vào trong giỏ
 * hàng
 */
public class Cart {

    private List<Item> items;

    /**
     * Tạo đối tượng Cart mới có một arraylist, tạo mới danh sách giỏ hàng
     */
    public Cart() {
        items = new ArrayList<>();
    }

    /**
     *
     * @return Trả về toàn bộ giỏ hàng
     */
    public List<Item> getItems() {
        return items;
    }

    /**
     *
     * @param id id của sản phẩm
     * @return Số lượng còn lại của sản phẩm trong kho
     */
    public int getQuantityById(String id) {
        return getItemById(id).getQuantity();
    }

    /**
     *
     * @param id
     * @return Trả về item đang có trong giỏ hàng
     */
    private Item getItemById(String id) {
        for (Item i : items) {
            if (i.getProduct().getId().equals(id)) {
                return i;
            }
        }
        return null;
    }

    /**
     *
     * @param t
     */
    public void addItem(Item t) {
        //Kiểm tra xem đã có sp đó ở trong giỏ hàng chưa
        if (getItemById(t.getProduct().getId()) != null) {
            Item m = getItemById(t.getProduct().getId());
            //Set số lượng mua mới trong list bằng số lượng cũ cộng số lượng mới
            m.setQuantity(m.getQuantity() + t.getQuantity());
        } else {
            items.add(t);
        }
    }

    /**
     *
     * @param id
     */
    public void removeItem(String id) {
        if (getItemById(id) != null) {
            items.remove(getItemById(id));
        }
    }

    /**
     *
     * @return
     */
    public double getTotalMoney() {
        double t = 0;
        for (Item i : items) {
            t += (i.getQuantity() * i.getPrice());
        }
        return t;
    }

    private Product getProductById(String id, List<Product> list) {
        for (Product p : list) {
            if (p.getId().equals(id)) {
                return p;
            }
        }
        return null;
    }

    /**
     *
     * @param txt
     * @param list
     */
    public Cart(String txt, List<Product> list) {
        items = new ArrayList<>();
        try {
            if (txt != null && txt.length() != 0) {
                String[] s = txt.split("-"); //lấy ra danh sách sản phẩm
                for (String i : s) {
                    String[] n = i.split(":");
                    String id = n[0];
                    int quantity = Integer.parseInt(n[1]);
                    Product p = getProductById(id, list);
                    Item t = new Item(p, quantity, p.getPrice());
                    addItem(t);
                }
            }
        } catch (NumberFormatException e) {
            System.out.println(e);
        }
    }
}
