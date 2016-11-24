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
        final boolean valueDefault = true;
        String email = "ricardobravo@outlook.com";

        PowerMockito.mockStatic(Util.class);
        Mockito.when(Util.validEmail(email)).thenReturn(valueDefault);

        boolean value = Util.validEmail(email);
        Assert.assertEquals(valueDefault, value);
        PowerMockito.verifyStatic(Mockito.times(1));

    }

    @Test
    @PrepareForTest(Util.class)
    public void emailError() {
        final boolean valueDefault = false;
        String email = "ricardobravo";

        PowerMockito.mockStatic(Util.class);
        Mockito.when(Util.validEmail(email)).thenReturn(valueDefault);

        boolean value = Util.validEmail(email);
        Assert.assertEquals(valueDefault, value);
        PowerMockito.verifyStatic(Mockito.times(1));
    }


}
