package com.psm.bookingchallenge.controllers;

import com.psm.bookingchallenge.configs.jwt.JwtTokenUtil;
import com.psm.bookingchallenge.dtos.TokenRefreshDTO;
import com.psm.bookingchallenge.dtos.UserDTO;
import com.psm.bookingchallenge.dtos.responses.ResponseDTO;
import com.psm.bookingchallenge.factories.dtos.ResponseDTOFactory;
import com.psm.bookingchallenge.models.enums.RoleEnum;
import com.psm.bookingchallenge.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Controller()
@RequestMapping("/users")
public class UserController {



    @Autowired
    private UserService userService;
    @Autowired
    private ResponseDTOFactory responseDTOFactory;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @PostMapping("/create_guest_user")
    ResponseEntity<ResponseDTO> createGuestUser(@RequestBody UserDTO userDTO ) {
        ResponseDTO response = createUser(userDTO, RoleEnum.ROLE_GUEST.name());
        HttpStatusCode responseCode = response.getData()!=null?HttpStatus.OK:HttpStatus.BAD_REQUEST;
        return new ResponseEntity<>(response, responseCode);
    }

    @PostMapping("/create_owner_user")
    ResponseEntity<ResponseDTO> createOwnerUser(@RequestBody UserDTO userDTO ) {
        ResponseDTO response = createUser(userDTO, RoleEnum.ROLE_OWNER.name());
        HttpStatusCode responseCode = response.getData()!=null?HttpStatus.OK:HttpStatus.BAD_REQUEST;
        return new ResponseEntity<>(response, responseCode);
    }

    @PutMapping()
    public ResponseEntity<ResponseDTO> editUser(@RequestBody UserDTO userDTO) {
        List<String> validationResult = userService.validateUserData(userDTO);
        if (validationResult.isEmpty()) {
            UserDTO userDTOEdited = userService.saveUser(userDTO);
            return new ResponseEntity<>(responseDTOFactory.create(userDTOEdited, null), HttpStatus.OK);
        }
        return new ResponseEntity<>(responseDTOFactory.create(null, validationResult), HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseDTO> getUser(@PathVariable Long id) {
        UserDTO userDTO = userService.getUser(id);
        ResponseDTO responseDTO = responseDTOFactory.create(userDTO, null);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @PostMapping("/refresh_token")
    public ResponseEntity<TokenRefreshDTO> refreshToken(@RequestParam(name = "username") String username) {

        MultiValueMap<String, String> header = new LinkedMultiValueMap<>();
        header.put("Authorization", List.of(jwtTokenUtil.generateTokenForHeader(username)));
        header.put("Access-Control-Expose-Headers", List.of("Authorization"));

        return new ResponseEntity<>(new TokenRefreshDTO(username, true), header, HttpStatus.OK);
    }

    private ResponseDTO createUser(UserDTO userDTO, String role) {
        List<String> validationResult = userService.validateUserData(userDTO);
        if (validationResult.isEmpty()) {
            UserDTO userDTOCreated = userService.saveUser(userDTO);
            List<String> userAuthorities = List.of(role);
            userService.saveRoles(userDTO.getUsername(), userAuthorities);
            return responseDTOFactory.create(userDTOCreated, null);
        }
        return responseDTOFactory.create(null, validationResult);
    }


}
