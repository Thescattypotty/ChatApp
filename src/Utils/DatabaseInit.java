package Utils;

import java.sql.SQLException;

public interface DatabaseInit {
    
    public void createTable() throws SQLException;

    public void DeleteTable() throws SQLException;
}
