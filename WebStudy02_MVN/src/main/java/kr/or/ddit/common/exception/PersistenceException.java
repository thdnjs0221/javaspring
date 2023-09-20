package kr.or.ddit.common.exception;

import java.sql.SQLException;

/**
 * Persistence Layer에서 SqlException전환하는 용도로 사용할 예외
 *
 */
public class PersistenceException extends RuntimeException {

	public PersistenceException(String message, SQLException cause) {
		super(message, cause);
	
	}

	public PersistenceException(SQLException cause) {
		super(cause);
	
	}
	

}
