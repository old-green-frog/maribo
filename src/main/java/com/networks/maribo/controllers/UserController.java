package com.networks.maribo.controllers;


import com.networks.maribo.LocalContext;
import com.networks.maribo.service.MariboUser;
import com.networks.maribo.service.TestUser;
import com.networks.maribo.service.mappers.MariboUserMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;


import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping(path = "${urlprefix}/users")
public class UserController {

    @GetMapping(path = "")
    public List<MariboUser> list(
            @RequestParam(value = "all", defaultValue = "false") boolean all,
            @RequestParam(value = "id", defaultValue = "0") int id
    ) {

        List<MariboUser> users = null;

        if (all) {
            users = MariboUser.getAll(MariboUser.class, new MariboUserMapper());
        } else if (id != 0) {
            users = MariboUser.getOne(MariboUser.class, new MariboUserMapper(), id);
        }

        if (users == null || users.size() == 0) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "users not found!"
            );
        }

        return users;
    }

    @PostMapping(path = "", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> create(@RequestBody MariboUser user)
    {
        user.save();
        return ResponseEntity.status(HttpStatus.CREATED).body("Ok");
    }

}
