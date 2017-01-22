package net.logvv.shiro;

import net.logvv.shiro.controller.HomeController;
import net.logvv.shiro.dao.JdbcUserDaoImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@SpringBootTest
public class ApplicationTests
{
    @Autowired
    protected WebApplicationContext wac;

    @Autowired
    private JdbcUserDaoImpl jdbcUserDao;

    private MockMvc mockMvc;

    @Before
    public void setup(){
        mockMvc = MockMvcBuilders.standaloneSetup(new HomeController()).build();
    }

	@Test
	public void testHomeController() throws Exception{
        // hello
        mockMvc.perform(MockMvcRequestBuilders.get("/hello").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("Hello World")));

        RequestBuilder request = null;

        // 获取用户信息
        mockMvc.perform(MockMvcRequestBuilders.get("/account")
                .param("firstname","wysiwyg")
                .param("lastname","qwertyuiop"))
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("success")));
	}

    @Test
    public void testInsertPassenger()
    {
        jdbcUserDao.insertPassenger("赵大宝",42);
        jdbcUserDao.insertPassenger("宋小宝",37);
        jdbcUserDao.insertPassenger("牛老根",62);
    }

    @Test
    public void testQueryPassengers()
    {
        jdbcUserDao.queryPassengers();
    }

    @Test
    public void testUpdatePassenger()
    {
        jdbcUserDao.updatePassenger(7,"刘老根",62);
    }

}
