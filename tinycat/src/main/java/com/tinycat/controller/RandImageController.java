package com.tinycat.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tinycat.util.RandomGraphic;

@Controller
@RequestMapping("/RandImg")
public class RandImageController {
	@RequestMapping
	public void getRandImg(HttpServletRequest req, HttpServletResponse resp) throws IOException, Exception {
		// 设置输出内容为图像，格式为jpeg
		resp.setContentType("image/jpg");
		try {
			// 将内容输出到响应客户端对象的输出流中，生成的图片中包含6个字符
			String v = RandomGraphic.createInstance(6).drawAlpha(RandomGraphic.GRAPHIC_JPEG, resp.getOutputStream());
			// 将字符串的值保留在session中，便于和用户手工输入的验证码比较
			req.getSession().setAttribute("randCode", v);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
