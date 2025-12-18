package com.freshfruit.nhsservice;

import com.freshfruit.nhsentity.Cart;
import com.freshfruit.nhsentity.CartItem;
import com.freshfruit.nhsentity.Product;
import com.freshfruit.nhsentity.UserEntity;
import com.freshfruit.nhsrepository.CartItemRepository;
import com.freshfruit.nhsrepository.CartRepository;
import com.freshfruit.nhsrepository.ProductRepository;
import com.freshfruit.nhsrepository.UserRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CartService {

    private final CartRepository cartRepo;
    private final CartItemRepository cartItemRepo;
    private final ProductRepository productRepo;
    private final UserRepository userRepo;

    // Lấy giỏ hàng theo userId
    public Cart getCartByUser(Integer userId) {
        return cartRepo.findByUser_UserId(userId);
    }

    // Thêm sản phẩm vào giỏ
    public void addToCart(Integer userId, Integer productId) {

        Cart cart = getCartByUser(userId);

        if (cart == null) {
            cart = new Cart();
            cart.setUser(userRepo.findById(userId).orElse(null));
            cart.setItems(new ArrayList<>());
            cartRepo.save(cart);
        }

        Product product = productRepo.findById(productId).orElse(null);
        if (product == null) return;

        CartItem item = cartItemRepo.findByCartAndProduct(cart, product);

        if (item == null) {
            item = new CartItem();
            item.setCart(cart);
            item.setProduct(product);
            item.setQuantity(1);
        } else {
            item.setQuantity(item.getQuantity() + 1);
        }

        cartItemRepo.save(item);
    }

    // Xóa sản phẩm khỏi giỏ
    @Transactional
    public void removeItemByItemId(Integer itemId) {

        CartItem item = cartItemRepo.findById(itemId).orElse(null);
        if (item == null) return;

        Cart cart = item.getCart();

        // 🔥 XÓA KHỎI LIST (QUAN TRỌNG)
        cart.getItems().remove(item);

        // 🔥 Hibernate tự xóa DB nhờ orphanRemoval = true
    }


    @Transactional
    public void updateQuantity(Integer itemId, Integer quantity) {

        CartItem item = cartItemRepo.findById(itemId).orElse(null);
        if (item == null) return;

        if (quantity <= 0) {
            // nếu nhập 0 → xóa luôn
            item.getCart().getItems().remove(item);
        } else {
            item.setQuantity(quantity);
        }
    }







    // Tính tổng tiền
    public int getTotalPrice(Cart cart) {
        if (cart == null || cart.getItems() == null) return 0;

        return cart.getItems()
                .stream()
                .mapToInt(i -> i.getQuantity() * i.getProduct().getPrice().intValue())
                .sum();
    }
    public int getCartCount(Integer userId) {
        Cart cart = cartRepo.findByUser_UserId(userId);
        if (cart == null || cart.getItems() == null) return 0;

        return cart.getItems()
                .stream()
                .mapToInt(CartItem::getQuantity)
                .sum();
    }



}
