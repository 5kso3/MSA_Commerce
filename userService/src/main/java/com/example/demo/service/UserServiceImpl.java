package com.example.demo.service;

import com.example.demo.dto.UserDto;
import com.example.demo.jpa.UserEntity;
import com.example.demo.jpa.UserRepository;
import com.example.demo.vo.ResponseOrder;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.modelmapper.spi.MatchingStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/* Service를 상속받은 ServiceImpl
   @Service 를 이용해 서비스 로직을 처리하는 것을 알려준다
   여기에서 비즈니스 로직 처리를 한다
   UserDto에 랜덤 ID를 추가하고, UserEntity로 변환한다
   이후 이를 DB에 저장하도록 Repository로 전달
   return 할 때는 UserDto로 값을 다시 변환해서 보내도록 한다
 */
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final Environment env;
    private final RestTemplate restTemplate;

    @Override
    public UserDto createUser(UserDto userDto) {
        userDto.setUserId(UUID.randomUUID().toString());

        ModelMapper mapper = new ModelMapper();
        // 설정 정보가 딱 맞아떨어져야지 변환 가능
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        //DB에 저장하기 위해서는 UserEntity 가 필요
        UserEntity userEntity = mapper.map(userDto, UserEntity.class);
        userEntity.setEncryptedPwd("encrypted_password");
        userRepository.save(userEntity);

        UserDto userVo = mapper.map(userEntity, UserDto.class);
        return userVo;
    }

    @Override
    public UserDto getUserByUserId(String userId) {
        Optional<UserEntity> userEntity = userRepository.findByUserId(userId);
        if(userEntity == null){
            throw new RuntimeException("UserNotFound");
        }
        UserDto userDto = new ModelMapper().map(userEntity, UserDto.class);

        String orderUrl = String.format(env.getProperty("order_service.url"), userId);
        ResponseEntity<List<ResponseOrder>> orderListResponse =
                restTemplate.exchange(orderUrl, HttpMethod.GET, null,
                    new ParameterizedTypeReference<List<ResponseOrder>>() {
                        });
        List<ResponseOrder> orderList = orderListResponse.getBody();
        userDto.setOrders(orderList);

        return userDto;
    }

    @Override
    public Iterable<UserEntity> getUserByAll() {
        return userRepository.findAll();
    }
}


