package com.example.gagong.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.gagong.entity.InviteCode;

public interface InviteCodeRepository extends JpaRepository<InviteCode, Long> {

	Optional<InviteCode> findByCode(int code);
}
