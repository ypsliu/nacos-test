package com.demo.mapStruct.mapper;

import com.demo.mapStruct.dto.UserDTO;
import com.demo.mapStruct.vo.UserVO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

/**
 * Created with IntelliJ IDEA.
 * User: lzy
 * Date: 2022/1/26
 * Time: 14:30
 * Description: No Description
 */
// spring方式加载,生成的实现类上面会自动添加一个@Component注解，可以通过Spring的 @Autowired方式进行注入
//@Mapper(componentModel = "spring")
@Mapper
public interface UserMapper {
    // default方式加载
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    /**
     * 将DTO转VO
     * source 源数据
     * target 目标数据
     * @param userDTO
     * @return
     */
    @Mapping(source = "loginName",target = "userName")
    @Mapping(target = "createTime",dateFormat = "yyyy-MM-dd HH:mm:ss")
    @Mapping(target = "age",source = "age",numberFormat = "$#.00")
    UserVO userVO2UserDTO(UserDTO userDTO);
}
