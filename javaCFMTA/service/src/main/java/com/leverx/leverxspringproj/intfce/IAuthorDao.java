package com.leverx.leverxspringproj.intfce;

import com.leverx.leverxspringproj.model.Author;

import java.util.Map;

public interface IAuthorDao extends IDao<Author, String> {
    Map<?, ?> getRelativeEntity(String id);
}
