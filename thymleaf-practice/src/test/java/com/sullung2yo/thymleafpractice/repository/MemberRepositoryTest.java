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
                .username("sullung2yo")
                .build();

        memberRepository.save(member);

        //When
        Optional<Member> foundMember = memberRepository.findById(member.getId());

        //Then
        assertThat(foundMember).isPresent();
        assertThat(foundMember.get().getId()).isEqualTo(member.getId());
        assertThat(foundMember.get().getUsername()).isEqualTo(member.getUsername());
    }

    @Test
    void existsByUsername() {
        //Given
        Member member = Member.builder()
                .username("sullung2yo")
                .build();

        memberRepository.save(member);

        //When
        boolean exists = memberRepository.existsByUsername(member.getUsername());

        //Then
        assertThat(exists).isEqualTo(true);
    }
}