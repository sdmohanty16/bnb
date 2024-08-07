package com.airbnb.service;

import com.airbnb.entity.AppUser;
import com.airbnb.exception.UserExists;
import com.airbnb.payload.AppUserDto;
import com.airbnb.payload.LoginDto;
import com.airbnb.repository.AppUserRepository;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AppUserServiceImpl implements AppUserService{

    private AppUserRepository appUserRepository;
    private JWTService jwtService;

    public AppUserServiceImpl(AppUserRepository appUserRepository, JWTService jwtService) {

        this.appUserRepository = appUserRepository;
        this.jwtService = jwtService;
    }


    @Override
    public AppUserDto createUser(AppUserDto appUserDto) {
        AppUser user = mapToEntity(appUserDto);

        Optional<AppUser> opEmail = appUserRepository.findByEmail(user.getEmail());
        if (opEmail.isPresent()){
            throw new UserExists("Email Id Exists");
        }

        Optional<AppUser> opUsername = appUserRepository.findByEmail(user.getUsername());
        if (opUsername.isPresent()){
            throw new UserExists("Username Exists");
        }

        String hashpw = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt(10));
        user.setPassword(hashpw);
        AppUser savedUser = appUserRepository.save(user);
        AppUserDto dto = mapToDto(savedUser);
        return dto;
    }

    @Override
    public String verifyLogin(LoginDto loginDto) {
        Optional<AppUser> opUsername = appUserRepository.findByUsername(loginDto.getUsername());
        if(opUsername.isPresent()){
            AppUser user = opUsername.get();
            if(BCrypt.checkpw(loginDto.getPassword(), user.getPassword())){
                return jwtService.generateToken(user);
            }
        }
        return null;
    }


    AppUser mapToEntity(AppUserDto dto){
        AppUser entity = new AppUser();
        entity.setName(dto.getName());
        entity.setEmail(dto.getEmail());
        entity.setUsername(dto.getUsername());
        entity.setPassword(dto.getPassword());
        return entity;
    }

    AppUserDto mapToDto(AppUser user){
        AppUserDto dto = new AppUserDto();
        dto.setId(user.getId());
        dto.setName(user.getName());
        dto.setEmail(user.getEmail());
        dto.setUsername(user.getUsername());
        dto.setPassword(user.getPassword());
        return dto;
    }
}
