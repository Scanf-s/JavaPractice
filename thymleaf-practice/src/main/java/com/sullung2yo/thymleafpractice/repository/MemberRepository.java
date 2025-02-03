package com.sullung2yo.thymleafpractice.repository;

import com.sullung2yo.thymleafpractice.domain.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {

    Optional<Member> findById(Long id);
    boolean existsByUsername(String username);

}
