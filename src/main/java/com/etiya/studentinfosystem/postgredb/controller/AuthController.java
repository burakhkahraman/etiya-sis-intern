package com.etiya.studentinfosystem.postgredb.controller;
import com.etiya.studentinfosystem.postgredb.dto.LoginDto;
import com.etiya.studentinfosystem.postgredb.dto.RefreshDto;
import com.etiya.studentinfosystem.postgredb.dto.RegisterDto;
import com.etiya.studentinfosystem.postgredb.model.APIResponse;
import com.etiya.studentinfosystem.postgredb.model.JwtResponse;
import com.etiya.studentinfosystem.postgredb.service.AuthService;
import com.etiya.studentinfosystem.postgredb.utils.ExtractJwtDetails;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequestMapping("/auth")
@Tag(name = "Authentication", description = "Yetkilendirme işlemleri için endpointler")
@AllArgsConstructor
public class AuthController {
    private AuthService authService;
    private ExtractJwtDetails extractJwtDetails;
    @Operation(summary = "Kullanıcı girişi sağlar", description = "Verilen bilgilerle kullanıcı girişi yapar ve JWT döner.")
    @PostMapping(path = {"/login", "/sign-in"})
    public ResponseEntity<JwtResponse> loginRequestHandler(@Valid @RequestBody LoginDto loginDto) {
        return ResponseEntity.ok(authService.loginService(loginDto));
    }
    @Operation(summary = "Kullanıcı kayıt işlemi", description = "Verilen bilgilerle yeni bir kullanıcı kaydı oluşturur.")
    @PostMapping("/sign-up")
    public ResponseEntity<APIResponse> registerRequestHandler(@Valid @RequestBody RegisterDto registerDto) throws Exception {
        return new ResponseEntity<APIResponse>(authService.registerService(registerDto), HttpStatus.CREATED);
    }
    @Operation(summary = "JWT yenileme işlemi", description = "Mevcut token ile yeni bir JWT almak için kullanılır.")
    @PostMapping("/refreshToken")
    public ResponseEntity<JwtResponse> refreshTokenRequestHandler(
            @Valid @RequestBody RefreshDto refreshDto)
            throws JsonProcessingException {
        return new ResponseEntity<>(authService.refreshService(refreshDto),HttpStatus.OK);
    }
}
