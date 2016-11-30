package aws.fleet.exception;

/**
 * General purpose {@link RuntimeException}. Wraps Checked {@link Exception}s
 * when no action is required from the client
 *
 * @author rafaeldantas@gmail.com
 *
 */
public class FleetException extends RuntimeException {

	private static final long serialVersionUID = 8591391202079219094L;

	public FleetException(String msg, Exception e) {
		super(msg, e);
	}

	public FleetException(String msg) {
		super(msg);
	}

}
