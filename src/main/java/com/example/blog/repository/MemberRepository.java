package com.example.blog.repository;

import com.example.blog.dto.NewMember;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<NewMember, Long> {

    Optional<NewMember> findByUserId(String userId);
}