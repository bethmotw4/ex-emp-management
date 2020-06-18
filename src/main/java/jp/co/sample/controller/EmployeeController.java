package jp.co.sample.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.sample.domain.Employee;
import jp.co.sample.form.UpdateEmployeeForm;
import jp.co.sample.service.EmployeeService;

/**
 * 従業員関連機能の処理を制御するコントローラ.
 * 
 * @author yoshiki.morimoto
 *
 */
@Controller
@RequestMapping("/employee")
public class EmployeeController {
	@Autowired
	private EmployeeService service;
	
	/**
	 * 従業員情報更新時に使用するフォームを初期化する.
	 * 
	 * @return 従業員情報更新フォーム
	 */
	@ModelAttribute
	public UpdateEmployeeForm setUpUpdateEmployeeForm() {
		return new UpdateEmployeeForm();
	}
	
	/**
	 * 従業員一覧を出力する.
	 * 
	 * @param model リクエストパラメータ
	 * @return 従業員一覧画面
	 */
	@RequestMapping("/showList")
	public String showList(Model model) {
		List<Employee> employeeList = service.showList();
		model.addAttribute("employeeList", employeeList);
		return "employee/list";
	}
	
	/**
	 * 従業員情報を出力する.
	 * 
	 * @param id ID
	 * @param model リクエストパラメータ
	 * @return 従業員情報の出力画面
	 */
	@RequestMapping("/showDetail")
	public String showDetail(String id, Model model) {
		Employee employee = service.showDetail(Integer.parseInt(id));
		model.addAttribute("employee", employee);
		return "employee/detail";
	}
	
	/**
	 * 従業員情報を更新する.
	 * 
	 * @param form 従業員情報フォーム
	 * @return 従業員一覧画面
	 */
	@RequestMapping("/update")
	public String update(UpdateEmployeeForm form) {
		Employee employee = service.showDetail(Integer.parseInt(form.getId()));
		employee.setDependentsCount(Integer.parseInt(form.getDependentsCount()));
		service.update(employee);
		return "redirect:/employee/showList";
	}
}
