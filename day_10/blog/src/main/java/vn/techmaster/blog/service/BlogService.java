package vn.techmaster.blog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.techmaster.blog.dto.BlogInfo;
import vn.techmaster.blog.entity.Blog;
import vn.techmaster.blog.repository.BlogRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BlogService {
    @Autowired
    private BlogRepository blogRepository;

    public List<BlogInfo> getAllBlogInfo() {
        return blogRepository.getAllBlogInfo();
    }

    public List<BlogInfo> getBlogPopular(int limit) {
        return blogRepository.getAllBlogInfo()
                .stream()
                .sorted((a, b) -> b.getCountComment() - a.getCountComment())
                .limit(limit)
                .collect(Collectors.toList());
    }

    public BlogInfo getBlogInfoById(String id) {
        Optional<BlogInfo> blogInfoOptional =  blogRepository.getAllBlogInfo()
                .stream()
                .filter(blogInfo -> blogInfo.getId().equals(id))
                .findFirst();

        return blogInfoOptional.orElse(null);
    }
}
