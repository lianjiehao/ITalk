package dataclass;

public class UserData {
	String loginName;
	String name;
	String ganQing;
	String xinBie;
	String yuanXi;
	String nianJi;
	
	public String getXinBie() {
		return xinBie;
	}
	public UserData(String loginName, String name, String ganQing,
			String xinBie, String yuanXi, String nianJi) {
		super();
		this.loginName = loginName;
		this.name = name;
		this.ganQing = ganQing;
		this.xinBie = xinBie;
		this.yuanXi = yuanXi;
		this.nianJi = nianJi;
	}
	public void setXinBie(String xinBie) {
		this.xinBie = xinBie;
	}
	public String getYuanXi() {
		return yuanXi;
	}
	public void setYuanXi(String yuanXi) {
		this.yuanXi = yuanXi;
	}
	public String getNianJi() {
		return nianJi;
	}
	public void setNianJi(String nianJi) {
		this.nianJi = nianJi;
	}
	public String getGanQing() {
		return ganQing;
	}
	public void setGanQing(String ganQing) {
		this.ganQing = ganQing;
	}
	
	public String getLoginName() {
		return loginName;
	}
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
