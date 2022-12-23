package example.controllers;

import example.domain.Todo;
import example.services.TodoService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class TodoController {
    @Autowired
    protected TodoService todoService;


    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public ModelAndView index()  {
        ModelAndView mav = new ModelAndView("index");
        List<Todo> dataList = todoService.findAllTodos();

        //mav.addObject("todos", mapOf("todos" to dataList))
        mav.addObject("todos", dataList);
        return mav;
    }
    @RequestMapping(value = "/add", method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String add(@RequestParam("text") String request) {
        Todo todo = new Todo(0L, request);
        todoService.save(todo);
        return "redirect:/index";
    }
    @RequestMapping(value = "/delete", method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String delete(@RequestParam("id") Long id) {
        todoService.delete(id);
        return "redirect:/index";
    }
}
