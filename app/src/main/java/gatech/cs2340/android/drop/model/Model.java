package gatech.cs2340.android.drop.model;

import java.util.ArrayList;
import java.util.List;

import gatech.cs2340.android.drop.controllers.AccountDAO;

/**
 * Created by Jayden Sun on 3/3/17.
 */

public class Model {

    private AccountDAO accountDAO = new AccountDAO();


    /** Singleton instance */
    private static final Model _instance = new Model();
    public static Model getInstance() { return _instance; }

    private List<SourceReport> _sourceReports;

    private SourceReport _currentSourceReport;

    /** Null Object pattern, returned when no sourceReport is found */
    private final SourceReport theNullCourse = new SourceReport("No Such SR", TYPE.OTHER, Water.WASTE);

    /**
     * make a new model
     */
    public Model() {
        _sourceReports = new ArrayList<>();
    }

    public void registerAccount(String name, String email, String password, UserType userType) {
        accountDAO.registerAccount(new Account(name, email, password, userType));
    }

    public void getAccount(String email) {
         accountDAO.getAccount(email);
    }

    public ArrayList<Account> getAccount() {
        return accountDAO.getAccount();
    }


    /**
     * get the sourceReports
     * @return
     */
    public List<SourceReport> get_sourceReports() {
        return _sourceReports;
    }

    /**
     * add sourse-report to the app
     * @param sourceReport
     * @return
     */
    public boolean addSourceReport(SourceReport sourceReport) {
        for (SourceReport s: _sourceReports) {
            if (s.equals(sourceReport)) return false;
        }
        _sourceReports.add(sourceReport);
        return true;
    }

    public SourceReport getCurrentSourceReport() {
        return _currentSourceReport;
    }

    public void setCurrentSourceReport(SourceReport sourceReport) {
        _currentSourceReport = sourceReport;
    }

}
