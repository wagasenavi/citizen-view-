package uk.vetstoria;

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

  private static final String CHANGELOG_FILE = "/changelog_vetstoria.xml";
  private static final Logger LOGGER = LoggerFactory.getLogger(DatabaseInitializerBean.class);
  private static final String JNDI_VETSTORIA = "java:jboss/datasources/vetstoria";
  public static final String PERSISTENCE_UNIT_VETSTORIA = "vetstoria";
  
  @Resource(lookup = JNDI_VETSTORIA)
  private DataSource ds;

  @PostConstruct
  protected void bootstrap() {
    LOGGER.info("In bootstrap updating");

    final DatabaseUpdaterBean updater = new DatabaseUpdaterBean(CHANGELOG_FILE, this.ds, PERSISTENCE_UNIT_VETSTORIA);
    updater.update();

    LOGGER.info("Finished updating");

  }
}
