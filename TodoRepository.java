package tuto.demo;

import org.springframework.data.jpa.repository.JpaRepository;
import tuto.demo.model.Todo;

public interface TodoRepository extends JpaRepository <Todo,Long > {

}
