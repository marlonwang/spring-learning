package net.logvv.swagger.web;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.logvv.swagger.model.Classmate;
import net.logvv.swagger.model.User;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/users")     // 通过这里配置使下面的映射都在/users下，可去除
public class UserController {
	
    static Map<Long, User> users = Collections.synchronizedMap(new HashMap<Long, User>());
    @ApiOperation(value="获取用户列表", notes="返回用户姓名、年龄、id")
    @RequestMapping(value={""}, method=RequestMethod.GET)
    public List<User> getUserList() {
    	List<User> list = new ArrayList<User>();
    	for(int i = 0;i<3;i++)
    	{
    		User u = new User();
    		list.add(u);
    	}
        return list;
    }
    
    @ApiParam(name = "code", value = "班级编号", required = false)
    @RequestMapping(value="/class", method = RequestMethod.GET)
    public Classmate getCLassmates(@RequestParam("code")String classCode)
    {
    	Classmate classmates = new Classmate();
    	List<User> list = new ArrayList<User>();
    	for(int i = 0;i<3;i++)
    	{
    		User u = new User();
    		list.add(u);
    	}
    	classmates.setStudents(list);
    	
    	return classmates;
    }
    
    @ApiOperation(value="创建用户", notes="根据User对象创建用户")
    @ApiImplicitParam(name = "user", value = "用户详细实体user", required = true, dataType = "User")
    @RequestMapping(value="", method=RequestMethod.POST)
    public String postUser(@RequestBody User user) {
        users.put(user.getId(), user);
        return "success";
    }
    
    @ApiOperation(value="获取用户详细信息", notes="根据url的id来获取用户详细信息")
    @ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "Long")
    @RequestMapping(value="/{id}", method=RequestMethod.GET)
    public User getUser(@PathVariable Long id) {
    	// do something
        return users.get(id);
    }
    
    @ApiOperation(value="更新用户详细信息", notes="根据url的id来指定更新对象，并根据传过来的user信息来更新用户详细信息")
    @ApiImplicitParams({
    		@ApiImplicitParam(name = "id", value = "用户ID", required = true),
            @ApiImplicitParam(name = "user", value = "用户详细实体user", required = true, dataType = "User")
    })
    @ApiResponse(code = 200, message="success")
    @RequestMapping(value="/{id}", method=RequestMethod.PUT)
    public String putUser(@PathVariable Long id, @RequestBody User user) {
        User u = users.get(id);
        u.setName(user.getName());
        u.setAge(user.getAge());
        users.put(id, u);
        return "success";
    }
    
    @ApiOperation(value="删除用户", notes="根据url的id来指定删除对象")
    @ApiParam(name = "id", value = "用户ID", required = true)
    @RequestMapping(value="/{id}", method=RequestMethod.DELETE)
    public String deleteUser(@PathVariable Long id) {
        users.remove(id);
        return "success";
    }
}