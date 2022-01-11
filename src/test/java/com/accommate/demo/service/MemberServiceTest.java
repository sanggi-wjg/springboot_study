package com.accommate.demo.service;

import com.accommate.demo.model.Member;
import com.accommate.demo.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Transactional
class MemberServiceTest {

    @Autowired
    MemberService memberService;
    @Autowired
    MemberRepository memberRepository;

    @Test
//    @Rollback(value = false)
    public void testMemberJoin() throws Exception {
        // given
        Member member = new Member();
        member.setUsername("Snow");

        // when
        Long saveId = memberService.join(member);

        // then
        assertEquals(member.getId(), saveId);
        assertEquals(member, memberRepository.findById(saveId));
    }

    @Test
    public void testDuplicateName() throws Exception {
        // given
        Member member1 = new Member();
        member1.setUsername("Snow");

        Member member2 = new Member();
        member2.setUsername("Snow");

        // when
        memberService.join(member1);

        // then
        assertThrows(IllegalStateException.class, () -> {
            memberService.join(member2);
        });
//        fail("중복 에러가 발생해야 한다.");
    }
}