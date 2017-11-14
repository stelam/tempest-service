/**
 * Created by stelam on 2017-11-14.
 */

package com.tempest.service.repository;

import com.tempest.service.model.NodeWar;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;


@Repository
public interface NodeWarRepository extends CrudRepository<NodeWar, Long> {
    Collection<NodeWar> findAll();
}