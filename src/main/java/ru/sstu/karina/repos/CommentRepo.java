package ru.sstu.karina.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.sstu.karina.models.Comment;

@Repository
public interface CommentRepo extends JpaRepository<Comment, Long> {
}
