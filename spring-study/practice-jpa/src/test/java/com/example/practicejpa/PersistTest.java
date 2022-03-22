package com.example.practicejpa;

import com.example.practicejpa.entity.User;
import com.example.practicejpa.repository.PostRepository;
import com.example.practicejpa.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

@DataJpaTest
class PersistTest {

    @Autowired
    TestEntityManager testEntityManager;
    @Autowired
    UserRepository userRepository;

    @Test
    void 영속성_테스트() {
        // ------------------- ( 저장 ) -------------------
        User user = User.builder()
            .name("a")
            .age(1)
            .build();

        System.out.println("저장전");
        System.out.println(user);

        User saved = userRepository.save(user);
        Long id = saved.getId();
        System.out.println("저장후");
        System.out.println(user);

        System.out.println(user == saved);

        /**
         * 1. user 객체 생성 (비영속)
         * 2. save -> user 영속화 -> flush로 쿼리 날라감 -> save 반환
         * 3. save 또한 영속성 컨텍스트에서 가져온것이므로 영속화된 user와 동일 객체
         */
        System.out.println("\n");

        // ------------------- ( 조회 ) -------------------
        System.out.println("1차 캐시에서 조회");
        User cache = userRepository.findById(id).get();
        System.out.println(cache);

        testEntityManager.clear();

        System.out.println("DB에서 조회");
        User db = userRepository.findById(id).get();
        System.out.println(db);

        System.out.println(cache == db);
        /**
         * 1. 영속성 컨텍스트를 초기화 후 select하면 쿼리 나감
         * 2. 1차캐시 엔티티를 새롭게 채우기에 이전 1차캐시 엔티티와 다른 객체임
         */
        System.out.println("\n");

        // ------------------- ( 수정 ) -------------------
        db.setName("b");
        testEntityManager.flush();
        System.out.println("수정함");
//        testEntityManager.clear();

        User updated = userRepository.findById(id).get();
        System.out.println(updated);

        System.out.println(db == updated);

        /**
         * 1. 변경감지 -> 1차캐시를 변경함
         * 2. flush 발생시 스냅샷과 비교 후 쿼리나감
         * 3. 당연히 1차캐시에서 조회함 (clear 하면 쿼리나가지만 결과는 동일함)
         */
    }


}