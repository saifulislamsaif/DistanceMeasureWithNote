package controller;

import entity.User;
import event.RegistrationCompleteEvent;
import model.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import service.UserService;

import javax.servlet.http.HttpServletRequest;

@RestController
public class RegistrationController {

    @Autowired
    private UserService userService;

    private ApplicationEventPublisher publisher;

    @PostMapping("/register")
    public String registration(@RequestBody UserModel userModel, final HttpServletRequest request) {

        User user = userService.registration(userModel);

        publisher.publishEvent(new RegistrationCompleteEvent(user, applicationUrl(request)  ));

        return "Success";
    }

    private String applicationUrl(HttpServletRequest request) {
        return "http://"+request.getServerName()+":"+request.getServerPort()+request.getServletPath();
    }
}
