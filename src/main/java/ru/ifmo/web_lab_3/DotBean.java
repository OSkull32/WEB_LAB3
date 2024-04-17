package ru.ifmo.web_lab_3;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import lombok.Getter;
import lombok.Setter;
import java.io.Serializable;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

@Setter
@Getter
@Named("dotBean")
@ApplicationScoped
public class DotBean implements Serializable {

    private Dot dot;
    @Inject
    private DotDao dotDao;
    private Collection<Dot> dotsList;
    private int timezone;
    private Dot lastDot;
    private String dotsJson;

    @PostConstruct
    public void postConstruct() {
        try {
            dot = new Dot();
            lastDot = dot;
            dotsList = new LinkedList<>(DAOFactory.getInstance().getResultDAO().getAllResults());
            System.out.println(dotsList);
        } catch (Exception e) {
            System.err.print("Error in postConstruct()" + e);
        }
    }

    public boolean validateY() {
        return dot.getY() >= -5 && dot.getY() <= 5;
    }

    public void add() throws SQLException {
        long timer = System.nanoTime();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        String currentTime = formatter.format(LocalDateTime.now().minusMinutes(getTimezone()));

        dot.setStatus(AreaChecker.isHit(dot));
        dot.setTime(currentTime);
        dot.setScriptTime((long) ((System.nanoTime() - timer) * 0.001));
        dotDao.addNewResult(dot);
        dotsList.add(dot);


        lastDot = dot;
        dot = new Dot();
        dot.setX(lastDot.getX());
        dot.setY(lastDot.getY());
        dot.setR(lastDot.getR());

    }

    public void clear() throws SQLException {
        dotDao.clearResults();
        dotsList = dotDao.getAllResults();
    }

    public String getDotsJson() {
        return dotsList.stream()
                .map(Dot::toJSON)
                .toList()
                .toString();
    }

}