package web.service;

import web.model.User;

import javax.validation.Valid;
import java.util.List;

public interface UserService {

    List<User> index();

    User show(int id);

    void save(User user);

    void update(@Valid User user);

    void delete(int id);
}
