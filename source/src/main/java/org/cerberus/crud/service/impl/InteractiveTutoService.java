package org.cerberus.crud.service.impl;

import org.cerberus.crud.dao.impl.InteractiveTutoDAO;
import org.cerberus.crud.entity.InteractiveTuto;
import org.cerberus.crud.service.IInterractiveTutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InteractiveTutoService implements IInterractiveTutoService {

    @Autowired
    private InteractiveTutoDAO interactiveTutoDao;

    @Override
    public InteractiveTuto getInteractiveTutorial(int id, boolean withStep) {
        return interactiveTutoDao.getInteractiveTutorial(id,withStep);
    }

    @Override
    public List<InteractiveTuto> getListInteractiveTutorial(boolean withStep) {
        return interactiveTutoDao.getListInteractiveTutorial(withStep);
    }
}
