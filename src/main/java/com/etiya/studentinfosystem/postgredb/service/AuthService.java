package com.etiya.studentinfosystem.postgredb.service;

import com.etiya.studentinfosystem.postgredb.dto.LoginDto;
import com.etiya.studentinfosystem.postgredb.dto.RefreshDto;
import com.etiya.studentinfosystem.postgredb.dto.RegisterDto;
import com.etiya.studentinfosystem.postgredb.model.APIResponse;
import com.etiya.studentinfosystem.postgredb.model.JwtResponse;

public interface AuthService {
    public JwtResponse loginService(LoginDto loginDto);
    public APIResponse registerService(RegisterDto registerDto) throws Exception;

    public JwtResponse refreshService( RefreshDto refreshDto);
}
