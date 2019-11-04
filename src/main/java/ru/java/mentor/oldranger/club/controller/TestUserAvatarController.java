package ru.java.mentor.oldranger.club.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.server.Session;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.java.mentor.oldranger.club.model.user.Role;
import ru.java.mentor.oldranger.club.model.user.User;
import ru.java.mentor.oldranger.club.model.user.UserAvatar;
import ru.java.mentor.oldranger.club.service.user.UserAvatarService;
import ru.java.mentor.oldranger.club.service.user.UserService;

import javax.servlet.http.HttpSession;
import java.io.IOException;

@Controller
@RequestMapping("/test*")
public class TestUserAvatarController {

    // Тестирование добавления, обновления и удаления аватарки в профиле

    @Autowired
    UserAvatarService userAvatarService;
    @Autowired
    UserService userService;


    @GetMapping("/profile")
    public String getTestProfile(HttpSession session,
                                 @ModelAttribute("message") String message,
                                 Model model) {
        User user = userService.getUserByNickName("User");
        session.setAttribute("user", user);
        model.addAttribute("message", message + "");
        return "profile";
    }


    @PostMapping("/uploadAvatar")
    public String uploadAvatar(@RequestParam("file") MultipartFile file,
                               RedirectAttributes redirectAttributes,
                               @SessionAttribute User user) {

        try {
            if (user.getAvatar() == null) {
                userAvatarService.setAvatarToUser(user, file);
            } else {
                userAvatarService.updateUserAvatar(user, file);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        redirectAttributes.addFlashAttribute("message", "Аватар " + file.getOriginalFilename() + " успешно загружен!");

        return "redirect:/test/profile";
    }

    @GetMapping("/deleteAvatar")
    public String deleteAvatar(@SessionAttribute User user,
                               RedirectAttributes redirectAttributes) {
        try {
            userAvatarService.deleteUserAvatar(user);
        } catch (IOException e) {
            e.printStackTrace();
        }

        redirectAttributes.addFlashAttribute("message", "Аватар удален!");

        return "redirect:/test/profile";
    }


}