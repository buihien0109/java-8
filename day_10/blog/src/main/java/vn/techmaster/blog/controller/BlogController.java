package vn.techmaster.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import vn.techmaster.blog.repository.CategoryRepository;
import vn.techmaster.blog.service.BlogService;

@Controller
public class BlogController {

    @Autowired
    private BlogService blogService;

    @Autowired
    private CategoryRepository categoryRepository;

    // Danh sách tất cả bài viết
    @GetMapping("/admin/blogs")
    public String getBlogPage(Model model) {
        // TODO : Bổ sung thêm phân trang
        model.addAttribute("blogs", blogService.getAllBlogDto());

        return "admin/blog/blog-index";
    }

    // Danh sách bài viết của tôi
    @GetMapping("/admin/blogs/own-blogs")
    public String getOwnBlogPage(Model model) {
        // TODO : Về sau userId sẽ là id của user đang đăng nhập
        Integer userId = 1;

        model.addAttribute("blogs", blogService.getAllBlogDtoByUserId(userId));

        return "admin/blog/blog-yourself";
    }

    // Tạo bài viết
    @GetMapping("/admin/blogs/create")
    public String getBlogCreatePage(Model model) {
        model.addAttribute("categoies", categoryRepository.findAll());
        return "admin/blog/blog-create";
    }

    // Chi tiết bài viết
    @GetMapping("/admin/blogs/{id}/detail")
    public String getBlogDetailPage(@PathVariable String id) {
        return "admin/blog/blog-detail";
    }
}
