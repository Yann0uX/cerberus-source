package org.cerberus.crud.factory.impl;

import org.cerberus.crud.entity.InteractiveTuto;
import org.cerberus.crud.factory.IFactoryInteractiveTuto;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class FactoryInteractiveTuto implements IFactoryInteractiveTuto {
    @Override
    public InteractiveTuto create(int id, String libelle) {
        return new InteractiveTuto(id,libelle, null);
    }
}
