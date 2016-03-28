package cn.fancy.json;

import com.alibaba.fastjson.JSON;

public class FastJsonTest {
	public static void main(String[] args) {
		Group group = new Group();
		group.setId(0L);
		group.setName("admin");

		Dog guestUser = new Dog();
		guestUser.setId(2L);
		guestUser.setName("guest");

		Dog rootUser = new Dog();
		rootUser.setId(3L);
		rootUser.setName("root");

		group.addDog(guestUser);
		group.addDog(rootUser);

		String jsonString = JSON.toJSONString(group);

		System.out.println(jsonString);
	}
}
