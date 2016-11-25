package com.rba.unittestmvp;

import com.rba.unittestmvp.login.LoginPresenter;
import com.rba.unittestmvp.login.LoginView;
import com.rba.unittestmvp.util.Constant;
import com.rba.unittestmvp.util.Util;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Ricardo Bravo on 24/11/16.
 */

@PowerMockIgnore("javax.net.ssl.*")
@RunWith(PowerMockRunner.class)
public class LoginActivityTest{

    @Mock private LoginPresenter loginPresenter;
    @Mock private LoginView loginView;

    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);
        loginPresenter = new LoginPresenter(loginView);
    }

    @Test
    public void login() {
        Map<String, String> data = new HashMap<>();
        data.put(Constant.KEY_EMAIL, "ricardobravo@outlook.com");
        data.put(Constant.KEY_PASSWORD, "123456");
        loginPresenter.login(data);
    }


    @Test
    @PrepareForTest(Util.class)
    public void emailSuccessful() {
        String email = "ricardobravo@outlook.com";
        PowerMockito.mockStatic(Util.class);

        Mockito.when(loginPresenter.validEmail(email)).thenReturn(true);
        Assert.assertTrue(loginPresenter.validEmail(email));
    }

    @Test
    @PrepareForTest(Util.class)
    public void emailError() {
        String email = "ricardobravo";
        PowerMockito.mockStatic(Util.class);
        Mockito.when(loginPresenter.validEmail(email)).thenReturn(false);

        Assert.assertFalse(loginPresenter.validEmail(email));
    }

    @Test
    @PrepareForTest(Util.class)
    public void passwordSuccessful() {
        String password = "123456";
        PowerMockito.mockStatic(Util.class);
        Mockito.when(loginPresenter.validPassword(password)).thenReturn(true);
        Assert.assertTrue(loginPresenter.validPassword(password));
    }

    @Test
    @PrepareForTest(Util.class)
    public void passwordError() {
        String password = "123";
        PowerMockito.mockStatic(Util.class);
        Mockito.when(loginPresenter.validPassword(password)).thenReturn(false);
        Assert.assertFalse(loginPresenter.validPassword(password));
    }

    /*
    @Test
    @PrepareForTest(Util.class)
    public void passwordSuccessfulUtil() {
        String password = "123";
        Util util = Mock(Util.class);

        //PowerMockito.mockStatic(Util.class);
        Assert.assertTrue(Util.validPassword(password));
    }
    */


}
