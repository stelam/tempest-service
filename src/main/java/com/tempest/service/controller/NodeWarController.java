package com.tempest.service.controller;

import com.tempest.service.model.NodeWar;
import com.tempest.service.repository.NodeWarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

/**
 * Created by stelam on 2017-11-14.
 */

@RestController
@RequestMapping("/node-wars")
public class NodeWarController {

    @Autowired
    private NodeWarRepository nodeWarRepository;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Collection<NodeWar>> getNodeWars() {
        return new ResponseEntity<>(nodeWarRepository.findAll(), HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<NodeWar> getNodeWar(@PathVariable long id) {
        NodeWar party = nodeWarRepository.findOne(id);

        if (party != null) {
            return new ResponseEntity<>(nodeWarRepository.findOne(id), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> addNodeWar(@RequestBody NodeWar nodeWar) {
        return new ResponseEntity<>(nodeWarRepository.save(nodeWar), HttpStatus.CREATED);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteNodeWar(@PathVariable long id) {
        nodeWarRepository.delete(id);

        return new ResponseEntity<Void>(HttpStatus.OK);
    }
}