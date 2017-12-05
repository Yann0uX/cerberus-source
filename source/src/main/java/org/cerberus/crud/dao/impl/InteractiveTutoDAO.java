package org.cerberus.crud.dao.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.cerberus.crud.dao.IInterractiveTutoDAO;
import org.cerberus.crud.entity.InteractiveTuto;
import org.cerberus.crud.entity.InteractiveTutoStep;
import org.cerberus.crud.entity.InteractiveTutoStepType;
import org.cerberus.crud.factory.impl.FactoryInteractiveTuto;
import org.cerberus.crud.factory.impl.FactoryInteractiveTutoStep;
import org.cerberus.database.DatabaseSpring;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

@Repository
public class InteractiveTutoDAO implements IInterractiveTutoDAO {

    private static final Logger LOG = LogManager.getLogger(InteractiveTutoDAO.class);

    @Autowired
    private DatabaseSpring databaseSpring;

    @Autowired
    private FactoryInteractiveTuto factoryIT;

    @Autowired
    private FactoryInteractiveTutoStep factoryITStep;

    @Override
    public InteractiveTuto getInteractiveTutorial(int id, boolean withStep) {

        final String query = "SELECT id, title, description, role, ord, level FROM interactive_tuto it WHERE it.id = ?";

        InteractiveTuto tuto = null;
        try (
                Connection connection = this.databaseSpring.connect();
                PreparedStatement preStat = connection.prepareStatement(query);
        ) {
            preStat.setInt(1, id);

            try (ResultSet resultSet = preStat.executeQuery()) {
                if (resultSet.first()) {
                    tuto = getInteractiveTutoFromResultset(resultSet,withStep);
                }
            }
        } catch (SQLException exception) {
            LOG.warn("Unable to execute query : "+exception.toString(), exception);
        } finally {
            this.databaseSpring.closeConnection();
        }
        return tuto;
    }

    public List<InteractiveTutoStep> getListStep(int idInteractiveTuto) {
        final String query = "SELECT id, selector, description, type, attr1 FROM interactive_tuto_step its WHERE its.id_interactive_tuto = ? order by step_order";

        List<InteractiveTutoStep> tuto = new LinkedList<>();

        try (
                Connection connection = this.databaseSpring.connect();
                PreparedStatement preStat = connection.prepareStatement(query);
        ) {
            preStat.setInt(1, idInteractiveTuto);
            try(ResultSet resultSet = preStat.executeQuery()) {
                while (resultSet.next()) {
                    int idStep = resultSet.getInt("id");
                    String selector = resultSet.getString("selector");
                    String description = resultSet.getString("description");
                    String type = resultSet.getString("type");
                    String attr1 = resultSet.getString("attr1");

                    tuto.add(factoryITStep.create(idStep, selector, description, InteractiveTutoStepType.getEnum(type),attr1));
                }
            }
        } catch (SQLException exception) {
            LOG.warn("Unable to execute query : "+exception.toString(), exception);
        } finally {
            this.databaseSpring.closeConnection();
        }

        return tuto;
    }


    @Override
    public List<InteractiveTuto> getListInteractiveTutorial(boolean withStep) {
        final String query = "SELECT id, title, description, role, ord, level FROM interactive_tuto it";

        List<InteractiveTuto> res = new LinkedList<>();

        try (
                Connection connection = this.databaseSpring.connect();
                PreparedStatement preStat = connection.prepareStatement(query);
        ) {
            try(ResultSet resultSet = preStat.executeQuery()) {
                while (resultSet.next()) {
                    res.add(getInteractiveTutoFromResultset(resultSet, withStep));
                }
            }
        } catch (SQLException exception) {
            LOG.warn("Unable to execute query : "+exception.toString(), exception);
        } finally {
            this.databaseSpring.closeConnection();
        }

        return res;
    }

    private InteractiveTuto getInteractiveTutoFromResultset(ResultSet rs, boolean withStep) throws SQLException {
        int idTuto = rs.getInt("id");
        String title = rs.getString("title");
        String description = rs.getString("description");
        String role = rs.getString("role");
        int order = rs.getInt("ord");
        int level = rs.getInt("level");
        InteractiveTuto tuto = factoryIT.create(idTuto, title, description, role, order, level);

        if (withStep)
            tuto.setSteps(getListStep(idTuto));

        return tuto;
    }
}
