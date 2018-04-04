package vballada.photosapp.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PhotosappController {

	@RequestMapping("/hello")
	@ResponseBody
	public String helloWorld() {
		return "Hello!";
	}
}
