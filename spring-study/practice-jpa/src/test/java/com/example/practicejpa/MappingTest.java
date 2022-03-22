package com.example.practicejpa;

import com.example.practicejpa.entity.Post;
import com.example.practicejpa.entity.User;
import com.example.practicejpa.repository.PostRepository;
import com.example.practicejpa.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

@DataJpaTest
public class MappingTest {

    @Autowired
    TestEntityManager testEntityManager;
    @Autowired
    UserRepository userRepository;
    @Autowired
    PostRepository postRepository;

    @Test
    void 양방향매핑() {
        User user = userRepository.save(
            User.builder()
                .name("이름1")
                .age(1)
                .build()
        );
        System.out.println("User 저장완료");

        Post post = postRepository.save(
            Post.builder()
                .title("제목1")
                .content("내용1")
                .user(user)
                .build()
        );
        System.out.println("Post 저장완료");

        testEntityManager.clear();

        User saved = userRepository.findById(user.getId()).get();
        System.out.println("user select 실행됨");

        System.out.println(saved);  // posts 리스트 사이즈만 출력해도 바로 프록시 초기화함
        System.out.println(saved.getPosts());
    }
}
