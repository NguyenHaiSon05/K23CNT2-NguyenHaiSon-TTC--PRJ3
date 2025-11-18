package k23cnt2.nhsDay06Lab8.nhscontroller;

import k23cnt2.nhsDay06Lab8.nhsentity.NhsAuthor;
import k23cnt2.nhsDay06Lab8.nhsentity.NhsBook;
import k23cnt2.nhsDay06Lab8.nhsservice.NhsAuthorService;
import k23cnt2.nhsDay06Lab8.nhsservice.NhsBookService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/nhsbook")
public class NhsBookController {

    @Autowired
    private NhsBookService nhsBookService;

    @Autowired
    private NhsAuthorService nhsAuthorService;

    private static final String UPLOAD_DIR = "src/main/resources/static/";
    private static final String UPLOAD_PATH = "images/nhsbook/";

    // ===============================
    // 1) HIỂN THỊ DANH SÁCH SÁCH
    // ===============================
    @GetMapping("")
    public String listBooks(Model model) {
        model.addAttribute("books", nhsBookService.getAllBooks());
        return "nhsbook-list";   // file: templates/nhsbook-list.html
    }

    // ===============================
    // 2) FORM THÊM SÁCH MỚI
    // ===============================
    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("book", new NhsBook());
        model.addAttribute("authors", nhsAuthorService.getAllAuthors());
        return "nhsbook-form";   // file: templates/nhsbook-form.html
    }

    // ===============================
    // 3) LƯU SÁCH MỚI
    // ===============================
    @PostMapping("/new")
    public String saveBook(@ModelAttribute NhsBook book,
                           @RequestParam(value = "authorIds", required = false) List<Long> authorIds,
                           @RequestParam("imageBook") MultipartFile imageFile) {

        handleImageUpload(book, imageFile);

        // Gán danh sách tác giả
        if (authorIds != null) {
            List<NhsAuthor> authors = nhsAuthorService.findAllById(authorIds);
            book.setAuthors(authors);
        } else {
            book.setAuthors(new ArrayList<>());
        }

        nhsBookService.saveBook(book);
        return "redirect:/nhsbook";
    }

    // ===============================
    // 4) FORM SỬA SÁCH
    // ===============================
    @GetMapping("/edit/{id}")
    public String editBook(@PathVariable Long id, Model model) {
        NhsBook book = nhsBookService.getBookById(id);

        model.addAttribute("book", book);
        model.addAttribute("authors", nhsAuthorService.getAllAuthors());

        return "nhsbook-form";
    }

    // ===============================
    // 5) LƯU CẬP NHẬT SÁCH
    // ===============================
    @PostMapping("/edit/{id}")
    public String updateBook(@PathVariable Long id,
                             @ModelAttribute NhsBook book,
                             @RequestParam(value = "authorIds", required = false) List<Long> authorIds,
                             @RequestParam("imageBook") MultipartFile imageFile) {

        NhsBook oldBook = nhsBookService.getBookById(id);

        // Upload ảnh mới hoặc giữ ảnh cũ
        if (!imageFile.isEmpty()) {
            handleImageUpload(book, imageFile);
        } else {
            book.setImgUrl(oldBook.getImgUrl());
        }

        // cập nhật danh sách tác giả
        if (authorIds != null) {
            List<NhsAuthor> authors = nhsAuthorService.findAllById(authorIds);
            book.setAuthors(authors);
        } else {
            book.setAuthors(new ArrayList<>());
        }

        book.setId(id);
        nhsBookService.saveBook(book);

        return "redirect:/nhsbook";
    }

    // ===============================
    // 6) XÓA SÁCH
    // ===============================
    @GetMapping("/delete/{id}")
    public String deleteBook(@PathVariable Long id) {
        nhsBookService.deleteBook(id);
        return "redirect:/nhsbook";
    }

    // ===============================
    // METHOD XỬ LÝ UPLOAD ẢNH
    // ===============================
    private void handleImageUpload(NhsBook book, MultipartFile imageFile) {
        try {
            if (!imageFile.isEmpty()) {
                Path uploadPath = Paths.get(UPLOAD_DIR + UPLOAD_PATH);

                if (!Files.exists(uploadPath)) {
                    Files.createDirectories(uploadPath);
                }

                String originalName = StringUtils.cleanPath(imageFile.getOriginalFilename());
                String ext = originalName.substring(originalName.lastIndexOf("."));
                String newFile = System.currentTimeMillis() + ext;

                Path filePath = uploadPath.resolve(newFile);
                Files.copy(imageFile.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

                book.setImgUrl("/" + UPLOAD_PATH + newFile);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
