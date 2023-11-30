package Utils.DbModels;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class ObjectDb {
    protected Connection connection;

    public ObjectDb(Connection connection) {
        this.connection = connection;
    }

    public abstract Object getObject(int id) throws SQLException;

    public abstract void insertObject(Object obj) throws SQLException;

    public abstract void updateObject(Object obj) throws SQLException;

    public abstract void deleteObject(int id) throws SQLException;

    protected abstract Object mapResultSetToObject(ResultSet rs) throws SQLException;
}
