package com.example.espressorobot


import android.support.test.rule.ActivityTestRule
import android.support.test.rule.GrantPermissionRule
import android.support.test.runner.AndroidJUnit4
import android.test.suitebuilder.annotation.LargeTest
import com.example.espressorobot.annotations.RegressionTest
import com.example.espressorobot.annotations.SmokeTest
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import com.jraska.falcon.FalconSpoonRule


@LargeTest
@RunWith(AndroidJUnit4::class)
class LoginTest2 {

    @Rule @JvmField
    val grantPermissionRule: GrantPermissionRule = GrantPermissionRule.grant(android.Manifest.permission.WRITE_EXTERNAL_STORAGE)

    @get:Rule
    val mActivityTestRule: ActivityTestRule<MainActivity> = ActivityTestRule(MainActivity::class.java)

    @get:Rule
    val falconSpoonRule = FalconSpoonRule()

    @Test
    @SmokeTest
    fun loginWrongPassword2() {
        login {
            setEmail("mail@example.com")
            setPassword("wrong")
            clickLogin()
            screenShot(falconSpoonRule, "loginWrongPassword")
            matchErrorText(string(R.string.login_fail))
        }

    }

    @Test
    @RegressionTest
    fun loginSuccess2() {
        login {
            setEmail("mail@example.com")
            setPassword("pass")
            clickLogin()
            screenShot(falconSpoonRule, "loginSuccess")
            matchText(R.id.tvName, string(R.string.name_surname))
        }
    }

    private fun string(res: Int): String = mActivityTestRule.activity.getString(res)
}
