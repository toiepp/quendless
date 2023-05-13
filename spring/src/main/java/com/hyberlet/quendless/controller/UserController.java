package com.hyberlet.quendless.controller;

import com.hyberlet.quendless.controller.exceptions.BadRequestException;
import com.hyberlet.quendless.model.User;
import com.hyberlet.quendless.model.dto.ServerMessage;
import com.hyberlet.quendless.model.dto.UserDto;
import com.hyberlet.quendless.service.DtoService;
import com.hyberlet.quendless.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

@RestController
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private DtoService dtoService;

    // TODO: добавить всем методам @GetMapping(value = "/test", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(
            summary = "Получить список пользователей",
            description = "Только для админов. Для других пользователей - Forbidden"
    )
    @GetMapping("/users")
    public List<UserDto> getAll() {
        return userService.userList().stream().map(dtoService::userToDto).toList();
    }


    @Operation(
            summary = "Регистрация пользователя",
            description = "Создаёт нового пользователя. При неправильном заполнении сущности - BadRequest. Если логин уже занят - тоже BadRequest."
    )
    @PostMapping("/users/register")
    @ResponseStatus(HttpStatus.CREATED)
    public ServerMessage registration(@RequestBody User user) {
        if (user == null)
            throw new BadRequestException("User not presented");
        System.out.println(user);
        System.out.println("Name: '" + user.getLogin() + "' Password: '" + user.getPassword() + "'");
        if (Objects.equals(user.getLogin(), ""))
            throw new BadRequestException("User has empty login");
        userService.createUser(user);
        return new ServerMessage("Ok");
    }

    @Operation(
            summary = "Получить текущего пользователя",
            description = "Возвращает пользователя, который отправил этот запрос"
    )
    @GetMapping("/users/me")
    public UserDto getCurrentUser() {
        return dtoService.userToDto(userService.getCurrentUser());
    }

    @Operation(
            summary = "Редактировать поля пользователя",
            description = "TODO"
    )
    @PutMapping("/users/{user_id}")
    public String editUser(@PathVariable UUID user_id) {
        // todo: implement
        return null;
    }

    @Operation(
            summary = "Удалить пользователя по id",
            description = "TODO"
    )
    @DeleteMapping("/users/{user_id}")
    public String deleteUser(@PathVariable UUID user_id) {
        // todo: implement
        return null;
    }

}
