package vn.techmaster.blog;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import vn.techmaster.blog.entity.Category;
import vn.techmaster.blog.util.Utils;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class CategoryTest {

    @Test
    void generate_category_string_test() {
        List<Category> categories = new ArrayList<>();
        categories.add(new Category(1, "Lập trình"));
        categories.add(new Category(2, "Database"));
        categories.add(new Category(3, "Frontend"));

        String rs = Utils.generateCategoryString(categories);
        System.out.println(rs);
    }
}
