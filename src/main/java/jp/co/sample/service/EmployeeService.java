package jp.co.sample.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jp.co.sample.domain.Employee;
import jp.co.sample.repository.EmployeeRepository;

/**
 * 従業員関連機能の業務処理を行うサービス.
 * 
 * @author yoshiki.morimoto
 *
 */
@Service
@Transactional
public class EmployeeService {
	@Autowired
	private EmployeeRepository repository;
	
	/**
	 * 従業員情報一覧を全検索する.
	 * 
	 * @return 従業員一覧
	 */
	public List<Employee> showList() {
		return repository.findAll();
	}
	
	/**
	 * 従業員情報を取得する.
	 * 
	 * @param id ID
	 * @return 従業員情報
	 */
	public Employee showDetail(Integer id) {
		return repository.load(id);
	}
	
	/**
	 * 従業員情報を更新する.
	 * 
	 * @param employee 従業員情報
	 */
	public void update(Employee employee) {
		repository.update(employee);
	}
}
