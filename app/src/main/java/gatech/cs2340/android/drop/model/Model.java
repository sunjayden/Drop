package gatech.cs2340.android.drop.model;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import gatech.cs2340.android.drop.BuildConfig;
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
    private final SourceReport theNullCourse = new SourceReport("No Such SR", gatech.cs2340.android.drop.model.Type.OTHER, Water.WASTE);

    /**
     * make a new model
     */
    public Model() {
        _sourceReports = new ArrayList<>();
        loadDummyData();
    }


    private void loadDummyData() {
        _sourceReports.add(new SourceReport("report1", Type.LAKE, Water.WASTE));
        _sourceReports.add(new SourceReport( "report2", Type.SPRING, Water.PORTABLE));
        _sourceReports.add(new SourceReport("report3", Type.STREAM, Water.TREATABLECLEAR));
        _sourceReports.add(new SourceReport("report4", Type.OTHER, Water.TREATABLEMUDDY));
        _currentSourceReport = _sourceReports.get(0);
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
            if (s.equals(sourceReport)) {
                return false;
            }
        }
        _sourceReports.add(sourceReport);
        Log.d("Model", "added here");
        return true;
    }

    public SourceReport getCurrentSourceReport() {
        return _currentSourceReport;
    }

    public void setCurrentSourceReport(SourceReport sourceReport) {
        _currentSourceReport = sourceReport;
    }

    public SourceReport getCourseReportById (int id) {
        for (SourceReport s: _sourceReports) {
            if (s.getId() == id) {
                return s;
            }
        }
        return theNullCourse;
    }

    public void replaceSourceReport(SourceReport sourceReport) {
        SourceReport existing = getCourseReportById(sourceReport.getId());

        if (BuildConfig.DEBUG && sourceReport == null) {
            throw new AssertionError();
        }
        existing.setName(existing.getName());
        existing.setWater(existing.getWater());
        existing.setLocation(existing.getLocation());
        existing.setType(existing.getType());
    }

}
