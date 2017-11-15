package com.tempest.service.repository;

/**
 * Created by stelam on 2017-11-14.
 */


import com.tempest.service.model.Member;
import org.springframework.data.repository.CrudRepository;
import java.util.Optional;

public interface MemberRepository extends CrudRepository<Member, Long>{
    Optional<Member> findByUsername(String username);
    Member save(Member member);
    void deleteMemberById(Long id);

}