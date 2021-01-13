package base;

import pages.Dashboard;
import pages.LoginPage;

public interface Pageinitialize {
	
	public LoginPage login = new LoginPage();
	public Dashboard dashboard = new Dashboard();
	void startDriver();
	

}
