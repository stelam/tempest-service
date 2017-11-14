package com.tempest.service.repository;

/**
 * Created by stelam on 2017-11-14.
 */


import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.tempest.service.model.Member;

public interface MemberRepository extends CrudRepository<Member, Long>{
    Member findOneByUsername(String username);
}