package com.lambdaschool.usermodel.services;

import com.lambdaschool.usermodel.exceptions.ResourceNotFoundException;
import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.lambdaschool.usermodel.UserModelApplicationTest;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = UserModelApplicationTest.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class UserServiceImplTest {
    @Autowired
    UserService userService;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void findUserById() {
        assertEquals("cinnamon",
                userService.findUserById(7).getUsername());
    }

    @Test
    public void findByNameContaining() {
        assertEquals(1,
                userService.findByNameContaining("barn").size());
    }

    @Test
    public void findAll() {
        System.out.println(userService.findAll());
        assertEquals(5,
                userService.findAll().size());
    }

    @Test
    public void g_delete() {
        userService.delete(7);
        assertEquals(4,
                userService.findAll().size());
    }

    @Test(expected = ResourceNotFoundException.class)
    public void gg_deletefailed() {
        userService.delete(777);
        assertEquals(4,
                userService.findAll().size());
    }

    @Test
    public void c_findByName() {
        assertEquals("admin",
                userService.findByName("admin").getUsername());
    }

    @Test
    public void cc_findByNameFailed() {
        assertEquals("admin",
                userService.findByName("admin").getUsername());
    }

    @Test
    public void save() {
    }

    @Test
    public void update() {
    }

    @Test
    public void deleteAll() {
    }
}