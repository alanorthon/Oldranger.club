package ru.java.mentor.oldranger.club.service.utils.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.access.hierarchicalroles.RoleHierarchy;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import ru.java.mentor.oldranger.club.model.user.Role;
import ru.java.mentor.oldranger.club.model.user.User;
import ru.java.mentor.oldranger.club.service.user.UserService;
import ru.java.mentor.oldranger.club.service.utils.SecurityUtilsService;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class SecurityUtilsServiceImpl implements SecurityUtilsService {

    @Autowired
    @Lazy
    private RoleHierarchy roleHierarchy;

    @Autowired
    private UserService userService;

    @Autowired
    private SessionRegistry sessionRegistry;

    private static final Logger LOG = LoggerFactory.getLogger(SecurityUtilsServiceImpl.class);

    @Override
    public List<Long> getUsersFromSessionRegistry() {
       LOG.debug("Getting users from session registry");
        List<Long> usersIds = new ArrayList<>();
        try {
            for (Object principal: sessionRegistry.getAllPrincipals()) {
                if (principal instanceof User) {
                    usersIds.add(((User) principal).getId());
                }
            }
            LOG.debug("Returned list of {} ids", usersIds.size());
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
        return usersIds;
    }

    @Override
    public boolean isAuthorityReachableForLoggedUser(Role role) {
        Collection<? extends GrantedAuthority> authorities = SecurityContextHolder.getContext().getAuthentication().getAuthorities();
        Collection<? extends GrantedAuthority> reachableGrantedAuthorities = roleHierarchy.getReachableGrantedAuthorities(authorities);
        return reachableGrantedAuthorities.contains(role);
    }

    @Override
    public boolean isLoggedUserIsUser() {
        return isAuthorityReachableForLoggedUser(new Role("ROLE_USER"));
    }

    @Override
    public User getLoggedUser() {
        LOG.debug("Getting logged user");
        User user = null;
        try {
            if (SecurityContextHolder.getContext().getAuthentication().getPrincipal() instanceof String) return null;
            String username = ((UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
            user = userService.getUserByNickName(username);
            LOG.debug("Returned user {}", user);
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
        return user;
    }
}