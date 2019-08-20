package com.example.auth;

import com.example.auth.entity.User;
import com.example.auth.repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.util.Collections;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class JpaRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    @Commit
    public void insert() {

  //      User user = new User("ys@ys", passwordEncoder.encode("ys"), "yeonsu", Collections.singletonList("ROLE_USER"));
//		Users user = new Users(userVO.getUserId(), bCryptPasswordEncoder.encode(userVO.getPassword()), true);
   //     userRepository.save(user);

        User user = User.builder()
                .uid("ys@ys")
                .name("yeonsu")
                .password(passwordEncoder.encode("ys"))
                .roles(Collections.singletonList("ROLE_USER"))
                .build();

        //when
        userRepository.save(user);

       // userRepository.findByUid("ys@ys");
    }
}