package com.sullung2yo.thymleafpractice.repository;

import com.sullung2yo.thymleafpractice.domain.entity.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class MemberRepositoryTest {

    @Autowired
    MemberRepository memberRepository;

    @Test
    void findById() {
        //Given
        Member member = Member.builder()
                .name("sullung2yo")
                .build();

        memberRepository.save(member);

        //When
        Optional<Member> foundMember = memberRepository.findById(member.getId());

        //Then
        assertThat(foundMember).isPresent();
        assertThat(foundMember.get().getId()).isEqualTo(member.getId());
        assertThat(foundMember.get().getName()).isEqualTo(member.getName());
    }

    @Test
    void existsByUsername() {
        //Given
        Member member = Member.builder()
                .name("sullung2yo")
                .build();

        memberRepository.save(member);

        //When
        boolean exists = memberRepository.existsByUsername(member.getName());

        //Then
        assertThat(exists).isEqualTo(true);
    }
}