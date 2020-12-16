package com.lambdaschool.usermodel.services;

import com.lambdaschool.usermodel.exceptions.ResourceNotFoundException;
import com.lambdaschool.usermodel.models.Role;
import com.lambdaschool.usermodel.models.User;
import com.lambdaschool.usermodel.models.UserRoles;
import com.lambdaschool.usermodel.models.Useremail;
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

    @Autowired
    RoleService roleService;

    @Autowired
    UseremailService useremailService;

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

    @Test(expected = ResourceNotFoundException.class)
    public void cc_findByNameFailed() {
        assertEquals("admin",
                userService.findByName("admins").getUsername());
    }

    @Test
    public void h_save() {
        Role r2 = new Role("user");
        r2.setRoleid(2);
//        r2 = roleService.save(r2);

        User u3 = new User("testbarnbarn",
                "ILuvM4th!",
                "testbarnbarn@lambdaschool.local");
        u3.getRoles()
                .add(new UserRoles(u3,
                        r2));
        u3.getUseremails()
                .add(new Useremail(u3,
                        "testbarnbarn@email.local"));

        User addUser = userService.save(u3);
        assertNotNull(addUser);
        assertEquals(u3.getUsername(),
                addUser.getUsername());
    }

    @Test
    public void h_saveput() {
        Role r2 = new Role("user");
        r2.setRoleid(1);
//        r2 = roleService.save(r2);

        User u3 = new User("testbarnbarn",
                "ILuvM4th!",
                "testbarnbarn@lambdaschool.local");
        u3.setUserid(13);
        u3.getRoles().clear();
        u3.getRoles()
                .add(new UserRoles(u3,
                        r2));
//        u3.getUseremails().clear();
        u3.getUseremails()
                .add(new Useremail(u3,
                        "testbarnbarn@email.local"));

        User addUser = userService.save(u3);
        assertNotNull(addUser);
        assertEquals(u3.getUsername(),
                addUser.getUsername());
    }

    @Test(expected = ResourceNotFoundException.class)
    public void hhg_savepufailed() {
        Role r2 = new Role("user");
        r2.setRoleid(1);
//        r2 = roleService.save(r2);

        User u3 = new User("testbarnbarn",
                "ILuvM4th!",
                "testbarnbarn@lambdaschool.local");
        u3.setUserid(13333);
        u3.getRoles().clear();
        u3.getRoles()
                .add(new UserRoles(u3,
                        r2));
        u3.getUseremails().clear();
        u3.getUseremails()
                .add(new Useremail(u3,
                        "testbarnbarn@email.local"));

        User addUser = userService.save(u3);
        assertNotNull(addUser);
        assertEquals(u3.getUsername(),
                addUser.getUsername());
    }

    @Test
    public void i_update() {
        Role r2 = new Role("user");
        r2.setRoleid(1);
//        r2 = roleService.save(r2);

        User u3 = new User("testbarnbarn",
                "ILuvM4th!",
                "testbarnbarn@lambdaschool.local");
        u3.setUserid(13);

        u3.getRoles()
                .add(new UserRoles(u3,
                        r2));

        u3.getUseremails()
                .add(new Useremail(u3,
                        "testbarnbarn@email.local"));

        User addUser = userService.update(u3, 13);
        assertNotNull(addUser);
        assertEquals(u3.getUsername(),
                addUser.getUsername());
    }

    @Test
    public void deleteAll() {
    }
}