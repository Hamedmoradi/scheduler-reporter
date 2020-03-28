package com.pooyabyte.training.repository;

import com.pooyabyte.training.domain.Authority;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorityRepository extends JpaRepository<Authority,Long> {
}
