package com.khrd.test_jpa_hibernate2;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/")
public class UserController {
    private final UserServiceImpl userServiceImpl;

    public UserController(UserServiceImpl userService) {
        this.userServiceImpl = userService;
    }

    @PostMapping("")
    public Users save(@RequestBody UserRuquest userRuquest){
        return userServiceImpl.createUser(userRuquest);
    }

    @PutMapping("/{id}")
    public Users edite(@PathVariable Integer id){
        return userServiceImpl.edit(id);
    }

    @GetMapping("/{id}")
    public Users getUserByID(@PathVariable Integer id){
        return userServiceImpl.getUserById(id);
    }
}
