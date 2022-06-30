package vn.techmaster.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.techmaster.blog.entity.Blog;

@Repository
public interface BlogRepository extends JpaRepository<Blog, String> {
}