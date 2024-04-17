package ru.ifmo.web_lab_3;


public class DAOFactory {
    private static DotDao resultDAO;

    private static DAOFactory instance;

    public static DAOFactory getInstance() {
        if (instance == null)
            instance = new DAOFactory();
        return instance;
    }

    public DotDao getResultDAO() {
        if (resultDAO == null)
            resultDAO = new DotDao();
        return resultDAO;
    }
}
