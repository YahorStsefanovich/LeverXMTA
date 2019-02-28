package com.leverx.leverxspringproj.dao;

import com.leverx.leverxspringproj.model.Author;
import com.leverx.leverxspringproj.intfce.IAuthorDao;
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
public class AuthorDao implements IAuthorDao {
	private static final Logger logger = LoggerFactory.getLogger(AuthorDao.class);

	private final DataSource dataSource;
	private static final String TABLE_NAME = "javaCFMTA::Author";
	private static final String AUTHOR_ID = "author_id";
	private static final String AUTHOR_NAME = "name";

	@Autowired
	public AuthorDao(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Override  
	public Optional<Author> getById(String id) {
		Optional<Author> entity = Optional.empty();
		try (Connection conn = dataSource.getConnection();
				PreparedStatement stmnt = conn.prepareStatement(
				String.format("SELECT TOP 1 * FROM \"%s\" WHERE \"%s\" = ?", TABLE_NAME, AUTHOR_ID)))
		{
			stmnt.setString(1, id);
			ResultSet result = stmnt.executeQuery();

			if (result.next()) {
				Author author = new Author(
				        id,
                        result.getString(AUTHOR_NAME),
                        null
                );
				entity = Optional.of(author);
			} else {
				entity = Optional.empty();
			}
		} catch (SQLException e) {
			logger.error("Can't get entity by Id: " + e.getMessage());
		}
		return entity;  
	} 
	 
	@Override
	public List<Author> getAll() {

		 List<Author> authorsList = new ArrayList<>();

		 try (Connection conn = dataSource.getConnection();
				 PreparedStatement stmnt = conn.prepareStatement(
				 		String.format("SELECT \"%s\", \"%s\" FROM \"%s\"", AUTHOR_ID, AUTHOR_NAME, TABLE_NAME)))
		 {
			 ResultSet result = stmnt.executeQuery();
			 while (result.next()) {
				 Author author = new Author(
                         result.getString(AUTHOR_ID),
                         result.getString(AUTHOR_NAME),
                         null
                 );
				 authorsList.add(author);
			 }
		 } catch (SQLException e) {
			 logger.error("Can't get list of entities: " + e.getMessage());
		 }
		 return authorsList;
	} 
	 
	@Override
	public void save(Author entity) {
		 try (Connection conn = dataSource.getConnection();
				 PreparedStatement stmnt = conn.prepareStatement(
				String.format("INSERT INTO \"%s\"(\"%s\") VALUES (?)", TABLE_NAME, AUTHOR_NAME)))
		 {
			 stmnt.setString(1, entity.getName());
			 stmnt.execute();
		 } catch (SQLException e) {
			 logger.error("Can't to add entity: " + e.getMessage());
		 }
	} 
	 
	@Override
	public void delete(String id) {
		 try (Connection conn = dataSource.getConnection();
			 	PreparedStatement stmnt = conn.prepareStatement(
			 			String.format("DELETE FROM \"%s\" WHERE \"%s\" = ?", TABLE_NAME, AUTHOR_ID)))
		 {
			 stmnt.setString(1, id);
			 stmnt.execute();
		 } catch (SQLException e) {
			 logger.error("Can't delete entity: " + e.getMessage());
		 }
	}
	 
	@Override
	public void update(Author entity) {
		 try(Connection conn = dataSource.getConnection();
				 PreparedStatement stmnt = conn.prepareStatement(
				 		String.format("UPDATE \"%s\" SET \"%s\" = ? WHERE \"%s\" = ?", TABLE_NAME, AUTHOR_NAME, AUTHOR_ID)))
		 {
			 stmnt.setString(1, entity.getName());
			 stmnt.setString(2, entity.getAuthorId());
			 stmnt.executeUpdate();
		 } catch (SQLException e) {
			 logger.error("Can't update entity: " + e.getMessage());
		 }
	}
	
}
