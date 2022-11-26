package uk.vetstoria.exception;

public class UpdateDatabaseException extends RuntimeException {

  /**
   *
   */
  private static final long serialVersionUID = 8265544811183216857L;

  public UpdateDatabaseException(final String message) {
    super(message);
  }

  public UpdateDatabaseException(final String message, final Throwable cause) {
    super(message, cause);
  }

  public UpdateDatabaseException(final Throwable cause) {
    super(cause);
  }

}
