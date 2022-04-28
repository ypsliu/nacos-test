package com.demo.provider.service;

import com.demo.provider.entity.User;
import com.demo.provider.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Squaretest 测试插件
 */
@ExtendWith(MockitoExtension.class)
class DemoServiceTest {

    @Mock
    private UserMapper mockUserMapper;
    @Mock
    private DemoService1 mockDemoService1;

    @InjectMocks
    private DemoService demoServiceUnderTest;

    @Test
    void testInsert() {
        // Setup
        when(mockUserMapper.insert(new User(0L, "userName", "password", 0, "address", "phoneNum"))).thenReturn(0);

        // Run the test
        demoServiceUnderTest.insert();

        // Verify the results
        verify(mockUserMapper).insert(new User(0L, "userName", "password", 0, "address", "phoneNum"));
        verify(mockDemoService1).insert1();
    }
}
