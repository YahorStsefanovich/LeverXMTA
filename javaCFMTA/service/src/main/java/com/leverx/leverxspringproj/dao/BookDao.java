package com.leverx.leverxspringproj.dao;

import com.leverx.leverxspringproj.model.Book;
import com.leverx.leverxspringproj.intfce.IBookDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class BookDao implements IBookDao {

    private static final Logger logger = LoggerFactory.getLogger(BookDao.class);
    private static final String TABLE_NAME = "javaCFMTA::ExtraInfo.Book";
    private static final String BOOK_ID = "book_id";
    private static final String BOOK_NAME = "name";
    private static final String sequenceName = "javaCFMTA::book_id";

    private final DataSource dataSource;

    @Autowired
    public BookDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public Optional<Book> getById(String id) {

        Optional<Book> entity = Optional.empty();

        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmnt = conn.prepareStatement(
                     String.format("SELECT TOP 1 * FROM \"%s\" WHERE \"%s\" = ?", TABLE_NAME, BOOK_ID)))
        {
            stmnt.setString(1, id);
            ResultSet result = stmnt.executeQuery();
            if (result.next()) {
                Book book = new Book(
                            id,
                            result.getString(BOOK_ID),
                            result.getString("author_id")
                        );
                entity = Optional.of(book);
            } else {
                entity = Optional.empty();
            }
        } catch (SQLException e) {
            logger.error("Can't get entity by Id: " + e.getMessage());
        }

        return entity;
    }

    @Override
    public List<Book> getAll() {
        List<Book> bookList = new ArrayList<>();
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmnt = conn.prepareStatement(String.format("SELECT * FROM \"%s\"", TABLE_NAME)))
        {
            ResultSet result = stmnt.executeQuery();

            while (result.next()) {
                Book book = new Book(
                            result.getString(BOOK_ID),
                            result.getString(BOOK_NAME),
                            result.getString("author_id")
                        );
                bookList.add(book);
            }
        } catch (SQLException e) {
            logger.error("Can't get list of entities: " + e.getMessage());
        }

        return bookList;
    }

    @Override
    public Book createEntity(Book entity) {
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmnt = conn.prepareStatement(
             String.format("INSERT INTO \"%s\"(\"author_id\", \"%s\", \"%s\") VALUES (?,?,?)", TABLE_NAME, BOOK_ID, BOOK_NAME)))
        {
        	stmnt.setString(1, entity.getAuthor_id());
        	stmnt.setString(2, Sequence.getNextValue(dataSource, sequenceName, logger));
        	stmnt.setString(3, entity.getName());
            stmnt.execute();
        } catch (SQLException e) {
            logger.error("Can't add entity: " + e.getMessage());
        }

        return entity;
    }

    @Override
    public String deleteEntity(String id) {
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmnt = conn.prepareStatement(String.format("DELETE FROM \"%s\" WHERE \"%s\" = ?", TABLE_NAME, BOOK_ID)))
        {
            stmnt.setString(1, id);
            stmnt.execute();
        } catch (SQLException e) {
            logger.error("Can't delete entity: " + e.getMessage());
        }

        return id;
    }

    @Override
    public Book updateEntity(Book entity) {
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmnt = conn.prepareStatement(
                     String.format("UPDATE \"%s\" SET \"%s\" = ? WHERE \"%s\" = ?", TABLE_NAME, BOOK_NAME, BOOK_ID)))
        {
            stmnt.setString(1, entity.getName());
            stmnt.setString(2, entity.getBook_id());
            stmnt.executeUpdate();
        } catch (SQLException e) {
            logger.error("Can't update entity: " + e.getMessage());
        }

        return entity;
    }
}