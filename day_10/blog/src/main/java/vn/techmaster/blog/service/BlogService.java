package vn.techmaster.blog.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.techmaster.blog.dto.BlogDto;
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

    @Autowired
    private ModelMapper modelMapper;

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

    // Lấy danh sách tất cả blog ở dạng DTO
    public List<BlogDto> getAllBlogDto() {
        List<Blog> blogs = blogRepository.findAll();
        return blogs.stream()
                .map(blog -> modelMapper.map(blog, BlogDto.class))
                .collect(Collectors.toList());
    }

    // Lấy danh sách tất cả blog ở dạng DTO của user
    public List<BlogDto> getAllBlogDtoByUserId(Integer id) {
        List<Blog> blogs = blogRepository.getBlogsByUser_Id(id);
        return blogs.stream()
                .map(blog -> modelMapper.map(blog, BlogDto.class))
                .collect(Collectors.toList());
    }
}
