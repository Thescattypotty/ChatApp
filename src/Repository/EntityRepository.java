package Repository;

import java.sql.Connection;
import java.sql.SQLException;

import Utils.Database;
import Utils.DatabaseInit;

public abstract class EntityRepository extends Database implements DatabaseInit
{
    protected Connection connection;

    public EntityRepository()
    {
        this.connection = Database.Connect();
    }

    @Override
    public abstract void createTable() throws SQLException;

    @Override
    public abstract void DeleteTable() throws SQLException;

    

}
