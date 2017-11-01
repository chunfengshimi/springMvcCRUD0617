package com.crud0617.controller;

import java.util.Map;

//import javax.validation.Valid;

import com.crud0617.dao.DepartmentDao;
import com.crud0617.dao.EmployeeDao;
import com.crud0617.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

//import com.atguigu.springmvc.crud.dao.DepartmentDao;
//import com.atguigu.springmvc.crud.dao.EmployeeDao;
//import com.atguigu.springmvc.crud.entities.Employee;

@Controller
public class EmployeeHandler {

	@Autowired
	private EmployeeDao employeeDao;
	
	@Autowired
	private DepartmentDao departmentDao;

	@ModelAttribute
	public void getEmployee(@RequestParam(value="id",required=false) Integer id,
							Map<String, Object> map){
		if(id != null){
			map.put("employee", employeeDao.get(id));
		}
	}

	@RequestMapping(value="/emp", method=RequestMethod.PUT)
	public String update(Employee employee){
		employeeDao.save(employee);

		return "redirect:/emps";
	}
	@RequestMapping(value = "/emp/{id}",method = RequestMethod.GET)
	public String input(@PathVariable("id")Integer id,Map<String,Object> map){
		map.put("departments",departmentDao.getDepartments());
		map.put("employee",employeeDao.get(id));
		return "input";
	}

	@RequestMapping(value="/emp/{id}", method=RequestMethod.DELETE)
	public String delete(@PathVariable("id") Integer id) {
		employeeDao.delete(id);
		return "redirect:/emps";
	}

	@RequestMapping(value = "/emp",method = RequestMethod.POST)
	public String save(Employee employee){
		employeeDao.save(employee);
		return "redirect:/emps";
	}
	@RequestMapping(value = "/emp",method = RequestMethod.GET)
	public String input(Map<String,Object> map){
		map.put("departments",departmentDao.getDepartments());
		map.put("employee",new Employee());
		return "input";
	}
	@RequestMapping("success")
	public String success(){
		return "success";
	}
	@RequestMapping("/emps")
	public String list(Map<String, Object> map){
		map.put("employees", employeeDao.getAll());
		return "list";
	}
	
//	@InitBinder
//	public void initBinder(WebDataBinder binder){
//		binder.setDisallowedFields("lastName");
//	}
	
}
