package vn.techmaster.blog.util;

import org.apache.logging.log4j.util.Strings;
import vn.techmaster.blog.dto.BlogDto;
import vn.techmaster.blog.entity.Category;

import java.util.List;

public class Utils {
    public static String generateCategoryString(List<BlogDto.CategoryDto> categories) {
        if(categories.size() == 0) {
            return "";
        }

        // Lấy ra Listname của Category
        List<String> categoriesName = categories.stream().map(BlogDto.CategoryDto::getName).toList();

        // Chuyển List -> Array
        String[] itemsArray = new String[categoriesName.size()];
        itemsArray = categoriesName.toArray(itemsArray);

        // Join Array -> String
        return String.join(", ", itemsArray);
    }
}
