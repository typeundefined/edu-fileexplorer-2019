package ru.amm.fileexplorer.server.controller.api;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.amm.fileexplorer.server.data.api.UserLoginDTO;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @PostMapping
    public void login(@RequestBody UserLoginDTO creds) {
        throw new RuntimeException("Implement me");
    }
}
