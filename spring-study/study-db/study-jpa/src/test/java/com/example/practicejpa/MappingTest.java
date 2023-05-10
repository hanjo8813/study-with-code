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

    @Test
    void 로딩() {
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

        Post saved = postRepository.findById(post.getId()).get();
        System.out.println("post select 실행됨");

        // toString에서 user_id를 출력해주는데 여기서는 추가쿼리 안나감 -> 프록시가 기본적으로 PK 가지고있음
        System.out.println(saved);
        // user를 가져오는 순간 n+1 발생
        System.out.println(saved.getUser());
    }
    
    
    // fetch join 테스트하기

}
