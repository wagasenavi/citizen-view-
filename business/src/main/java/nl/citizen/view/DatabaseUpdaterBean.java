package nl.citizen.view;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.MessageFormat;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import liquibase.Contexts;
import liquibase.Liquibase;
import liquibase.database.jvm.JdbcConnection;
import liquibase.exception.LiquibaseException;
import liquibase.resource.ClassLoaderResourceAccessor;
import liquibase.resource.ResourceAccessor;
import nl.citizen.view.exception.UpdateDatabaseException;

public class DatabaseUpdaterBean {

  private static final Logger LOGGER = LoggerFactory.getLogger(DatabaseUpdaterBean.class);
  private final String changeLogFile;
  private final String datasourceName;
  private final DataSource ds;

  public DatabaseUpdaterBean(final String changeLogFile, final DataSource ds, final String datasourceName) {
    this.changeLogFile = changeLogFile;
    this.ds = ds;
    this.datasourceName = datasourceName;
  }

  public void update() {
    LOGGER.info("In update in class {}", DatabaseUpdaterBean.class.getName());
    final ResourceAccessor resourceAccessor = new ClassLoaderResourceAccessor(getClass().getClassLoader());
    try (Connection connection = this.ds.getConnection()) {
      final Liquibase liquiBase = new Liquibase(this.changeLogFile, resourceAccessor, new JdbcConnection(connection));
      liquiBase.update(new Contexts());
    } catch (SQLException | LiquibaseException e) {
      final String msg = MessageFormat.format("Something went wrong updating datasource {0}: {1}", this.datasourceName,
          e.getMessage());
      LOGGER.error(msg, e);
      throw new UpdateDatabaseException(msg, e);
    }

    LOGGER.info("Finished updating datasource {}.", this.datasourceName);

  }
}
