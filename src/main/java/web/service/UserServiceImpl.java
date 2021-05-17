package web.service;


import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import web.dao.RoleDAO;
import web.dao.UserDAO;
import web.model.User;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService, UserDetailsService {
    private  final UserDAO userDAO;
    private  final RoleDAO roleDAO;

    private final PasswordEncoder bCryptPasswordEncoder;
        public UserServiceImpl( PasswordEncoder bCryptPasswordEncoder, UserDAO userDAO, RoleDAO roleDAO) {
            this.userDAO = userDAO;
            this.roleDAO = roleDAO;
            this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        }


    @Override
    public List<User> userList() {
        return userDAO.userList();
    }

    @Override
    public void save(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userDAO.save(user);
    }

    @Override
    public void delete(User user) {
        userDAO.delete(user);
    }

    @Override
    public User getById(Long id) {
        return userDAO.getById(id);
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        User user = userDAO.getUserByName(username);
        return user;
    }
}
