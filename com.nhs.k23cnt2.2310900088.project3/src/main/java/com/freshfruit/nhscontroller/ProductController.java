package com.freshfruit.nhscontroller;

import com.freshfruit.nhsentity.Product;
import com.freshfruit.nhsentity.Review;
import com.freshfruit.nhsentity.UserEntity;
import com.freshfruit.nhsrepository.ProductRepository;
import com.freshfruit.nhsrepository.ReviewRepository;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class ProductController {

    // ✅ BẮT BUỘC PHẢI CÓ
    private final ProductRepository productRepo;
    private final ReviewRepository reviewRepo;

    // ===============================
    // PRODUCT DETAIL + REVIEWS
    // ===============================
    @GetMapping("/product/{id}")
    public String productDetail(@PathVariable Integer id,
                                Model model,
                                HttpSession session) {

        Product product = productRepo.findById(id).orElse(null);
        if (product == null) return "redirect:/";

        List<Review> reviews =
                reviewRepo.findByProduct_ProductIdOrderByCreatedAtDesc(id);

        Double avgRating = reviewRepo.avgRating(id);

        UserEntity user =
                (UserEntity) session.getAttribute("loggedUser");

        boolean reviewed = false;
        if (user != null) {
            reviewed = reviewRepo
                    .existsByUser_UserIdAndProduct_ProductId(
                            user.getUserId(), id);
        }

        model.addAttribute("product", product);
        model.addAttribute("reviews", reviews);
        model.addAttribute("avgRating", avgRating);
        model.addAttribute("reviewed", reviewed);

        return "product/detail";
    }

    // ===============================
    // SUBMIT REVIEW
    // ===============================
    @PostMapping("/review")
    public String submitReview(@RequestParam Integer productId,
                               @RequestParam Integer rating,
                               @RequestParam String comment,
                               HttpSession session) {

        UserEntity user =
                (UserEntity) session.getAttribute("loggedUser");
        if (user == null) return "redirect:/login";

        // ❌ Không cho đánh giá 2 lần
        if (reviewRepo.existsByUser_UserIdAndProduct_ProductId(
                user.getUserId(), productId)) {
            return "redirect:/product/" + productId;
        }

        Review review = new Review();
        review.setUser(user);
        review.setProduct(productRepo.findById(productId).get());
        review.setRating(rating);
        review.setComment(comment);
        review.setCreatedAt(LocalDateTime.now());

        reviewRepo.save(review);

        return "redirect:/product/" + productId;
    }
}
