package com.networks.maribo.controllers;

import com.networks.maribo.LocalContext;
import com.networks.maribo.service.TestUser;
import com.networks.maribo.service.mappers.TestUserMapper;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;


@RestController
@RequestMapping(path = "${test-urlprefix}/users")
public class TestUserController {

    private final String queryForAll = "SELECT * FROM TestUser";
    private final String queryForOne = "SELECT * FROM TestUser WHERE id=%s";
    private final String createQuery = "INSERT INTO TestUser(id, name, email) VALUES('%s', '%s', '%s')";

    @GetMapping(path = "")
    public List<TestUser> list(
            @RequestParam(value = "all", defaultValue = "false") boolean all,
            @RequestParam(value = "id", defaultValue = "0") int id
        )
    {

        List<TestUser> users = null;

        if (all) {
            users = LocalContext.runner.query(queryForAll, new TestUserMapper());
        } else if (id != 0) {
            users = LocalContext.runner.query(String.format(queryForOne, id), new TestUserMapper());
        }

        if (users == null || users.size() == 0) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "users not found!"
            );
        }

        return users;
    }

    @PostMapping(path = "", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> create(@RequestBody TestUser user)
    {
        LocalContext.runner.update(
                String.format(createQuery, user.getId(), user.getName(), user.getEmail())
        );
        return ResponseEntity.status(HttpStatus.CREATED).body("Ok");
    }
}
