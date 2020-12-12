package com.pedro.fakenewsdetector.model.dao;

import android.content.Context;

import com.pedro.fakenewsdetector.model.FakeNewsModel;
import com.pedro.fakenewsdetector.model.SystemModel;
import com.pedro.fakenewsdetector.model.helper.DaoHelper;

import java.sql.SQLException;
import java.util.List;

public class FakeNewsDAO extends DaoHelper<FakeNewsModel> implements IDAO<FakeNewsModel> {
    public FakeNewsDAO(Context c) {
        super(c, FakeNewsModel.class);
    }

    @Override
    public boolean create(FakeNewsModel obj) {
        try {
            return this.getDao().create(obj) > 0;
        } catch (SQLException e) {
            return false;
        }
    }

    @Override
    public List<FakeNewsModel> list() {
        try {
            return this.getDao().queryForAll();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public FakeNewsModel getById(int id) {
        try {
            return this.getDao().queryForId(id);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean update(FakeNewsModel obj) {
        try {
            return this.getDao().update(obj) > 0;
        } catch (SQLException e) {
            return false;
        }
    }

    @Override
    public boolean delete(FakeNewsModel obj) {
        try {
            return this.getDao().delete(obj) > 0;
        } catch (SQLException e) {
            return false;
        }
    }
}
