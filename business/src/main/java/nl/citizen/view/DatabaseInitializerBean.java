package nl.citizen.view;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Startup
@Singleton
@TransactionManagement(TransactionManagementType.BEAN)
public class DatabaseInitializerBean {

  private static final String CHANGELOG_FILE = "/changelog_citizen_view.xml";
  private static final Logger LOGGER = LoggerFactory.getLogger(DatabaseInitializerBean.class);
  private static final String JNDI_CITIZEN_VIEW = "java:jboss/datasources/citizen_view.sql";
  public static final String PERSISTENCE_UNIT_CITIZEN_VIEW = "citizen_view";
  
  @Resource(lookup = JNDI_CITIZEN_VIEW)
  private DataSource ds;

  @PostConstruct
  protected void bootstrap() {
    LOGGER.info("In bootstrap updating");

    final DatabaseUpdaterBean updater = new DatabaseUpdaterBean(CHANGELOG_FILE, this.ds, PERSISTENCE_UNIT_CITIZEN_VIEW);
    updater.update();

    LOGGER.info("Finished updating");

  }
}
