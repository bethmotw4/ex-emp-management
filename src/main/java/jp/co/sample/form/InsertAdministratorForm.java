package jp.co.sample.form;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

/**
 * 管理者登録時に使用するフォーム.
 * 
 * @author yoshiki.morimoto
 *
 */
public class InsertAdministratorForm {
	/** 名前 */
	@NotBlank(message = "名前が未入力です")
	private String name;
	/** メールアドレス */
	@NotBlank(message = "メールアドレスが未入力です")
	@Email(message = "Eメール形式が不正です")
	private String mailAddress;
	/** パスワード */
	@NotBlank(message = "パスワードが未入力です")
	private String password;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMailAddress() {
		return mailAddress;
	}
	public void setMailAddress(String mailAddress) {
		this.mailAddress = mailAddress;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@Override
	public String toString() {
		return "InsertAdministratorForm [name=" + name + ", mailAddress=" + mailAddress + ", password=" + password
				+ "]";
	}
	
}
